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
<c:set value="${department.id }" var="deptId" scope="session"></c:set>
  <div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">Programs of ${department.name} <span style="float:right;"><a href="managedepartments.html">Manage Departments</a> || <a href="logout.html"> Log Out</a></span></div>
  <div class="panel-body">
  <table class="table">
			<tr>
				<th>Programs</th>
				<th colspan=2>Operation</th>
			</tr>
			<c:forEach items="${department.programs}" var="eachProgram">
				<tr>
					<td>${eachProgram.name }</td>
					<td><a
						href="EditProgram.html?programId=${eachProgram.id }&deptId=${deptId}">
							Edit </a></td>
					<td><a
						href="DeleteProgram.html?programId=${eachProgram.id }&deptId=${deptId}">
							Delete </a></td>
							</tr>
			</c:forEach>
			<tr>
			<td colspan="3"><a href="AddNewProgram.html?deptId=${deptId}"><span class="glyphicon glyphicon-plus-sign"></span><span> Add New Program</span></a></td>
			</tr>
			
		</table>
		<h4>Department Requirements</h4>
		<table class="table">
		<tr>
			<td>
				<ul>
				<c:forEach items="${department.deptRequirements}" var= "eachDeptRequirements">
				<li>${eachDeptRequirements.requiredFeild }<span><a href="EditFeild.html?requirementId=${eachDeptRequirements.id }&deptId=${deptId}"> Edit</a></span><span> || <a href="deleteFeild.html?requirementId=${eachDeptRequirements.id }&deptId=${deptId}"> delete</a></span>
				<ul>
				<li>
				Expected format(${eachDeptRequirements.feildType})
				</li>
				</ul>
				</li>
				</c:forEach>
				</ul> 
 			</td>
			</tr>
			 <tr>
	   		 <td><a href="AddNewRequirement.html?deptId=${deptId}"><span class="glyphicon glyphicon-plus-sign"></span><span> Add New Requirement</span></a></td>
	   		</tr>
		</table>
  </div>
  </div>
  </div>
</body>
</html>