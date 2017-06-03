<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap Core CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
<spring:url value="/resources/css/sb-admin.css" var="adminCss" />
<link href="${adminCss}" rel="stylesheet">
<spring:url value="/resources/js/admin.js" var="adminjs" />
<script src="${adminjs}"></script>
<spring:url value="/resources/js/dirPagination.js" var="dirjs" />
<script src="${dirjs}"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body ng-app="admin">
	<div id="wrapper" ng-controller="registeredUsers"
		data-ng-init="getPersonsList(1)">
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<ul class="nav navbar-right top-nav">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><i class="fa fa-user"></i>${name}<b
					class="caret"></b></a>
				<ul class="dropdown-menu">
					<li class="divider"></li>
					<li><a href="logout.htm"></i> Log Out</a></li>
				</ul></li>
		</ul>
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav side-nav">
				<li class="active" ng-click="getPersonsList()"><a><i
						class="fa fa-fw fa-registeredUsers"></i>View Registered Users</a></li>
				<li ng-click="getCategories()"><a><i
						class="fa fa-fw fa-category"></i>Add Product Category</a></li>
				<li ng-click="getCropCategories()"><a><i
						class="fa fa-fw fa-category"></i>Add Crop Category</a></li>
			</ul>
		</div>
		</nav>
		<div id="page-wrapper">
			<div class="container-fluid" ng-show="viewUsers">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Registered Users</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-registeredUsers"></i></li>
						</ol>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-6">
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Person Id</th>
										<th>Name</th>
										<th>EmailId</th>
										<th>Role</th>
										<th>View Address</th>
										<th>Account Status</th>
									</tr>
								</thead>
								<tbody dir-paginate="person in persons|itemsPerPage:5"
									pagination-id="person.personId" total-items="totalCount"
									current-page="current">
									<tr>
										<td>{{person.personId}}</td>
										<td>{{person.name}}</td>
										<td>{{person.emailId}}</td>
										<td>{{person.userAccount.role}}</td>
										<td><button type="button" class="btn btn-success"
												data-toggle="modal" data-target="#addressDetails"
												ng-click="personDetails(person)">view Address
												Details</button></td>
										<td ng-show="person.userAccount.status=='Disabled'"><button
												type="submit" ng-click="activate(person.personId,person)"
												ng-disabled="activated" class="btn btn-default">Activate</button></td>
										<td ng-show="person.userAccount.status=='active'"><span
											class="glyphicon glyphicon-ok" aria-hidden="true"></span></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="container-fluid" ng-show="viewCategory">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">List of Category</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-registeredUsers"></i></li>
						</ol>
					</div>
					<div class="col-lg-3">
						<div class="input-group" ng-form name="myForm1">
							<span class="error" ng-show="myForm1.input.$error.required">
								Required!</span> <span class="error"
								ng-show="myForm1.input.$error.pattern">Alphabets only!</span> <input
								type="text" ng-model="cName" class="form-control" name="input"
								ng-pattern="regex" placeholder="Enter Product Category Name.."
								required> <span class="input-group-btn">
								<button class="btn btn-default" ng-click="addCategory(cName)"
									type="button">Add!</button>
							</span>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-6">
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Category Id</th>
										<th>Category Name</th>
									</tr>
								</thead>
								<tbody ng-repeat="category in categories">
									<tr>
										<td>{{category.id}}</td>
										<td>{{category.name}}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>

			</div>
			<div class="container-fluid" ng-show="viewCropCategory">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">List of Crop Category</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-registeredUsers"></i></li>
						</ol>
					</div>
					<div class="col-lg-3">
						<div class="input-group" ng-form name="myForm">
							<span class="error" ng-show="myForm.inputc.$error.required">
								Required!</span> <span class="error"
								ng-show="myForm.inputc.$error.pattern">Alphabets only!</span><input
								type="text" name="inputc" ng-model="cropCategoryName"
								class="form-control" placeholder="Enter Crop Category Name.."
								required ng-pattern="regex"> <span
								class="input-group-btn">
								<button class="btn btn-default"
									ng-click="addCropCategory(cropCategoryName)" type="button">Add!</button>
							</span>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-6">
						<div class="table-responsive">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Crop Category Id</th>
										<th>Crop Category Name</th>
									</tr>
								</thead>
								<tbody ng-repeat="cc in cropCategories">

									<tr>
										<td>{{cc.id}}</td>
										<td>{{cc.name}}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>

			</div>

		</div>
		<div class="row" ng-show="viewUsers">
			<div class="col-lg-12">
				<dir-pagination-controls pagination-id="person.personId"
					max-size="5" direction-links="true" boundary-links="true"
					on-page-change="getPersonsList(newPageNumber)">
				</dir-pagination-controls>
			</div>
		</div>
		<div class="modal fade" id="addressDetails" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close" ng-click="clearFields()">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Address Details</h4>
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
							<label for="name">Zip Code</label> <input type="text"
								class="form-control" name="zip" ng-model="zip"
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
		<!-- jQuery -->
		<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>




	</div>
</body>
</html>