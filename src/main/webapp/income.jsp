<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Income Tracker</title> 
<link href="css/expense.css" rel="stylesheet" />
</head>
<body>
    <div class="expense-form">
		<h2>Income Tracker</h2>
		<form id="expense-form" method="post" action="IncomeServlet">
			<label for="expense-name">Source:</label>
			<input type="text" id="source" name="source"><br>

			<label for="expense-amount">Amount:</label>
			<input type="number" id="amount" name="amount"><br>

			<!--<label for="expense-category">Category:</label>
			<input type="text" id="expense-category" name="expense-category"><br>-->

            <label for="date">Date:</label>
            <input type="date" id="date" name="date"><br>

			<button type="submit" id="submit-expense">Submit</button>
		</form>
	</div>
    

</body>
</html>