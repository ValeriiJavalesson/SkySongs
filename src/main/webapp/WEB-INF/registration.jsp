<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Registration page</title>
<link rel="icon" type="image/x-icon" href="images/favicon.ico">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css"
	rel="stylesheet">
<link rel="stylesheet" href="style/css/registration.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>	
	<div class="container d-flex flex-column align-items-center">
		<h1 class="m-3"></h1>

		<form:form method='POST' action="registration"
			modelAttribute="userForm" enctype="multipart/form-data" id="userform"
			class="container">
			<div class="justify-content-center row">
				<div class="m-3 col-5">
					<div class="mb-3">
						<div class="d-flex justify-content-between">
							<label for="inputFirstName" class="form-label">Ім’я</label>
							<div class="errorMessage text-danger" hidden='true'></div>
						</div>
						<input type="text" class="form-control" id="inputFirstName"
							name="firstname">
					</div>
					<div class="mb-3">
						<div class="d-flex justify-content-between">
							<label for="inputLastName" class="form-label">Прізвище</label>
							<div class="errorMessage text-danger" hidden='true'></div>
						</div>
						<input type="text" class="form-control" id="inputLastName"
							name="lastname">
					</div>
					<div class="mb-3">
						<div class="d-flex justify-content-between">
							<label for="inputEmail" class="form-label">Логін-email</label>
							<div class="errorMessage text-danger" hidden='true'>Invalid email</div>
							<div class="isPresent text-danger" hidden='true'>Email is already registered</div>
						</div>
						<input type="text" class="form-control" id="inputEmail"
							aria-describedby="emailHelp" name="email">
					</div>
					<div class="mb-3">
						<div class="d-flex justify-content-between">
							<label for="inputPassword" class="form-label">Пароль</label>
							<div class="errorMessage text-danger" hidden='true'>Password must contain at least 6 characters</div>
						</div>
						<input type="password" class="form-control" id="inputPassword"
							name="password">
					</div>
					<button name="submit" type="button" value="submit"
						class="btn btn-primary" onclick='checkForm()'>Зареєструватися</button>
				</div>
			</div>

		</form:form>
		<a href="login" type="button" class="btn btn-light border border-1 m-5">На
			сторінку входу</a>
	</div>

	<script src="js/registration.js"></script>
</body>
</html>