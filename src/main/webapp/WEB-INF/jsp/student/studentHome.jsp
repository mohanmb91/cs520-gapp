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
	<div class="panel-heading">Welcome ${user.student.userName} !! <span style="float:right;"><a href = "/springmvc/logout.html"> Log Out</a>
	</span>
	</div>
	<div class="panel-body">
		<div class="panel panel-default">
		<div class = "panel-heading">List of Created Application</div>
			<div class = "panel-body">
				<div class="panel panel-default">
						<div class="panel-heading">Application of ${user.student.firstName } ${user.student.last_name }</div>
						<div class="panel-body">
						<table class="table">
						<tr>
						<th>First Name of the applicant</th>
						<th>last Name of the applicant</th>
						<th>Department/program applied to</th>
						<th>Term</th>
						<th>Operations</th>
						<th>submit</th>
						</tr>
						<c:forEach items="${user.student.applications}" var="eachApplication">
						<tr>
						<td>
						${eachApplication.firstName }
						</td>
						<td>
						${eachApplication.last_name }
						</td>
						<td>
						${eachApplication.program.department.name }/${eachApplication.program.name }
						</td>
						<td>
						${eachApplication.term }
						</td>
						<td>
						<c:choose>
						<c:when test="${eachApplication.isSubmitted eq false}">
						<a href="editApplication.html?applicationId=${eachApplication.id}">Edit</a> ||
						<a href="viewApplication.html?applicationId=${eachApplication.id}">View</a>
						</c:when>
						<c:otherwise>
						<a href="viewApplication.html?applicationId=${eachApplication.id}">View</a>
						</c:otherwise>
						</c:choose>
						</td>
						<td>
						<c:choose>
						<c:when test="${ eachApplication.isSubmitted eq false}">
							<c:choose>
							<c:when test="${ not empty eachApplication.educationBackgrounds  && not empty eachApplication.academics }">
							<a href="submitApplication.html?applicationId=${eachApplication.id}">submit</a>
							</c:when>
							<c:otherwise>
							you have to fill Educational BG and Academics to enable submit application
							</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
						application submitted
						</c:otherwise>
						</c:choose>
						
						</td>
						</tr>
						</c:forEach>
						</table>
						</div>
					</div>
				
			<a href="addNewApplication1.html?studentId=${user.student.id }">
			<span class="glyphicon glyphicon-plus-sign"></span><span> Add New Application</span></a>
			</div>
		</div>
	</div>
</div>


</div>
</body>
</html>