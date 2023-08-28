<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Dictionary</title>

	<link rel="stylesheet"
       	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <script
      	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script
     	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script
        src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    
</head>
<body>
	<div class="container">
		<h1 class="p-3">Add new dictionary</h1>
		<form:form action="/editSaveDictionary" method="post" modelAttribute="dict">
		  <div class="row">
                    	<div class="form-group col-md-12">
                    		<div class="col-md-6">
                    			<form:hidden path="id" class="form-control input-sm" />
                    		</div>
                    	</div>
                    </div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3" for="name">Name</label>
					<div class="col-md-6">
						<form:input type="text" path="name" id="name" class="form-control input-sm" required="required" value="${dict.name}"/>
					</div>
				</div>
			</div>
			
			<div class="row p-2">
				<div class="col-md-2">
					<button type="submit" value="Register" class="btn btn-success">Save</button>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>