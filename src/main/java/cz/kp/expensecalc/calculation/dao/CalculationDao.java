package cz.kp.expensecalc.calculation.dao;

import java.util.List;

import cz.kp.expensecalc.calculation.model.Calculation;

public interface CalculationDao {

	void save(Calculation calculation);
	
	List<Calculation> findAll(String property, boolean asc);

}
