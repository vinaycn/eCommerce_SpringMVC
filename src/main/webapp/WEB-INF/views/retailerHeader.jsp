<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<spring:url value="/resources/css/font-awesome.min.css" var="fontCss" />
<link href="${fontCss}" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
</head>
<body>
	<div class="container">
		<ul class="nav navbar-right top-nav">
			<li><a href="viewAllCrops.htm">View All Crops</a></li>
			<li><a href="viewMyBids.htm">View My Bids</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"></i>${name} <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li class="divider"></li>
					<li><a href="logout.htm"></i> Log Out</a></li>
				</ul></li>
		</ul>
	</div>

	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>