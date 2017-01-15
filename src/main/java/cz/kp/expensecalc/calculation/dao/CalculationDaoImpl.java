package cz.kp.expensecalc.calculation.dao;

import org.springframework.stereotype.Repository;

import cz.kp.expensecalc.calculation.model.Calculation;
import cz.kp.expensecalc.common.dao.AbstractJpaDao;

/**
 * 
 * @author dmarusca
 *
 */
@Repository
public class CalculationDaoImpl extends AbstractJpaDao<Calculation> implements CalculationDao {
	
	public CalculationDaoImpl(){
		super();
	}

}
