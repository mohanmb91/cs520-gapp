<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Applications</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body class="container-fluid">

<div class="container">
<div class="jumbotron">
<h1>GRADUATE ADMISSION</h1>      
</div>
  <div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">Manage departments <span><a href="logout.html"> Log Out</a></span></div>
  <div class="panel-body">
  <table class = "table">
			<tr>
				<th>Department Name</th>
				<th>Number of Programs</th>
				<th>Operation</th>
				<th></th>
				<th></th>
			</tr>

			<c:forEach items="${departments}" var="eachDepartment">
				<tr>
					<td>${eachDepartment.name}</td>
					<td>${eachDepartment.programs.size() }</td>
					<td><a href="EditDepartment.html?deptId=${eachDepartment.id }">Edit</a></td>
					<td><a href="ViewDepartment.html?deptId=${eachDepartment.id }">View/ Add</a></td>
					<td><a href="DeleteDepartment.html?deptId=${eachDepartment.id }">Delete</a></td>
				</tr>
			</c:forEach>
			<tr>
			<td colspan="5">
			<a href="AddNewDepartment.html?deptId=${deptId}">
			<span class="glyphicon glyphicon-plus-sign"></span><span> Add New Department</span></a>
			</td>
			</tr>
		</table>
  </div>
  </div>
  </div>
</body>
</html>