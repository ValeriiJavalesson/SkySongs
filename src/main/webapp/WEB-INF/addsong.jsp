<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>Додати пісню</title>

<link rel="icon" type="image/x-icon" href="../images/favicon.ico">
<link rel="stylesheet" type="text/css" href="style/css/addsong.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main>
		<div class="new_song_form_wrap">		
				<div class="new_song_form" >
				<div><input name="newSongId" type="number" hidden="hidden" value="${song.id}"></div>
				<c:choose>				
					<c:when test="${song.id>0}"><div><input placeholder="Номер в пісен2" name="number" type="number" class="" value="${song.number}"> </div></c:when>
					<c:otherwise><div><input placeholder="Номер в пісенникуs" name="number" type="number" class="" value="${maxNumberSong.number + 1}"> </div></c:otherwise>
				</c:choose>
				
				<div><input placeholder="Назва пісні" name="title" type="text" class="" value="${song.title}"> </div>
				<%-- <div><textarea placeholder="Текст пісні" name="text" class="">${song.text}</textarea></div> --%>
				<div id="fake_textarea" contenteditable="true">${song.text}</div>
				<div><button onclick="saveSong()">Зберегти</button></div>	
			</div>
		
		</div>		
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="js/addsong.js"></script>
</body>
</html>