<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dictionary List</title>

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

    <style>
        a{
            color: white;
        }
        a:hover {
            color: white;
            text-decoration: none;
        }
    </style>
</head>
<body>
<h4 class="p-3 d-inline-block">Dictionary List</h4>
			<button type="button" class="btn btn-primary">
				<a href="/addDictionary">Add Dictionary</a>
			</button>
	<button type="button" class="btn btn-success">
		<a href="/openDictionary/${dict}">Back</a>
	</button>
	<form:form action="/findWords" method="get" modelAttribute="newWord">
				  <form:hidden path="idDict" class="form-control input-sm" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3" for="keyWord">Key or value</label>
							<div class="col-md-6">
								<form:input type="text" path="keyWord" id="keyWord" class="form-control input-sm" required="required" maxlength="32"/>
							</div>
						</div>
					</div>
					<div class="row p-2">
						<div class="col-md-2">
							<button type="submit" value="Register" class="btn btn-success">Find</button>
						</div>
					</div>
				</form:form>
		<form:form>
			<table class="table table-borded">
				<tr>
					<th>Id</th>
					<th>Key</th>
					<th>Value</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				
				<c:forEach var="wordNew" items="${finds}">
					<tr>
						<td>${wordNew.id}</td>
						<td>${wordNew.keyWord}</td>
						<td>${wordNew.valueWord}</td>
						<td>
							<button type="button" class="btn btn-success">
								<a href="/editWord/${wordNew.id}">Edit</a>
							</button>
						</td>
						<td>
							<button type="button" class="btn btn-danger">
								<a href="/deleteWord/${wordNew.id}">Delete</a>
							</button>
						</td>
					</tr>
				</c:forEach>
				
			</table>
		</form:form>
</body>
</html>