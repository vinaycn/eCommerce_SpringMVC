<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<tiles:insertAttribute name="header" />
			</div>
		</div>
		<div class="row">
			<div class="col-sm-8">
			    <tiles:insertAttribute name="body" />
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</div>
</body>
</html>