<%@page import="com.colin.models.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>Product Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark justify-content-between"
			style="background-color: tomato">
			<div class="d-flex">
				<a href="/products" class="navbar-brand"> Product CRUD App </a>
				<ul class="navbar-nav">
					<li>
						<a href="/products" class="nav-link">Products</a>
					</li>
				</ul>
			</div>
			<div>
				<a href="/logout" type="button" class="btn btn-light">Logout</a>
			</div>
		</nav>
	</header>
	<br>
	<c:if test="${alertMessage != null}">
		<div class=container>
			<div class="alert alert-warning alert-dismissible fade show" role="alert">
			  <div class="text-center"><strong>Notice: </strong>${alertMessage}</div>
			</div>
		</div>
	</c:if>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Product List</h3> 
			<hr>
			<security:authorize access="hasRole('ADMIN')">		
				<div class="container text-left">
	
					<a href="/products/new" class="btn btn-success">Add
						New Product</a>
				</div>
			</security:authorize>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Quantity</th>
						<th>Price</th>
						<th>Total</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="product" items="${productList}">

						<tr>
							<td>${product.id}</td>
							<td>${product.name}</td>
							<td>${product.quantity}</td>
							<td>$${String.format("%.2f",product.price)}</td>
							<td>$${String.format("%.2f",product.quantity * product.price)}</td>
							<td>
								<a href="/products/edit/${product.id}" class="btn btn-secondary">Edit</a>
								<security:authorize access="hasRole('ADMIN')">
									<a href="/products/delete/${product.id}" class="btn btn-danger">Delete</a>
								</security:authorize>
							</td>
						</tr>
					</c:forEach>
					
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>$${String.format("%.2f", total)}</td>
						<td></td>
					</tr>
					
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>