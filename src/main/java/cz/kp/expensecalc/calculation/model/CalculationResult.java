package cz.kp.expensecalc.calculation.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CalculationResult implements Serializable {
	
	private String currencyCode; 
	private BigDecimal expense;
	
	
	public CalculationResult(){
		super();
	}


	public String getCurrencyCode() {
		return currencyCode;
	}


	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	public BigDecimal getExpense() {
		return expense;
	}


	public void setExpense(BigDecimal expense) {
		this.expense = expense;
	}
	
	
	

}
