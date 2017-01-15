package cz.kp.expensecalc.calculation.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cz.kp.expensecalc.test.ExpenseCalcTest;

public class CalculationServiceImplTest extends ExpenseCalcTest {

	@Autowired
	private CalculationService service;
	
	@Test
	public void calculationTestSimple(){
		Assert.assertTrue(true);
	}
	

}
