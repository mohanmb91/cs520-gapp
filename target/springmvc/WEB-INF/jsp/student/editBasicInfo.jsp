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
<div class="panel-heading">Edit Student Basic Info <span style="float:right;">Welcome ${application.userName }<a href = "/springmvc/logout.html"> Log Out</a>
</span></div>
<div class="panel-body">

 <form:form class="form" modelAttribute="application">
	  <div class="form-group">
		  <label for="userName">User Name</label>
		  <form:input path="userName" type="text" class="form-control" name="userName" value="${student.userName}" placeholder="userName" required="required"/>
	   </div>
	   <div class="form-group">
		  <label for="emailId">Email address</label>
		  <form:input path="emailId" type="email" class="form-control" name="emailId" value="${student.emailId}" placeholder="emailId" required="required"/>
	   </div>
	   <div class="form-group">
		  <label for="firstName">First Name</label>
		  <form:input path="firstName" type="text" class="form-control" name="firstName" value="${student.firstName}" placeholder="firstName" required="required"/>
	   </div>
	   <div class="form-group">
		  <label for="last_name">Last Name</label>
		  <form:input path="last_name" type="text" class="form-control" name="last_name" value="${student.last_name}" placeholder="last_name" required="required"/>
	   </div>
	   <div class="form-group">
		  <label for="cin">CIN</label>
		  <form:input path="cin" type="number" class="form-control" name="cin" value="${student.cin}" placeholder="cin" required="required"/>
	   </div>
	   <div class="form-group">
		  <label for="phoneNumber">Phone Number</label>
		  <form:input path="phoneNumber" type="number" class="form-control" name="phoneNumber" value="${student.phoneNumber}" placeholder="phoneNumber" required="required"/>
	   </div>
	   <div class="form-group">
		  <label for="gender">Gender</label>
		  <form:input path="gender" type="text" class="form-control" name="gender" value="${student.gender}" placeholder="gender" required="required"/>
	   </div>
	   <div class="form-group">
		  <label for="dateOfBirth">DOB</label>
		  <form:input path="dateOfBirth" type="text" class="form-control" name="dateOfBirth" value="${student.dateOfBirth}" placeholder="dateOfBirth" required="required"/>
	   </div>
	   <div class="form-group">
		  <label for="citizenship">Citizenship</label>
		  <form:input path="citizenship" type="text" class="form-control" name="citizenship" value="${student.citizenship}" placeholder="citizenship" required="required"/>
	   </div>
		<button class="btn-primary btn-block" type="submit">Save Basic Info</button>
		
</form:form>

</div>
</div>
</div>
</body>
</html>