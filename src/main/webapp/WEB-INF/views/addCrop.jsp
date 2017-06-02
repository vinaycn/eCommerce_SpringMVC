<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="addCrop">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<spring:url value="/resources/css/font-awesome.min.css" var="fontCss" />
<link href="${fontCss}" rel="stylesheet">
<spring:url value="/resources/css/sb-admin.css" var="adminCss" />
<link href="${adminCss}" rel="stylesheet">
<spring:url value="/resources/js/addCrop.js" var="acJs" />
<script src="${acJs}"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
</head>
<body ng-controller="addCropController" data-ng-init="getCartSize()">
	<div class="row">
		<c:if test="${requestScope.message!=null}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${requestScope.message}</strong>
			</div>
		</c:if>
		<c:if test="${requestScope.errorMessage!=null}">
			<div class="alert alert-warning alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${requestScope.errorMessage}</strong>
			</div>
		</c:if>
	</div>
	<div class="col-md-4"></div>

	<div class="col-md-4">
		<hr>
		<form:form action="addCrop.htm" modelAttribute="crop"
			method="post" enctype="multipart/form-data" name="crop">
			<fieldset class="form-group">
				<label for="name">Name </label> <input type="text"
					class="form-control" name="name"> 
				<form:errors path="name" style="color:red"></form:errors>
			</fieldset>
			<fieldset class="form-group">
				<label for="exampleInputPassword1">No Of Bags</label> <input
					type="number" name="quantity" min="0" class="form-control" required >
					<span class="error" ng-show="crop.quantity.$error.required">
					Required!</span>
					<form:errors path="quantity" style="color:red"></form:errors>
			</fieldset>
			<fieldset class="form-group">
				<label for="exampleInputPassword1">Expected Price</label> <input
					type="number" name="expectedPrice" min="0" class="form-control" required>
					<span class="error" ng-show="crop.expectedPrice.$error.required">
					Required!</span>
					<form:errors path="expectedPrice" style="color:red"></form:errors>
			</fieldset>
			<fieldset class="form-group">
				<label for="exampleInputPassword1">Crop Image</label> <input
					type="file" name="cropPhoto" class="form-control">
					<span>${pError}</span>
			</fieldset>
			<fieldset class="form-group">
				<label for="category">Crop Category </label> <select
					name="cropCategory.name" class="form-control">
					<c:forEach var="category" items="${requestScope.ccList}">
						<option><c:out value="${category.name}"></c:out></option>
					</c:forEach>
				</select>
			</fieldset>
			<button type="submit" class="btn btn-primary">Add</button>
		</form:form>
	</div>
	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>