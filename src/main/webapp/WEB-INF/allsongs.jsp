<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>Всі пісні</title>

<link rel="icon" type="image/x-icon" href="images/favicon.ico">
<link rel="stylesheet" type="text/css" href="style/css/allsongs.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main>
		<div class="main-content">
		<table>
			<thead>
			<tr>
				<th style="width: 10%">Номер в пісеннику</th>
				<th>Назва</th>
				<security:authorize access="isAuthenticated()">	
					<th></th>
					<th></th>
				</security:authorize> 
			</tr>
			</thead>
			<tbody>
				<c:forEach var="song" items="${songs}">
					<tr>						
						<td onclick="goToSong(${song.id})">${song.number}</td>
						<td onclick="goToSong(${song.id})">${song.title}</td>
						<security:authorize access="isAuthenticated()">		
							<td><button id="editSongButton" onclick="editSong(${song.id})">✎</button></td>
							<td><button id="deleteSongButton" onclick="deleteSong(${song.id})">X</button></td>
						</security:authorize> 
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
		</div>		
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="js/allsongs.js"></script>
</body>
</html>