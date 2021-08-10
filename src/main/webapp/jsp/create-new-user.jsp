<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>Add or edit a product</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link href="/css/error.css" rel="stylesheet">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
		</nav>
	</header>
	<br>
	
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
			
				<form:form method="POST">
				<fieldset class="form-group">
						<label>Name</label>
						<input type="text"
							 class="form-control"
							name="name" required="required"/>
					</fieldset>
					
					<fieldset class="form-group">
						<label>Password</label>
						<input type="password"
							 class="form-control"
							name="password" required="required"/>
					</fieldset>
					
					
					<fieldset class="form-group">
					<security:authorize access="hasRole('ADMIN')">	
					<c:forEach items = "${roles}" var="r">
						<input type = "checkbox" name = "role" value = "${r.name}">
						<label>${r.name}</label>
							</c:forEach>
					
					</security:authorize>
					
					<div class="d-flex justify-content-around">
						<a href="/landing-page" class="btn btn-danger">Cancel</a>
						<input type="submit" class="btn btn-success" value="Save"/>
					</div>
				</form:form>
				
			</div>
		</div>
	</div>
</body>
</html>