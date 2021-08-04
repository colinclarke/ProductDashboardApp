<%@page import="com.colin.models.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
			<div>
				<a href="/products" class="navbar-brand"> Product CRUD App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="/products" class="nav-link">Products</a></li>
			</ul>
		</nav>
	</header>
	<br>
	
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
			
				<form:form method="POST" modelAttribute="product">
				<h2>
					<c:if test="${product.name != null}">
            			Edit Product
            		</c:if>
					<c:if test="${product.name == null}">
            			Add New Product
            		</c:if>
				</h2>
					<%-- <form:errors path="*" element="div" cssClass="errors" /> --%>
					<c:if test="${product.name != null}">
						Product ID: ${product.id}<br>
					</c:if>
					
					<fieldset class="form-group">
						<label>Product Name</label>
						<form:input type="text"
							value="${product.name}" class="form-control"
							name="name" path="name" required="required"/>
						<form:errors path="name" cssClass="error" />
					</fieldset>
					
					<fieldset class="form-group">
						<label>Product Quantity</label>
						<input type="text"
							value="${product.quantity == 0 ? null : product.quantity}" class="form-control"
							name="quantity" required="required">
						<form:errors path="quantity" cssClass="error" />
					</fieldset>
					
					<fieldset class="form-group">
						<label>Product Price</label>
						<input type="text"
							value="${product.price == 0 ? null : product.price}" class="form-control"
							name="price" required="required">
						<form:errors path="price" cssClass="error" />
					</fieldset>
					<div class="d-flex justify-content-around">
						<a href="/products" class="btn btn-danger">Cancel</a>
						<input type="submit" class="btn btn-success" value="Save"/>
					</div>
				</form:form>
				
			</div>
		</div>
	</div>
</body>
</html>