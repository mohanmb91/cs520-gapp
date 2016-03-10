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
<div class="panel-heading">Student Academics <span><a href = "/springmvc/logout.html"> Log Out</a>
</span></div>
<div class="panel-body">

<form:form class="form" modelAttribute="studentAcademics" enctype="multipart/form-data" action="addNewAcademics.html?applicationId=${applicationId }" method="post">
	<div class="form-group">
		  <label for="greScore">GRE SCORE:</label>
		  <form:input name="greScore" path="greScore" type="number" class = "form-control" placeholder="greScore" required="required"/>
	</div>
	<div class="form-group">
		  <label for="tofelScore">TOFEL SCORE:</label>
		  <form:input name="tofelScore" path="tofelScore" type="number" class = "form-control" placeholder="tofelScore" required="required"/>
	</div>
	<div class="form-group">
		  <label for="gpa">GPA:</label>
		  <form:input name="gpa" path="gpa" type="number" step="0.1" class = "form-control" maxlength="4" placeholder="gpa max 4.0" required="required"/>
	</div>
	<div class="form-group">
		  <label for="greScore">Transcript Upload:</label>
		  <c:if test="${ not empty studentAcademics.fileLocationTranscript }">
		  <br/>
		  <label>you have already uploaded ${studentAcademics.fileLocationTranscript }</label>
		  <label>if you want to upload new file upload here</label>
		  </c:if>
		  <input type="file" name="file0"/>
	</div>
	<button class="btn-primary btn-block" type="submit">Save Additional Requirements</button>
	</form:form>


</div>
</div>
</div>
</body>
</html>