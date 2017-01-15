<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Calculation</title>

	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">	
	
	 <script src="/js/jquery-1.7.min.js" type="text/javascript"></script>

	 <script type="text/javascript">
	 $(document).ready(function() {
		 $("#calcButton").on("click",function(){
	 	 var birthDate = $('#birthDate').val();
		 var annualSalary = $('#annualSalary').val();
		 $.getJSON('calculationAction.action', { 'calcBean.birthDate': birthDate, 'calcBean.annualSalary': annualSalary}, function(data) {
			resp = JSON.parse(JSON.stringify(data));
			 $('#validation').empty();
          
			 if ( resp != null && resp.fieldErrors != null) {
				 
				 if (resp.fieldErrors["calcBean.annualSalary"]!=null) {
				 for (var i = 0; i < resp.fieldErrors["calcBean.annualSalary"].length; i++) {
	    	         div = $('<div/>');
	    	         div.addClass("alert alert-danger");
	    	         div.prepend(resp.fieldErrors["calcBean.annualSalary"][i]);
	    	         $('#validation').prepend(div);
				 }};
				 
				 if (resp.fieldErrors["calcBean.birthDate"]!=null) {
				  for (var i = 0; i < resp.fieldErrors["calcBean.birthDate"].length; i++) {
		    	     div = $('<div/>');
		    	     div.addClass("alert alert-danger");
		    	     div.prepend(resp.fieldErrors["calcBean.birthDate"][i]);
		    	     $('#validation').prepend(div);
				  }
				 }
			}
			
			 $('#results').empty();
			 if ( resp != null && ( resp.calcResults != null ) ) {
				for (var i = 0; i < resp.calcResults.length; i++) {
    	         tr = $('<tr/>');
    	            tr.append("<td>" + resp.calcResults[i].currencyCode + "</td>");
    	            tr.append("<td>" + resp.calcResults[i].expense + "</td>");
    	            $('#results').append(tr);
    	   		}
			 }
   		});
	    return false;
        });
	 });
	
	</script>
</head>
<body>
	<div class="container-fluid">	
	<h3>Expenses calculation</h3>

	<s:form>
		<div class="row" id="validation"></div>
	  <div class="form-group">
	    <label for="birthDate">Birth date (dd/mm/yyyy)</label>
		<input type="text" id="birthDate" name="calcBean.birthDate"	title="use format dd/mm/yyyy" />
	  </div>
	
	  <div class="form-group">
				<label for="annualSalary">Annual Salary</label> <input type="text" id="annualSalary" name="calcBean.annualSalary"/>
	  </div>
	
		<button id="calcButton" class="btn btn-primary">Calculate</button>
	</s:form>

	<h3>Expenses calculation results</h3>

	<table class="table table-inverse">
		<thead>
			<tr>
				<th>Currency</th>
				<th>Expense</th>
			</tr>
		</thead>
		<tbody id="results">
		</tbody>
	</table>


	</div>


</body>
</html>