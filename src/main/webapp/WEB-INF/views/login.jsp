<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FarmerAid Application Login Page</title>

<!-- Bootstrap Core CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
</head>
<body>
	<div class="container">
		<div class="row">
			<c:if test="${requestScope.error!=null}">
				<div class="alert alert-warning alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>${requestScope.error}</strong>
				</div>
			</c:if>
		</div>
		<div class="modal-body">
		<div class="row">
		<div class="col-xs-3">
		</div>
			<div class="col-xs-6">
					<div class="well">
						<form id="loginForm" action="access.htm" method="POST">
							<div class="form-group">
								<label for="username" class="control-label">Username</label> <input
									type="text" class="form-control" name="username" value=""
									required="" title="Please enter your username"
									placeholder="username"> <span class="help-block"></span>
							</div>
							<div class="form-group">
								<label for="password" class="control-label">Password</label> <input
									type="password" class="form-control" name="password"
									placeholder="password" value="" required=""
									title="Please enter your password"> <span
									class="help-block"></span>
							</div>
							<div id="loginErrorMsg" class="alert alert-error hide">Wrong
								username or password</div>
							<button type="submit" value="login" name="submit"
								class="btn btn-success btn-block">Login</button>
								<a href="signUp.htm">Click Here to Create Account</a>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Javascript -->
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>



</body>
</html>