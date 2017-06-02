<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="supplier">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<spring:url value="/resources/css/font-awesome.min.css" var="fontCss" />
<link href="${fontCss}" rel="stylesheet">
<spring:url value="/resources/css/shop-homepage.min.css" var="shCss" />
<link href="${shCss}" rel="stylesheet">
<spring:url value="/resources/css/form-mini.css" var="fmss" />
<link href="${fmss}" rel="stylesheet">
<spring:url value="/resources/css/sb-admin.css" var="adminCss" />
<link href="${adminCss}" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
</head>
<body ng-controller="supplierController">
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<ul class="nav navbar-right top-nav">
			<li><a href="" data-toggle="modal" data-target="#addProduct">Add
					a Product</a></li>
			<li><a href="#">View My Complaints</a></li>
			<li><a href="#">Contact</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><i class="fa fa-user"></i> John Smith <b
					class="caret"></b></a>
				<ul class="dropdown-menu">
					<li ng-click="getDetails()" data-toggle="modal"
						data-target="#getDetails"><a href="#"><i
							class="fa fa-fw fa-user"></i> Your Profile</a></li>
					<li class="divider"></li>
					<li><a href="#"><i class="fa fa-fw fa-power-off"></i> Log
							Out</a></li>
				</ul></li>
		</ul>
	</div>
	</nav>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<p class="lead">Shop Name</p>
				<div class="list-group">
					<a href="#" class="list-group-item">Category 1</a> <a href="#"
						class="list-group-item">Category 2</a> <a href="#"
						class="list-group-item">Category 3</a>
				</div>
			</div>
			<div class="col-md-9">
				<div class="row">
					<div class="col-sm-4 col-lg-4 col-md-4">
						<div class="thumbnail">
							<img src="http://placehold.it/320x150" alt="">
							<div class="caption">
								<h4 class="pull-right">$24.99</h4>
								<h4>
									<a href="#">First Product</a>
								</h4>
								<p>
									See more snippets like this online store item at <a
										target="_blank" href="http://www.bootsnipp.com">Bootsnipp
										- http://bootsnipp.com</a>.
								</p>
							</div>
						</div>
					</div>

					<div class="col-sm-4 col-lg-4 col-md-4">
						<div class="thumbnail">
							<img src="http://placehold.it/320x150" alt="">
							<div class="caption">
								<h4 class="pull-right">$64.99</h4>
								<h4>
									<a href="#">Second Product</a>
								</h4>
								<p>This is a short description. Lorem ipsum dolor sit amet,
									consectetur adipiscing elit.</p>
							</div>
						</div>
					</div>

					<div class="col-sm-4 col-lg-4 col-md-4">
						<div class="thumbnail">
							<img src="http://placehold.it/320x150" alt="">
							<div class="caption">
								<h4 class="pull-right">$74.99</h4>
								<h4>
									<a href="#">Third Product</a>
								</h4>
								<p>This is a short description. Lorem ipsum dolor sit amet,
									consectetur adipiscing elit.</p>
							</div>
						</div>
					</div>

					<div class="col-sm-4 col-lg-4 col-md-4">
						<div class="thumbnail">
							<img src="http://placehold.it/320x150" alt="">
							<div class="caption">
								<h4 class="pull-right">$84.99</h4>
								<h4>
									<a href="#">Fourth Product</a>
								</h4>
								<p>This is a short description. Lorem ipsum dolor sit amet,
									consectetur adipiscing elit.</p>
							</div>
						</div>
					</div>

					<div class="col-sm-4 col-lg-4 col-md-4">
						<div class="thumbnail">
							<img src="http://placehold.it/320x150" alt="">
							<div class="caption">
								<h4 class="pull-right">$94.99</h4>
								<h4>
									<a href="#">Fifth Product</a>
								</h4>
								<p>This is a short description. Lorem ipsum dolor sit amet,
									consectetur adipiscing elit.</p>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="addProduct" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add Product</h4>
				</div>
				<div class="modal-body">
					<div class="main-content">
						<div class="form-mini-container">
							<form class="form-mini" method="post" action="#">
								<div class="form-row">
									<input type="text" name="pname" placeholder="Product Name..">
								</div>
								<div class="form-row">
									<input type="number" min=0 name="tq"
										placeholder="Total Quantity..">
								</div>
								<div class="form-row">
									<label> <select name="dropdown">
											<option>Product Category</option>
											<option>Option One</option>
											<option>Option Two</option>
											<option>Option Three</option>
											<option>Option Four</option>
									</select>
									</label>
								</div>
								<div class="form-row">
									<input type="number" min=0 name="pq"
										placeholder="Enter Price Per quantity..">
								</div>
								<div class="form-row form-last-row">
									<button type="submit">Add Product</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="getDetails" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">your Details</h4>
				</div>
				<div class="modal-body">
					<form>
						<fieldset class="form-group">
							<label for="exampleInputEmail1">Name </label> <input type="text"
								class="form-control"> <small class="text-muted">We'll
								never share your email with anyone else.</small>
						</fieldset>
						<fieldset class="form-group">
							<label for="exampleInputPassword1">Email</label> <input
								type="text" class="form-control">
						</fieldset>
						<fieldset class="form-group">
							<label for="exampleInputPassword1">ZipCode</label> <input
								type="text" class="form-control">
						</fieldset>
						<fieldset class="form-group">
							<label for="exampleInputPassword1">Street Address</label> <input
								type="text" class="form-control">
						</fieldset>
						<fieldset class="form-group">
							<label for="exampleInputPassword1">City</label> <input
								type="text" class="form-control" readonly="readonly">
						</fieldset>
						<fieldset class="form-group">
							<label for="exampleInputPassword1">State</label> <input
								type="text" class="form-control" readonly="readonly">
						</fieldset>
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- /.container -->

	<div class="container">
		<hr>
		<!-- Footer -->
		<footer>
		<div class="row">
			<div class="col-lg-12">
				<p>Copyright &copy; Your Website 2014</p>
			</div>
		</div>
		</footer>
	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>
</html>