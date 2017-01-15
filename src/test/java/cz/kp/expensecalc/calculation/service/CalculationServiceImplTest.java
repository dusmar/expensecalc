package cz.kp.expensecalc.calculation.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cz.kp.expensecalc.calculation.model.Calculation;
import cz.kp.expensecalc.calculation.model.CalculationResult;
import cz.kp.expensecalc.test.ExpenseCalcTest;

public class CalculationServiceImplTest extends ExpenseCalcTest {

	@Autowired
	private CalculationService service;
	
	@Test
	public void getExpensesSimpleTest() throws Exception{
		Calculation calc = new Calculation();
		calc.setAnnualSalary(new BigDecimal(12));
		Date currentDate = Date.valueOf("2017-01-15");
		calc.setBirthDate(Date.valueOf("1983-01-14"));
		//base = (60-34) * 12/12 = 26;
		List<CalculationResult> results = service.getExpenses(calc, currentDate);
		Assert.assertTrue(results.size()==3);

		
		CalculationResult expectedResultCZK = new CalculationResult();
		expectedResultCZK.setCurrencyCode("CZK");
		expectedResultCZK.setExpense(new BigDecimal(26));
	
		Assert.assertTrue(results.contains(expectedResultCZK));
		
		CalculationResult expectedResultEUR = new CalculationResult();
		expectedResultEUR.setCurrencyCode("EUR");
		expectedResultEUR.setExpense(new BigDecimal(520));
	
		Assert.assertTrue(results.contains(expectedResultEUR));

		
		CalculationResult expectedResultUSD = new CalculationResult();
		expectedResultUSD.setCurrencyCode("USD");
		expectedResultUSD.setExpense(new BigDecimal(260));
	
		Assert.assertTrue(results.contains(expectedResultUSD));

		
	}
	

}
