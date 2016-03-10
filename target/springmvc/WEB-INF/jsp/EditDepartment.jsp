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
  <div class="panel-heading">Login</div>
  <div class="panel-body">
    
  <input type="hidden" name="deptId" value="${department.id}"/>
    
    <form class="form" method="post">
					<div class="form-group">
						<label for="departmentName">Program Name</label> <input type="text"
							class="form-control" name="departmentName"
							placeholder="Department Name" value="${department.name }" required />
					</div>
					<button class="btn-primary btn-block" type="submit">Add Department</button>
				</form>
  </div>
  </div>
  </div>

</body>
</html>