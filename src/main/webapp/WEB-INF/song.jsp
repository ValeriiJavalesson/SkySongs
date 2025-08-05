<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>${song.title}</title>

<link rel="icon" type="image/x-icon" href="images/favicon.ico">
<link rel="stylesheet" type="text/css" href="style/css/song.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main>
		<div class="container d-flex flex-column align-items-center">
			<input name="id" hidden="hidden" value="${song.id}">
			<div class="songNumber">${song.number}</div>
			<div class="songTitle">${song.title}</div>
			<%-- <div class="songText"><textarea readonly>${song.text}</textarea> </div> --%>
			<div id="fake_textarea" contenteditable="false">${song.text}</div>
		
		
		
		
		</div>		
	</main>
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="../js/song.js"></script>
</body>
</html>