<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="retailer">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<spring:url value="/resources/css/font-awesome.min.css" var="fontCss" />
<link href="${fontCss}" rel="stylesheet">
<spring:url value="/resources/css/shop-homepage.css" var="shCss" />
<link href="${shCss}" rel="stylesheet">
<spring:url value="/resources/css/sb-admin.css" var="adminCss" />
<link href="${adminCss}" rel="stylesheet">
<spring:url value="/resources/js/retailer.js" var="retJs" />
<script src="${retJs}"></script>
<spring:url value="/resources/js/dirPagination.js" var="dirjs" />
<script src="${dirjs}"></script>
</head>
<body ng-controller="retailerController" data-ng-init="getCrops(1)">
<div class="row">
		<div class="col-md-6">
			<h2>List Of Available Crops</h2>
		</div>
	</div>
	<div class="row">
		<hr>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-sm-3 col-lg-3 col-md-3"
					dir-paginate="crop in crops|itemsPerPage:8"
					total-items="totalCount">
					<div class="thumbnail">
						<img src="${pageContext.request.contextPath}{{crop.cropPhotoName}}" class="img-responsive" style="min-height:50px;height:140px;" alt="">
						<div class="caption">
							<h4>
								<span class="text-primary">Name: </span>{{crop.name}}
							</h4>
							<h4>
								<span class="text-primary">Expected Price: </span>{{crop.expectedPrice}}
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
							data-target="#crop" ng-click="cropDetails(crop)">Bid for this Crop</button>
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
	<div class="modal fade" id="crop" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true" ng-click="clearFields()">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Update your Product</h4>

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
					<input type="hidden" class="form-control" ng-model="id" name="id"
						readonly="readonly">
					<fieldset class="form-group">
						<label for="name">Name </label> <input type="text"
							class="form-control" ng-model="name" name="name"
							readonly="readonly">
					</fieldset>
					<fieldset class="form-group">
						<label for="name">Quantity Available for Bid</label> <input type="text"
							class="form-control" name="quantity" ng-model="quantity" readonly="readonly">
					</fieldset>
					<fieldset class="form-group">
						<label for="name"> Expected Price </label> <input type="text"
							class="form-control" ng-model="price" name="price" readonly="readonly">
					</fieldset>
					<fieldset class="form-group">
						<label for="name">Your Price </label> <input type="number"
							class="form-control" ng-model="yourPrice" min="0" ng-change="updateBtn()" name="yprice">
					</fieldset>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" ng-click="clearFields()" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary"
							ng-click="placeBid()" ng-disabled="bidBtn">Add Bid</button>
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