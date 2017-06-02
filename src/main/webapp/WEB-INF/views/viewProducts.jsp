<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="farmerProducts">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<spring:url value="/resources/css/font-awesome.min.css" var="fontCss" />
<link href="${fontCss}" rel="stylesheet">
<spring:url value="/resources/css/shop-homepage.css" var="shCss" />
<link href="${shCss}" rel="stylesheet">
<spring:url value="/resources/css/sb-admin.css" var="adminCss" />
<link href="${adminCss}" rel="stylesheet">
<spring:url value="/resources/js/farmerProducts.js" var="fpJs" />
<script src="${fpJs}"></script>
<spring:url value="/resources/js/dirPagination.js" var="dirjs" />
<script src="${dirjs}"></script>
</head>
<body ng-controller="farmerProductsController"
	data-ng-init="getAllProducts(1)">

	<div class="row">
		<div class="col-md-4">
			<label for="cat"> Filter By Category Name</label> <select name="cat"
				class="form-control" ng-model="categorySelected"
				ng-options="cat as cat.name for cat in categories"
				ng-change="getAllProducts(1)">
				<option value="">Select Category Name</option>
			</select>

		</div>
		<div class="col-md-4">
			<label for="cat"> Filter By Price </label> <select
				ng-model="priceSel"
				ng-options="price as price.text for price in prices"
				ng-change="getAllProducts(1)" class="form-control">
				<option value="">Select Price</option>
			</select>

		</div>
	</div>
	<div class="row">
		<hr>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-sm-3 col-lg-3 col-md-3"
					dir-paginate="product in products|itemsPerPage:8"
					total-items="totalCount">
					<div class="thumbnail">
						<img src="${pageContext.request.contextPath}{{product.getPhotoName}}" class="img-thumbnail" alt="" style="min-height:50px;height:140px;" >
						<div class="caption">
							<h4>
								<span class="text-primary">Name: </span>{{product.name}}
							</h4>
							<h4>
								<span class="text-primary">Price: </span>{{product.pricePerUnit}}
							</h4>
							<h4>
								<span class="text-primary">Available Quantity: </span>{{product.availableQuantity}}
							</h4>
							<h4>
								<span class="text-primary">Category:</span>{{product.category.name}}
							</h4>
							<p>{{product.description}}</p>
						</div>
						<button type="button" value="login" name="submit"
							class="btn btn-success btn-block" data-toggle="modal"
							data-target="#productCart" ng-show="{{product.availableQuantity!=0}}" ng-click="productDetails(product)">Add
							To Cart</button>
						<button value="login" name="submit"
							class="btn btn-success btn-block" data-toggle="modal" ng-show="{{product.availableQuantity==0}}"
							    ng-disabled="{{product.availableQuantity==0}}">Out
							Of Stock</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<dir-pagination-controls max-size="5" direction-links="true"
				boundary-links="true" on-page-change="getAllProducts(newPageNumber)">
			</dir-pagination-controls>
		</div>
	</div>
	<div class="modal fade" id="productCart" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" ng-click="clearFields()">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add To Cart</h4>
					<div ng-if="ds">
						<div class="alert alert-success alert-dismissible" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>{{mes}}</strong>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<input type="hidden" class="form-control" ng-model="id" name="id"
						readonly="readonly">
					<fieldset class="form-group">
						<label for="name">Name </label> <input type="text"
							class="form-control" ng-model="name" name="name"
							readonly="readonly">
					</fieldset>
					<fieldset class="form-group">
						<label for="name">Available Quantity </label> <input type="number"
							class="form-control" name="quantity" ng-model="quantity"
							readonly="readonly">
					</fieldset>
					<fieldset class="form-group">
						<label for="name">Listed Price</label> <input type="number"
							class="form-control" ng-model="price" name="price"
							readonly="readonly">
					</fieldset>
					<fieldset class="form-group">
						<label for="name">Choose Quantity </label> <input type="number"
							class="form-control" name="quantity" min="0" max="{{quantity}}"
							ng-change="calculateCartPrice()" ng-model="selQuantity">
					</fieldset>
					<fieldset class="form-group">
						<label for="name">You I'll Pay </label> <input type="number"
							class="form-control" ng-model="cartPrice" name="cartPrice"
							readonly="readonly">
					</fieldset>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							ng-click="clearFields()" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary"
							ng-disabled="cartBtn" ng-click="addToCart()">Add To Cart</button>
					</div>
				</div>

			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>