package cz.kp.expensecalc.calculation.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cz.kp.expensecalc.calculation.model.Calculation;
import cz.kp.expensecalc.test.ExpenseCalcTest;

public class CalculationDaoImplTest extends ExpenseCalcTest {

	@Autowired
	private CalculationDao calculationDao;

	
	@Test
	public void saveTestSimple(){
		Calculation calc = new Calculation();
		calc.setAnnualSalary(BigDecimal.ONE);
		long curr = System.currentTimeMillis();
		calc.setCalcDate(new Date(curr));
		calculationDao.save(calc);
		List<Calculation> results = calculationDao.findAll("id", true);
		Assert.assertTrue(!results.isEmpty());
		Assert.assertTrue(calc.getAnnualSalary().compareTo(BigDecimal.ONE)==0);
		Assert.assertTrue(calc.getCalcDate().compareTo(new Date(curr))==0);
	}
	
	
}
