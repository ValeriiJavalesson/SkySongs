<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title></title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="style/css/header.css">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg mb-3 sticky-top ">
			<div class="container-fluid">
				<div>
					<div class="logo" onclick="goHome()" ></div>
				</div>
				<div id="qrcode" onclick="toggleQR()"></div>
				<div class="buttons_row">					
					<div id="menu_button">						
						<button id="openMenu_button" onclick="toggleMenuList()"></button>
					</div>
					<div class="collapse justify-content-end" id="navbarSupportedContent">
					<%-- 		<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">		--%>			
										
								<!-- <div class="logout_button">
									<div class="qr-code" onclick="toggleQR()">QR-КОД</div>
									
								</div> -->
							<security:authorize access="isAuthenticated()">		
								<div class="logout_button">
									<a href="<c:url value="/addsong?id=0" />" class="">Додати ♫</a>
								</div>		
								<div class="logout_button">
									<a href="<c:url value="/logout" />" class="">Вийти</a>
								</div>
								
							</security:authorize> 
							<security:authorize access="!isAuthenticated()">				
								<div class="logout_button">
									<a href="<c:url value="/login" />" class="">Авторизація</a>
								</div>
							</security:authorize> 
							
					</div>
				</div>
					
				<%-- </security:authorize>--%>
			</div>
		</nav>
	</header>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<script src="js/header.js"></script>
</body>
</html>