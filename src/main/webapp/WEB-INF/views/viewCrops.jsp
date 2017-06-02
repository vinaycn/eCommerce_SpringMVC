<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="farmer">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Farmer</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<spring:url value="/resources/css/font-awesome.min.css" var="fontCss" />
<link href="${fontCss}" rel="stylesheet">
<spring:url value="/resources/css/shop-homepage.css" var="shCss" />
<link href="${shCss}" rel="stylesheet">
<spring:url value="/resources/css/sb-admin.css" var="adminCss" />
<link href="${adminCss}" rel="stylesheet">
<spring:url value="/resources/css/font-awesome.min.css" var="famCss" />
<link href="${famCss}" rel="stylesheet">
<spring:url value="/resources/js/farmer.js" var="fJs" />
<script src="${fJs}"></script>
<spring:url value="/resources/js/dirPagination.js" var="dirjs" />
<script src="${dirjs}"></script>
</head>
<body>
<body ng-controller="farmerController" data-ng-init="getCrops(1)">

	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-sm-3 col-lg-3 col-md-3"
					dir-paginate="crop in crops|itemsPerPage:4"
					total-items="totalCount">
					<div class="thumbnail">
						<img src="${pageContext.request.contextPath}{{crop.cropPhotoName}}" class="img-responsive" style="min-height:50px;height:140px;" alt="">
						<div class="caption">
							<h4>
								<span class="text-primary">Name: </span>{{crop.name}}
							</h4>
							<h4>
								<span class="text-primary">Price: </span>{{crop.expectedPrice}}
							</h4>
							<h4>
								<span class="text-primary">Available Quantity: </span>{{crop.quantity}}
							</h4>
							<h4>
								<span class="text-primary">Category: </span>{{crop.cropCategory.name}}
							</h4>
						</div>
						<button type="submit" value="login" name="submit"
							class="btn btn-success btn-block" data-toggle="modal"
							data-target="#product" ng-click="bidDetails(crop.id)">View
							Bids</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<dir-pagination-controls max-size="5" direction-links="true"
				boundary-links="true" on-page-change="getCrops(newPageNumber)">
			</dir-pagination-controls>
		</div>
	</div>
	<div class="modal fade bs-example-modal-lg" id="product" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Bids For This Crops</h4>

					<div ng-if="ds">
						<div class="alert alert-success alert-dismissible" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>{{mes}}</strong>
						</div>
					</div>
					<div ng-if="df">
						<div class="alert alert-warning alert-dismissible" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>{{mes}}</strong>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Bid By</th>
								<th>Bidder Email Id</th>
								<th>Added Quantity</th>
								<th>Your Expected Price</th>
								<th>Bid Price</th>
								<th>Status</th>
								<th>update</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="bid in bids">
								<td>{{bid.retailer.person.name}}</td>
								<td>{{bid.retailer.person.emailId}}</td>
								<td>{{bid.crop.quantity}}</td>
								<td>{{bid.crop.expectedPrice}}</td>
								<td>{{bid.price}}</td>
								<td>{{bid.status}}</td>
								<td><button type="button" ng-disabled="{{bid.status=='Accept'||bid.status=='Reject'}}"
										 class="btn btn-default"
										ng-click="changeStatusToAccept(bid)">Accept</button>
										<button type="button" ng-disabled="{{bid.status=='Accept'||bid.status=='Reject'}}"
										 class="btn btn-default"
										ng-click="changeStatusToReject(bid)">Reject</button></td>	
							</tr>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</html>