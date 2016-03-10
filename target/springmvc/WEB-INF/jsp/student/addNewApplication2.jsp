<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Applications</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  
<script type="text/javascript" src="../javascript/jquery-2.1.1.min.js"></script>
</head>
<body class="container-fluid">

<div class="container">
<div class="jumbotron">
<h1>GRADUATE ADMISSION</h1>      
</div>
<div class="panel panel-default">
	<!-- application academics -->
<div class="panel-heading">new application has been created for ${application.userName }!! kindly add and save the info<span style="float:right;"><a href = "/springmvc/logout.html"> Log Out</a>
</span></div>
<div class="panel-body">
<!-- application basic info -->
<div class="panel panel-default">
	<div class="panel panel-default">
			<div class = "panel-heading">Student Basic Info<span style="float:right;"><a href = "editBasicInfo.html?applicationId=${application.id}"> Edit Basic Info</a></span></div>
			<div class = "panel-body">
				<table class="table">
				<tr>
				<th>User Name</th>
				<th>Last Name</th>
				<th>Email Id</th>
				<th>Cin</th>
				<th>Phone Number</th>
				<th>Gender</th>
				<th>DOB</th>
				<th>Citizenship</th>
				</tr>
				<tr>
				<td><c:choose><c:when test="${not empty application.userName}">${application.userName}</c:when><c:otherwise>N/A</c:otherwise></c:choose> </td>
				<td><c:choose><c:when test="${not empty application.last_name}">${application.last_name}</c:when><c:otherwise>N/A</c:otherwise></c:choose> </td>
				<td><c:choose><c:when test="${not empty application.emailId}">${application.emailId}</c:when><c:otherwise>N/A</c:otherwise></c:choose> </td>
				<td><c:choose><c:when test="${not empty application.cin}">${application.cin}</c:when><c:otherwise>N/A</c:otherwise></c:choose> </td>
				<td><c:choose><c:when test="${not empty application.phoneNumber}">${application.phoneNumber}</c:when><c:otherwise>N/A</c:otherwise></c:choose> </td>
				<td><c:choose><c:when test="${not empty application.gender}">${application.gender}</c:when><c:otherwise>N/A</c:otherwise></c:choose> </td>
				<td><c:choose><c:when test="${not empty application.dateOfBirth}">${application.dateOfBirth}</c:when><c:otherwise>N/A</c:otherwise></c:choose> </td>
				<td><c:choose><c:when test="${not empty application.citizenship}">${application.citizenship}</c:when><c:otherwise>N/A</c:otherwise></c:choose> </td>
				</tr>
				</table>
			</div>
		</div>	
	
	<div class = "panel-heading">Student Educational Background</div>
			<div class = "panel-body">
			<table class = "table">
			<tr>     
				<th>Degree</th>     
				<th>College</th>
				<th>FromDate</th>
				<th>ToDate</th>
				<th>DegreeEarned</th>
				<th>Major</th>
				<th>operations</th>
			</tr>
			<c:choose>
			<c:when test="${ empty application.educationBackgrounds }">
			<tr>
			<td colspan="7">educational background not found. kindly add</td>
			</tr>
			</c:when>
			<c:otherwise>
			<c:set scope="session" var="applicationId" value="${application.id }"></c:set>
			<c:forEach items="${application.educationBackgrounds }" var="eachEducationalBackground">
			<tr>
			<td>${eachEducationalBackground.degree }</td>
			<td>${eachEducationalBackground.college }</td>
			<td>${eachEducationalBackground.fromDate }</td>
			<td>${eachEducationalBackground.toDate }</td>
			<td>${eachEducationalBackground.degreeEarned }</td>
			<td>${eachEducationalBackground.major }</td>
			<td><a href="removeEducationBackground.html?educationalBG=${eachEducationalBackground.id}&applicationId=${applicationId}">Remove</a></td>
			</tr>
			</c:forEach>
			</c:otherwise>
			</c:choose>
			</table>
			<a href="addNewEducationalBackground.html?applicationId=${application.id}">
			<span class="glyphicon glyphicon-plus-sign"></span><span> Add New Educational Background</span></a>
			</div>
</div>
<!-- application academics -->
<div class="panel panel-default">
		<div class = "panel-heading">Student Academics<span style="float:right;"><a href = "addNewAcademics.html?applicationId=${application.id}">Add or edit academics here Info</a></span></div>
			<div class = "panel-body">
			<table class = "table">
			<tr>     
				<th>GRE</th>     
				<th>GPA</th>
				<th>TOFEL</th>
				<th>Download Uploaded File</th>
				<th>Operation</th>
			</tr>
			<c:choose>
			<c:when test="${ empty application.academics }">
			<tr>
			<td colspan="5">Student has not yet filled Academics details. kindly update</td>
			</tr>
			</c:when>
			<c:otherwise>
			<c:set scope="session" var="applicationId" value="${application.id }"></c:set>
			<tr>
			<td>${application.academics.greScore }</td>
			<td>${application.academics.gpa }</td>
			<td>${application.academics.tofelScore }</td>
			<td>${application.academics.fileLocationTranscript }</td>
			<td><c:if test="${ application.academics.fileLocationTranscript ne null and application.academics.fileLocationTranscript != ''}"><a href="downloadFile.html?fileName=${application.id }_${application.academics.fileLocationTranscript}">View File</a></c:if></td>
			</tr>
			</c:otherwise>
			</c:choose>
			</table>
			</div>
		</div>
<a href="studentHome.html?userId=${application.student.user.id }" style="text-decoration: none;" class="btn-primary btn-block text-center" role="button">save & go to student Home</a>
</div>
</div>
</div>
</body>
</html>