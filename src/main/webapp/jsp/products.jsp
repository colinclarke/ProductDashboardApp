<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>Product Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="" class="navbar-brand"> Product
					CRUD App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/products"
					class="nav-link">Products</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Product List</h3> 
			<hr>
			<security:authorize access="isAuthenticated()">		
				<div class="container text-left">
	
					<a href="<%=request.getContextPath()%>/products/new" class="btn btn-success">Add
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
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="product" items="${productList}">

						<tr>
							<td>${product.id}</td>
							<td>${product.name}</td>
							<td>${product.quantity}</td>
							<td>$${String.format("%.2f",product.price)}</td>
							<td>$${String.format("%.2f",product.quantity * product.price)}</td>
							<td><a href="/products/edit/${product.id}">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="/products/delete/${product.id}">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>