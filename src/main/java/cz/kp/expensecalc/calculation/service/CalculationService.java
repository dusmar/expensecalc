package cz.kp.expensecalc.calculation.service;

import java.sql.Date;
import java.util.List;

import cz.kp.expensecalc.calculation.model.Calculation;
import cz.kp.expensecalc.calculation.model.CalculationResult;

public interface CalculationService {

    public List<CalculationResult> calculate(Calculation entity) throws Exception;

	public List<CalculationResult> getExpenses(Calculation calc, Date currentDate) throws Exception;


}
