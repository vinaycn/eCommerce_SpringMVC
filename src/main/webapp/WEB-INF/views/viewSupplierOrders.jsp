<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="supplierOrder">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">

<spring:url value="/resources/css/font-awesome.min.css" var="fontCss" />
<link href="${fontCss}" rel="stylesheet">
<spring:url value="/resources/css/shop-homepage.css" var="shCss" />
<link href="${shCss}" rel="stylesheet">
<spring:url value="/resources/css/sb-admin.css" var="adminCss" />
<link href="${adminCss}" rel="stylesheet">
<spring:url value="/resources/js/supplierOrder.js" var="soJs" />
<script src="${soJs}"></script>
<spring:url value="/resources/js/dirPagination.js" var="dirjs" />
<script src="${dirjs}"></script>
</head>
<body ng-controller="supplierOrderController"
	data-ng-init="getOrders(1)">
	<div class="row">
		<h4>Your Order List</h4>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Customer Name</th>
					<th>Product Name</th>
					<th>Category Name</th>
					<th>Available Quantity</th>
					<th>Ordered Quantity</th>
					<th>You ill Get</th>
					<th>Ordered Date</th>
					<th>Status</th>
					<th>Change Status To</th>
				</tr>
			</thead>
			<tbody>
				<tr dir-paginate="oi in orders|itemsPerPage:8"
					total-items="totalCount">
					<td>{{oi.forder.farmer1.person.name}}</td>
					<td>{{oi.product.name}}</td>
					<td>{{oi.product.category.name}}</td>
					<td>{{oi.product.availableQuantity}}</td>
					<td>{{oi.selectedQuantity}}</td>
					<td><span>$</span>{{oi.orderItemPrice}}</td>
					<td>{{oi.forder.orderedDate}}</td>
					<td>{{oi.status}}</td>
					<td><button ng-disabled="{{oi.status=='Delivered'}}" ng-model="val"
					        class="btn btn-default"
							ng-click="changeStatus(oi)">
							<span class="glyphicon glyphicon-shopping-edit"></span> 
							Change Status
						</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<dir-pagination-controls max-size="5" direction-links="true"
				boundary-links="true" on-page-change="getOrders(newPageNumber)">
			</dir-pagination-controls>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>