package cz.kp.expensecalc.calculation.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import cz.kp.expensecalc.common.model.AbstractEntity;

@Entity
@Table(name = "CALCULATION")
public class Calculation extends AbstractEntity {

	
	private static final long serialVersionUID = 1L;

	@Column(name = "ANNUAL_SALARY", nullable = false)
	private BigDecimal annualSalary;

	@Column(name = "CALC_DATE", nullable = false)
	private Date calcDate;

	@Transient
	private Date birthDate;

	public Calculation() {
		super();
	}

	public BigDecimal getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(BigDecimal annualSalary) {
		this.annualSalary = annualSalary;
	}

	public Date getCalcDate() {
		return calcDate;
	}

	public void setCalcDate(Date calcDate) {
		this.calcDate = calcDate;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}
