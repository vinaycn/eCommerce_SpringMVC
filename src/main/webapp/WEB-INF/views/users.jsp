<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
<spring:url value="/resources/js/admin.js" var="retJs" />
<script src="${retJs}"></script>
<spring:url value="/resources/js/dirPagination.js" var="dirjs" />
<script src="${dirjs}"></script>
</head>
<body ng-app="admin">
	<div id="page-wrapper" ng-controller="registeredUsers">

		<div class="container-fluid">

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
									<th>Serial Number</th>
									<th>Name</th>
									<th>EmailId</th>
									<th>Role</th>
									<th></th>
								</tr>
							</thead>
							<tbody ng-repeat="person in persons">
								<tr>
									<td>{{person.personId}}</td>
									<td>{{person.name}}</td>
									<td>{{perosn.emailId}}</td>
									<td>{{person.userAccount.role}}</td>
									<td><button type="submit"
											ng-click="activate(person.personId)" class="btn btn-default">Activate</button></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>