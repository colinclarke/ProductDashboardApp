<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Product Dashboard</title>
</head>
<body>
<br>
<div class="container col-md-5">
	<div class="card">
		<div class="card-body">
			<h5 class="card-title">Product Dashboard</h5>
			<p class="card-text">
				This simple application allows users to perform Create, 
				Read, Update, and Delete (CRUD) methods on a set of products. 
				Only admins can update the price of items.
			</p>
			<div class="d-flex justify-content-around">
				<a href="/products" class="btn btn-primary">Go to dashboard</a>
				<security:authorize access="!isAuthenticated()">
				  <a href="/login" class="btn btn-primary">Login</a>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
				  <a href="/logout" class="btn btn-primary">Logout</a>
				</security:authorize>
				<a href = "/create-new-user" class="btn btn-primary">Create new user</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>