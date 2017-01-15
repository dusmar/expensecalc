<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Calculation</title>
</head>
<body>
	<h3>Expenses calculation</h3>

	<s:form action="calculationAction">
		<s:textfield name="calcBean.birthDate" label="Birth date (dd/mm/yyyy)"  title="use format dd/mm/yyyy" />
		<s:textfield name="calcBean.annualSalary" label="Annual salary" />
		<s:submit />
	</s:form>

</body>
</html>