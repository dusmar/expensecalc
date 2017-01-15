<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Expenses calculation results</title>
	<style type="text/css">
.odd {
	background-color: silver;
}

.even {
	background-color: white;
}
</style>
</head>
<body>
	<h3>Expenses calculation results</h3>

	<table>
		<tr>
			<td width="50%">Currency</td>
			<td width="50%">Expense</td>
		</tr>

		<s:iterator value="calcResults" status="rowstatus">
			<tr
				class="<s:if test="#rowstatus.odd == true ">odd</s:if><s:else>even</s:else>">
				<td width="50%"><s:property value="currencyCode" /></td>
				<td width="50%"><s:property value="expense" /></td>
			</tr>
		</s:iterator>
	</table>




</body>
</html>