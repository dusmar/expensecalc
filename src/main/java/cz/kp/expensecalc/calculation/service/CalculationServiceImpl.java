package cz.kp.expensecalc.calculation.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cz.kp.expensecalc.calculation.dao.CalculationDao;
import cz.kp.expensecalc.calculation.model.Calculation;
import cz.kp.expensecalc.calculation.model.CalculationResult;
import cz.kp.expensecalc.calculation.service.ws.CurrencyConverterService;

/**
 * Service to calculate expenses in predefined currencies
 * @author dmarusca
 *
 */
@Service
public class CalculationServiceImpl implements CalculationService {
	
	private static final Logger logger = Logger.getLogger(CalculationServiceImpl.class);

	@Autowired
	private CalculationDao dao;

	@Autowired
	private CurrencyConverterService currencyConverterService;

	private static final String[] SUPPORTED_OUTPUT_CURRENCIES = { "CZK", "USD", "EUR" };

	private static final String BASE_CURRENCY = "CZK";

	private static final BigDecimal MONTHS = new BigDecimal(12);

	private static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;

	private static final int SCALE = 2;

	public CalculationServiceImpl() {
		super();
	}

	/**
	 * Store calculation to DB and return list of expenses in given currencies
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CalculationResult> calculate(Calculation entity) throws Exception {
		Date currentDate = new Date(System.currentTimeMillis());
		saveCalculation(entity, currentDate);
		return getExpenses(entity, currentDate);
	}

	/**
	 * Save calculation to DB
	 * @param entity
	 * @param currentDate
	 */
	private void saveCalculation(Calculation entity, Date currentDate) {
		entity.setCalcDate(currentDate);
		dao.save(entity);
	}

	/**
	 * 
	 * Calculate expenses in predefined currencies. First of all, it calculates age from given birthdate. Then it calculates expense in
	 * base currency as (60 - age)*monthly_salary, where monthly_salary is calculated from annual one (annual_salary/12).  
	 * Expense in base currency is converted to all predefines currencies by current Yahoo conversion rate.
	 */
	public List<CalculationResult> getExpenses(Calculation entity, Date currentDate) throws Exception {
		long age = getAgeFromBirthDate(entity.getBirthDate(), currentDate);
		BigDecimal expenseInBaseCurrency = new BigDecimal(60 - age).multiply(getMonthSalary(entity.getAnnualSalary()));
		List<CalculationResult> results = new ArrayList<CalculationResult>();
		for (String curreny : getSupportedCurrencies()) {
			CalculationResult result = new CalculationResult();
			result.setCurrencyCode(curreny);
			result.setExpense(expenseInBaseCurrency.multiply(currencyConverterService.convert(BASE_CURRENCY, curreny))
					.setScale(SCALE, ROUNDING_MODE));
			results.add(result);
		}

		return results;

	}

	protected String[] getSupportedCurrencies() {
		return SUPPORTED_OUTPUT_CURRENCIES;
	}

	private long getAgeFromBirthDate(Date date, Date currentDate) {
		return ChronoUnit.YEARS.between(date.toLocalDate(), currentDate.toLocalDate());
	}

	private BigDecimal getMonthSalary(BigDecimal yearlySalary) {
		return yearlySalary.divide(MONTHS, SCALE, ROUNDING_MODE);
	}

}
