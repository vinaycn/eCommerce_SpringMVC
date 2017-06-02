<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap Core CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">

<spring:url value="/resources/js/registration.js" var="regjs" />
<script src="${regjs}"></script>
</head>
<body ng-app="register">
	<div class="container-fluid">
		<div class="row">
			<c:if test="${requestScope.success!=null}">
				<div class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>${requestScope.success}</strong>
				</div>
			</c:if>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6" ng-controller="registration">
				<form:form action="registration.htm" method="post" id="fileForm"
					modelAttribute="person" role="form">
					<fieldset>
						<legend class="text-center">
							Valid information is required to register. <span class="req"><small>
									required *</small></span>
						</legend>

						<div class="form-group">
							<label for="firstname"><span class="req">* </span> Name:
							</label> <input class="form-control" type="text" name="name" id="txt"
								 />
							<div class="status">
								<form:errors path="name" style="color:red"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<label for="email"><span class="req">* </span> Email
								Address: </label> <input class="form-control"  type="text"
								name="emailId" id="email" />
							<div class="status">
								<form:errors path="emailId" style="color:red"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<label for="userAccount.role"> <span class="req">*</span>I
								am:
							</label> <select name="userAccount.role" class="form-control">
								<option>Farmer</option>
								<option>Supplier</option>
								<option>Retailer</option>
							</select>
						</div>
						<div class="form-group">
							<label for="address.zipcode"><span class="req">* </span>
								Zip Code: </label> <input class="form-control"  type="text"
								name="address.zipCode" ng-model="zipcode"
								ng-change="findAddress(zipcode)" />
							<div class="status">
								<form:errors path="address.zipCode" style="color:red"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<label for="address.streetName"><span class="req">*
							</span> Street Name: </label> <input class="form-control"  type="text"
								name="address.streetName" />
							<div class="status">
								<form:errors path="address.streetName" style="color:red"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<label for="address.city"><span class="req">* </span>
								City: </label> <input class="form-control"  type="text"
								name="address.city" ng-model="city" readonly="readonly" />
							<div class="status">
								<form:errors path="address.city" style="color:red"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<label for="address.state"><span class="req">* </span>
								State: </label> <input class="form-control"  type="text"
								name="address.state" ng-model="state" readonly="readonly" />
							<div class="status">
								<form:errors path="address.state" style="color:red"></form:errors>
							</div>
						</div>

						<div class="form-group">
							<label for="userAccount.username"><span class="req">*
							</span> User name: <small>This will be your login user name</small> </label> <input
								class="form-control" type="text" name="userAccount.username"
								id="txt" placeholder="minimum 6 letters" ng-model="username"
								ng-change="checkUserName(username)" />
							<div id="errLast">
								<form:errors path="userAccount.username" style="color:red"></form:errors>
								<span ng-show="uExists" style="color: red">Someone
									already have this UserName!</span>
							</div>
						</div>

						<div class="form-group">
							<label for="password"><span class="req">* </span>
								Password: </label> <input  name="userAccount.password"
								type="password" class="form-control inputpass" minlength="4"
								maxlength="16" id="pass1" />
								<form:errors path="userAccount.password" style="color:red"></form:errors>
							</p>

							<label for="password"><span class="req">* </span>
								Password Confirm: </label> <input  name="password"
								type="password" class="form-control inputpass" minlength="4"
								maxlength="16" placeholder="Enter again to validate" id="pass2"
								onkeyup="checkPass(); return false;" /> <span
								id="confirmMessage" class="confirmMessage"></span>
						</div>
						
						<div class="form-group">
							<input class="btn btn-success" type="submit" name="submit_reg"
								value="Register" id="reg">
						</div>
						<a href="login.htm">Click Here To Sign In</a>
						


					</fieldset>
				</form:form>
				<!-- ends register form -->

				<script type="text/javascript">
					function checkPass() {
						//Store the password field objects into variables ...
						var pass1 = document.getElementById('pass1');
						var pass2 = document.getElementById('pass2');
						//Store the Confimation Message Object ...
						var message = document.getElementById('confirmMessage');
						//Set the colors we will be using ...
						var goodColor = "#66cc66";
						var badColor = "#ff6666";
						//Compare the values in the password field 
						//and the confirmation field
						if (pass1.value == pass2.value) {
							//The passwords match. 
							//Set the color to the good color and inform
							//the user that they have entered the correct password 
							pass2.style.backgroundColor = goodColor;
							message.style.color = goodColor;
							message.innerHTML = "Passwords Match"
						    document.getElementById('reg').disabled=false;
						} else {
							//The passwords do not match.
							//Set the color to the bad color and
							//notify the user.
							pass2.style.backgroundColor = badColor;
							message.style.color = badColor;
							message.innerHTML = "Passwords Do Not Match!"
							document.getElementById('reg').disabled=true;
						}
					}
				</script>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>