<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Applications</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../javascript/jquery-2.1.1.min.js"></script>
</head>
<body class="container-fluid">

	<div class="container">
		<div class="jumbotron">
			<h1>GRADUATE ADMISSION</h1>
		</div>
		<div class="panel panel-default">
			<!-- application academics -->
			<div class="panel-heading">
				new application has been created for ${application.userName }!!
				kindly add and save the info<span style="float: right;"><a
					href="/springmvc/logout.html"> Log Out</a> </span>
			</div>
			<div class="panel-body">
				<form class="form" action="editApplication.html?applicationId=${applicationId }" enctype="multipart/form-data" method="post">
					<div class="form-group">
						<label for="department">Department Name:</label> <select
							id="department" class="form-control">
							<option value="${department.id }" disabled selected>${department.name }</option>
						</select>
					</div>
					<div class="form-group">
						<label for="program">Program Name:</label> <select id="program" name="program"
							class="form-control" required="required">
							<c:forEach items="${ programs}" var="eachProgram">
							<c:choose>
							<c:when test="${eachProgram.name eq selectedProgram}">
							<option value="${eachProgram.id }" selected>${eachProgram.name }</option>
							</c:when>
							<c:otherwise>
							<option value="${eachProgram.id }" >${eachProgram.name }</option>
							</c:otherwise>
							</c:choose>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="program">Term:</label> <select id="term" name="term"
							class="form-control" required="required">
							<c:choose>
							<c:when test="${term eq 'Fall 2016'}">
							<option class="form-control" value="Fall 2016" selected>Fall 2016</option>
							</c:when>
							<c:otherwise>
							<option class="form-control" value="Fall 2016" >Fall 2016</option>
							</c:otherwise>
							</c:choose>
							<c:choose>
							<c:when test="${term eq 'Spring 2017'}">
							<option class="form-control" value="Spring 2017" selected>Spring 2017</option>
							</c:when>
							<c:otherwise>
							<option class="form-control" value="Spring 2017">Spring 2017</option>
							</c:otherwise>
							</c:choose>
							<c:choose>
							<c:when test="${term eq 'Fall 2017'}">
							<option class="form-control" value="Fall 2017" selected>Fall 2017</option>
							</c:when>
							<c:otherwise>
							<option class="form-control" value="Fall 2017">Fall 2017</option>
							</c:otherwise>
							</c:choose>
						</select>
					</div>
					<div class="panel panel-default">
						<!-- Default panel contents -->
						<div class="panel-heading">Additional Requirements</div>
						<div class="panel-body" id="additionalRequirements">
							<c:forEach items="${ application.additionalDepartmentFeildValues}" var="eachFeildValue">
							<div class="form-group">
							<label for="${eachFeildValue.feildDetails.requiredFeild}">${ eachFeildValue.feildDetails.requiredFeild}:</label>
							<c:choose>
								<c:when test="${eachFeildValue.feildDetails.feildType eq 'Number'}">
									<c:if test="${eachFeildValue.feildDetails.required eq true}">
										<input type="text" name="${eachFeildValue.feildDetails.requiredFeild}" value="${eachFeildValue.feildValues }" required/>
									</c:if>
									<c:if test="${eachFeildValue.feildDetails.required eq false}">
										<input type="text" name="${eachFeildValue.feildDetails.requiredFeild}" value="${eachFeildValue.feildValues }" />
									</c:if>
								</c:when>
								<c:when test="${eachFeildValue.feildDetails.feildType eq 'Text'}">
									<c:if test="${eachFeildValue.feildDetails.required eq true}">
										<input type="text" name="${eachFeildValue.feildDetails.requiredFeild}" value="${eachFeildValue.feildValues }" required/>
									</c:if>
									<c:if test="${eachFeildValue.feildDetails.required eq false}">
										<input type="text" name="${eachFeildValue.feildDetails.requiredFeild}" value="${eachFeildValue.feildValues }" />
									</c:if>
								</c:when>
								<c:otherwise>
								<br/>${eachFeildValue.feildValues }
									<c:if test="${eachFeildValue.feildDetails.required eq true}">
										<input type="file" name="files[]" /><span>upload files to replace existing file</span>
									</c:if>
									<c:if test="${eachFeildValue.feildDetails.required eq false}">
										<input type="file" name="files[]"  /> <span>upload files to replace existing file</span>
									</c:if>
								</c:otherwise>
							</c:choose>
							</div>
							</c:forEach>
						</div>
					</div>
					<button class="btn-primary btn-block" type="submit">Save Application and continue</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>