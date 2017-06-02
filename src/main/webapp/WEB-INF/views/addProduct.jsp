<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<spring:url value="/resources/css/font-awesome.min.css" var="fontCss" />
<link href="${fontCss}" rel="stylesheet">
<spring:url value="/resources/css/sb-admin.css" var="adminCss" />
<link href="${adminCss}" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
</head>
<body>
	<div class="row">
		<c:if test="${requestScope.message!=null}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${requestScope.message}</strong>
			</div>
		</c:if>
	</div>
	<div class="col-md-4"></div>

	<div class="col-md-4">
		<hr>
		<form:form action="addProduct.htm" modelAttribute="product"
			method="post" enctype="multipart/form-data" ng-form="product" name="product">
			<fieldset class="form-group">
				<label for="name">Name </label> <input type="text"
					class="form-control" name="name"> 
				<form:errors path="name" style="color:red"></form:errors>
			</fieldset>
			<fieldset class="form-group">
				<label for="exampleInputPassword1">No Of Quantity</label> <input
					type="number" name="availableQuantity"  ng-model="availableQuantity" min="0" class="form-control" required>
				<form:errors path="availableQuantity" style="color:red"></form:errors>
				<span class="error"
					ng-show="product.availableQuantity.$error.required">
					Required!</span>
			</fieldset>
			<fieldset class="form-group">
				<label for="exampleInputPassword1">Price Per Unit</label> <input
					type="number" name="pricePerUnit" ng-model="pricePerUnit" min="0" class="form-control"
					required>
				<form:errors path="pricePerUnit" style="color:red"></form:errors>
				<span class="error" ng-show="product.pricePerUnit.$error.required">
					Required!</span> 
			</fieldset>
			<fieldset class="form-group">
				<label for="description">Product Description</label>
				<textarea type="text" name="description" class="form-control"></textarea>
				<form:errors path="description" style="color:red"></form:errors>
			</fieldset>
			<fieldset class="form-group">
				<label for="category"> Product Category </label> <select
					name="pCategory.name" class="form-control">
					<c:forEach var="category" items="${requestScope.categoryList}">
						<option><c:out value="${category.name}"></c:out></option>
					</c:forEach>
				</select>
			</fieldset>
			<fieldset class="form-group">
				<label for="addImage">Add Product Image</label> <input type="file"
					name="productImage" class="form-control"> <span>${pError}</span>
			</fieldset>
			<button type="submit" class="btn btn-primary">Add</button>
		</form:form>
	</div>
	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>