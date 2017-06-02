<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="farmerCart">
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
<spring:url value="/resources/js/farmerCart.js" var="fcJs" />
<script src="${fcJs}"></script>
</head>
<body ng-controller="cartController" data-ng-init="getCartList()">
	<div class="container">
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne">
					<h4 class="panel-title">
						<a role="button" data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" aria-expanded="true"
							aria-controls="collapseOne">Your Cart Items</a>
					</h4>
				</div>
				<div id="collapseOne" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					<div class="panel-body">
						<div class="row">
							<div class="col-sm-12 col-md-10 col-md-offset-1">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>Product</th>
											<th>Quantity</th>
											<th class="text-center">Price</th>
											<th class="text-center">Total</th>
											<th></th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="cart in carts">
											<td class="col-sm-8 col-md-6">
												<div class="media">
													<div class="media-body">
														<h4 class="media-heading">{{cart.product.name}}</h4>
														<span>Status: </span><span class="text-success"><strong>In
																Stock</strong></span>
													</div>
												</div>
											</td>
											<td class="col-sm-1 col-md-1" style="text-align: center"><strong>{{cart.selectedQuantity}}</strong>
											</td>
											<td class="col-sm-1 col-md-1 text-center"><strong>$</strong>
												<strong>{{cart.product.pricePerUnit}}</strong></td>
											<td class="col-sm-1 col-md-1 text-center"><strong>$</strong><strong>{{cart.totalPrice}}</strong>
											</td>
											<td class="col-sm-1 col-md-1">
												<button type="button" class="btn btn-danger"
													ng-click="removeCart(cart)">
													<span class="glyphicon glyphicon-remove"></span> Remove
												</button>
											</td>
											<td class="col-sm-1 col-md-1">
												<button type="button" class="btn btn-success"
													data-toggle="modal" data-target="#cartItem"
													ng-click="cartItemDetails(cart)">
													<span class="glyphicon glyphicon-edit"></span> update
												</button>
											</td>
										</tr>
										<tr>
											<td> </td>
											<td> </td>
											<td><h3>Total</h3></td>
											<td class="text-center"><h3>

													<span>$</span><strong>{{cartPrice}}</strong>
												</h3></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td> </td>
											<td> </td>
											<td> </td>
											<td><a class="btn btn-default"
												href="viewAllProducts.htm" role="button"> <span
													class="glyphicon glyphicon-shopping-cart"></span> Continue
													Shopping
											</a></td>
											<td></td>
											<td></td>
										</tr>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- End of First -->
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingThree">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseThree"
							aria-expanded="false" aria-controls="collapseThree"> Pay Here
						</a>
					</h4>
				</div>
				<div id="collapseThree" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingThree">
					<div class="panel-body">
						<!-- Second Panel Body -->
						<form class="form-horizontal" role="form" method="post"
							action="placeOrder.htm">
							<fieldset>
								<legend>Payment</legend>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="card-holder-name">Name
										on Card</label>
									<div class="col-sm-9">
										<input type="text" class="form-control"
											name="card-holder-name" id="card-holder-name"
											placeholder="Card Holder's Name" required>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="card-number">Card
										Number</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" name="card-number"
											id="card-number" placeholder="Debit/Credit Card Number"
											minlength="16" required>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="expiry-month">Expiration
										Date</label>
									<div class="col-sm-9">
										<div class="row">
											<div class="col-xs-3">
												<select class="form-control col-sm-2" name="expiry-month"
													id="expiry-month" required>
													<option>Month</option>
													<option value="01">Jan (01)</option>
													<option value="02">Feb (02)</option>
													<option value="03">Mar (03)</option>
													<option value="04">Apr (04)</option>
													<option value="05">May (05)</option>
													<option value="06">June (06)</option>
													<option value="07">July (07)</option>
													<option value="08">Aug (08)</option>
													<option value="09">Sep (09)</option>
													<option value="10">Oct (10)</option>
													<option value="11">Nov (11)</option>
													<option value="12">Dec (12)</option>
												</select>
											</div>
											<div class="col-xs-3">
												<select class="form-control" name="expiry-year" required>
													<option value="13">2013</option>
													<option value="14">2014</option>
													<option value="15">2015</option>
													<option value="16">2016</option>
													<option value="17">2017</option>
													<option value="18">2018</option>
													<option value="19">2019</option>
													<option value="20">2020</option>
													<option value="21">2021</option>
													<option value="22">2022</option>
													<option value="23">2023</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="cvv">Card
										CVV</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" name="cvv" id="cvv"
											placeholder="Security Code" required>
									</div>
								</div>
								<c:choose>
									<c:when test="${fn:length(sessionScope.CartList.cartList) gt 0}">
										<div class="form-group">
											<div class="col-sm-offset-3 col-sm-9">
												<input type="submit" class="btn btn-success"
													value="Place Order" />
											</div>
										</div>
									</c:when>
									<c:when test="${fn:length(sessionScope.CartList.cartList) eq 0}">
										<div class="form-group">
											<div class="col-sm-offset-3 col-sm-9">
												<input type="submit" class="btn btn-success"
													value="Place Order" disabled="disabled" />
											</div>
										</div>
									</c:when>
								</c:choose>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="cartItem" tabindex="-1" role="dialog"
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
							<label for="name">Available Quantity </label> <input
								type="number" class="form-control" name="quantity"
								ng-model="quantity" readonly="readonly">
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
								ng-disabled="cartBtn" ng-click="updateCart()">update</button>
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