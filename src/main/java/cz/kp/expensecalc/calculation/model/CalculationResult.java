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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
		result = prime * result + ((expense == null) ? 0 : expense.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalculationResult other = (CalculationResult) obj;
		if (currencyCode == null) {
			if (other.currencyCode != null)
				return false;
		} else if (!currencyCode.equals(other.currencyCode))
			return false;
		if (expense == null) {
			if (other.expense != null)
				return false;
		} else if (!(expense.compareTo(other.expense)==0))
			return false;
		return true;
	}
	
	
	

}
