package cz.kp.expensecalc.calculation.web;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import cz.kp.expensecalc.calculation.model.Calculation;
import cz.kp.expensecalc.calculation.model.CalculationResult;
import cz.kp.expensecalc.calculation.service.CalculationService;
import cz.kp.expensecalc.calculation.service.CalculationServiceImpl;

@Component("calculateAction")
@Scope("prototype")
public class CalculateAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Calculation calcBean;

	private List<CalculationResult> calcResults;

	@Autowired
	private CalculationService calculationService;
	
	private static final Logger logger = Logger.getLogger(CalculateAction.class);


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
		
		if (calcBean.getAnnualSalary() != null && calcBean.getAnnualSalary().compareTo(new BigDecimal(2000000000))>0) {

			addFieldError("calcBean.annualSalary", "Annual salary cannot be more than 2000000000.");

		}
		
		if (calcBean.getAnnualSalary() != null && calcBean.getAnnualSalary().compareTo(new BigDecimal(0))<0) {

			addFieldError("calcBean.annualSalary", "Annual salary cannot be less than 0.");

		}

	}

	
	public String execute()  {
		try {
			calcResults = calculationService.calculate(calcBean);
		} catch (Throwable t) {
			logger.error("Calculation error", t);
			return ERROR;
		}
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
