<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="retailerBids">
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
<spring:url value="/resources/js/retailerBids.js" var="retjs" />
<script src="${retjs}"></script>
<spring:url value="/resources/js/dirPagination.js" var="dirjs" />
<script src="${dirjs}"></script>
</head>
<body>
	<div class="container" ng-controller="retailerBidController"
		data-ng-init="getBids(1)">
		<div class="row">
			<h4>Your Crop Bids List</h4>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Crop Name</th>
						<th>Added Quantity</th>
						<th>Expected Price</th>
						<th>Your Bid Price</th>
						<th>Bid Date</th>
						<th>Status</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr dir-paginate="bid in bids|itemsPerPage:8"
						total-items="totalCount">
						<td>{{bid.crop.name}}</td>
						<td>{{bid.crop.quantity}}</td>
						<td>{{bid.crop.expectedPrice}}</td>
						<td>{{bid.price}}</td>
						<td>{{bid.createdOn}}</td>
						<td ng-show="{{bid.status=='Accept'}}" style="color: green">{{bid.status}}</td>
						<td ng-show="{{bid.status=='Reject'}}" style="color: Red">{{bid.status}}</td>
						<td ng-show="{{bid.status=='Pending'}}" style="color: blue">{{bid.status}}</td>
						<td><button type="button" class="btn btn-success"
								data-toggle="modal" data-target="#farmerDetails"
								ng-click="farmerDetails(bid)">
								view Farmer Details
							</button></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<dir-pagination-controls max-size="5" direction-links="true"
					boundary-links="true" on-page-change="getBids(newPageNumber)">
				</dir-pagination-controls>
			</div>
		</div>
		<div class="modal fade" id="farmerDetails" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close" ng-click="clearFields()">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Farmer Details</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" class="form-control" ng-model="id" name="id"
							readonly="readonly">
						<fieldset class="form-group">
							<label for="name">Crop Posted By</label> <input type="text"
								class="form-control" ng-model="name" name="name"
								readonly="readonly">
						</fieldset>
						<fieldset class="form-group">
							<label for="name">Email Id</label> <input type="text"
								class="form-control" name="email" ng-model="emailId"
								readonly="readonly">
						</fieldset>
						<fieldset class="form-group">
							<label for="name">Address</label> <input type="text"
								class="form-control" ng-model="st" name="price"
								readonly="readonly">
						</fieldset>
						<fieldset class="form-group">
							<label for="name">City</label> <input type="text"
								class="form-control" ng-model="city" name="price2"
								readonly="readonly">
						</fieldset>
						<fieldset class="form-group">
							<label for="name">State</label> <input type="text"
								class="form-control" ng-model="state" name="price3"
								readonly="readonly">
						</fieldset>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
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