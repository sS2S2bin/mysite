<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  


<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/statics/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.request.contextPath }/board">
					<c:choose>
						<c:when test="${param.reply == 'FALSE' }">
							<input type = "hidden" name = "a" value="write">
							<c:set var="writeORreply" value="글쓰기"></c:set>
						</c:when>
						<c:otherwise>
							<input type = "hidden" name = "a" value="reply">
							<input type = "hidden" name = "bno" value="${param.bno }">
							<c:set var="writeORreply" value="답글 달기"></c:set>
						</c:otherwise>
					</c:choose>
					<input type = "hidden" name = "writer" value="${authUser.no }">					
					<table class="tbl-ex">
						<tr>
							<th colspan="2">${writeORreply}</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="content"></textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board">취소</a>
						<c:choose>
							<c:when test="${param.reply == 'FALSE' }">
								<input type="submit" value="등록">
							</c:when>
							<c:otherwise>
								<input type="submit" value="답글 등록">
							</c:otherwise>
						</c:choose>
					</div>
				</form>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>