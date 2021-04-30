<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body style="background-color: black">

<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #888585">
			
			<ul class="navbar-nav">
				<li ><a style="font-size: 25px;font-weight:500;color:white;height:60px;height:50px;width:90px;background-color:linearimage(to right,black,white);" href="<%=request.getContextPath()%>/list"
					class="nav-link">Users</a></li>
			</ul>
		</nav>
	</header>
<br>

<div class="container col-md-5">
		<div class="card" style="height: 600px;">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${user != null}">
            			Edit User
            		</c:if>
						<c:if test="${user == null}">
            			Add New User
            		</c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>User Id</label> <input type="text"
						value="<c:out value='${user.id}' />" class="form-control"
						name="id" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>User Name</label> <input type="text"
						value="<c:out value='${user.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

		

				<fieldset class="form-group">
					<label>User address</label> <input type="text"
						value="<c:out value='${user.address}' />" class="form-control"
						name="address">
				</fieldset>

				<button type="submit" class="btn btn-success container col-md-5" style=" margin-left: 8rem;" >Save</button>
				</form>
			</div>
		</div>
	</div>
	
	
	<br>

</body>
</html>