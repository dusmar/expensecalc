package cz.kp.expensecalc.calculation.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import cz.kp.expensecalc.calculation.model.Calculation;
import cz.kp.expensecalc.calculation.model.CalculationResult;
import cz.kp.expensecalc.calculation.service.CalculationService;

@Component("calculateAction")
@Scope("prototype")
public class CalculateAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Calculation calcBean;

	private List<CalculationResult> calcResults;

	@Autowired
	private CalculationService calculationService;

	public CalculateAction() {
		super();
	}

	public void validate() {

		if (calcBean.getAnnualSalary() == null) {

			addFieldError("calcBean.annualSalary", "Annual salary is required.");

		}

		if (calcBean.getBirthDate() == null) {

			addFieldError("calcBean.birthDate", "Birth date is required.");

		}

	}

	
	public String execute() throws Exception {
		calcResults = calculationService.calculate(calcBean);
		return SUCCESS;
	}

	public Calculation getCalcBean() {
		return calcBean;
	}

	public void setCalcBean(Calculation calcBean) {
		this.calcBean = calcBean;
	}

	public List<CalculationResult> getCalcResults() {
		return calcResults;
	}

	public void setCalcResults(List<CalculationResult> calcResults) {
		this.calcResults = calcResults;
	}

}
