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
				<a href="/" class="navbar-brand"> Product CRUD App </a>
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
			
				<form:form method="POST" modelAttribute="pc">
					<h2>
						<c:if test="${product.id != 0 && product.id != null}">
	            			Edit Product
	            		</c:if>
						<c:if test="${product.id == 0 || product.id == null}">
	            			Add New Product
	            		</c:if>
					</h2>
					<%-- <form:errors path="*" element="div" cssClass="errors" /> --%>
					<c:if test="${product.id != 0 && product.id != null}">
						<fieldset class="form-group">
							<label>Product ID</label>
							<form:input type="text"
								readonly="true" cssClass="form-control"
								name="id" path="product.id" required="required"/>
							<form:errors path="product.id" cssClass="error" />
						</fieldset>
					</c:if>
					
					<fieldset class="form-group">
						<label>Product Name</label>
						<form:input type="text"
							value="${product.name}" cssClass="form-control"
							name="name" path="product.name" required="required"/>
						<form:errors path="product.name" cssClass="error" />
					</fieldset>
					
					<fieldset class="form-group">
						<label>Product Quantity</label>
						<form:input type="text"
							value="${product.quantity == 0 ? null : product.quantity}" cssClass="form-control"
							name="quantity" path="product.quantity" required="required" />
						<form:errors path="product.quantity" cssClass="error" />
					</fieldset>
					
					<fieldset class="form-group">
						<label>Product Price</label>
						<form:input type="text"
							value="${product.price == 0 ? null : product.price}" cssClass="form-control"
							name="price" path="product.price" required="required" />
						<form:errors path="product.price" cssClass="error" />
					</fieldset>
					
					<fieldset class="form-group">
						<label>Product Category</label>
						<form:input type="text"
							value="" cssClass="form-control"
							name="category" path="category.name" required="required" />
						<form:errors path="category.name" cssClass="error" />
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