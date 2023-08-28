<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<div class="container">
	
		<div class="col-sm-8">
			<h4 class="p-3 d-inline-block">Dictionary List</h4>
			<button type="button" class="btn btn-primary">
				<a href="/addDictionary">Add Dictionary</a>
			</button>
			<button type="button" class="btn btn-success">
				<a href="/">Back</a>
			</button>
		</div>
		<h4>${dict.name}</h4>
		<div id="accordion">
		  <div class="card">
		    <div class="card-header" id="headingOne">
		      <h5 class="mb-0">
		        <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
		          Добавить слово
		        </button>
		      </h5>
		    </div>
		
		    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
		      <div class="card-body">
		        <form:form action="/createWord" method="post" modelAttribute="newWord">
				  <form:hidden path="idDict" class="form-control input-sm" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3" for="keyWord">Key</label>
							<div class="col-md-6">
								<form:input type="text" path="keyWord" id="keyWord" class="form-control input-sm" required="required" maxlength="32"/>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3" for="valueWord">Value</label>
							<div class="col-md-6">
								<form:input type="text" path="valueWord" id="valueWord" class="form-control input-sm" required="required" maxlength="32"/>
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
		    </div>
		  </div>
		  <div class="card">
		    <div class="card-header" id="headingTwo">
		      <h5 class="mb-0">
		        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
		          Удалить по ключу
		        </button>
		      </h5>
		    </div>
		    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
		      <div class="card-body">
		        <form:form action="/deleteWordByKey" method="post" modelAttribute="newWord">
				  <form:hidden path="idDict" class="form-control input-sm" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3" for="keyWord">Key</label>
							<div class="col-md-6">
								<form:input type="text" path="keyWord" id="keyWord" class="form-control input-sm" required="required" maxlength="32"/>
							</div>
						</div>
					</div>
					<div class="row p-2">
						<div class="col-md-2">
							<button type="submit" value="Register" class="btn btn-success">Delete</button>
						</div>
					</div>
				</form:form>
		      </div>
		    </div>
		  </div>
		  <div class="card">
		    <div class="card-header" id="headingThree">
		      <h5 class="mb-0">
		        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
		          Найти слово
		        </button>
		      </h5>
		    </div>
		    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
		      <div class="card-body">
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
		      </div>
		    </div>
		  </div>
		</div>
		<form:form>
			<table class="table table-borded">
				<tr>
					<th>Id</th>
					<th>Key</th>
					<th>Value</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				
				<c:forEach var="word" items="${words}">
					<tr>
						<td>${word.id}</td>
						<td>${word.keyWord}</td>
						<td>${word.valueWord}</td>
						<td>
							<button type="button" class="btn btn-success">
								<a href="/editWord/${word.id}">Edit</a>
							</button>
						</td>
						<td>
							<button type="button" class="btn btn-danger">
								<a href="/deleteWord/${word.id}">Delete</a>
							</button>
						</td>
					</tr>
				</c:forEach>
				
			</table>
		</form:form>

	</div>
</body>
</html>