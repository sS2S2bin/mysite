<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  

<!DOCTYPE html>
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
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="3">글보기</th>
					</tr>
						<tr>
							<td class="label">제목</td>
							<td>${board.title }</td>
							<td style="text-align:right;"> 조회수 ${board.hit }</td>
						</tr>
						<tr>
							<td class="label">작성자</td>
							<td>${board.writer }</td>
							<td style="text-align:right;"> 날짜 ${board.regDate }</td>
						</tr>
						<tr>
							<td class="label">내용</td>
						<td>
							<div class="view-content">${board.content}</div>
						</td>
						</tr>

				</table>
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board">글목록</a>
					<c:choose >
						<c:when test="${empty authUser }">
							<a style="text-align:right;">글수정을 원하신다면 로그인 해주세요.</a>
						</c:when>
						<c:otherwise >
							<c:if test="${authUser.no==board.userNo }">
								<a href="${pageContext.request.contextPath }/board/modifyform/${board.no}/${authUser.no}">글수정</a>
							</c:if>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="bottom">	
				<c:if test="${ not empty authUser }">
					<a href="${pageContext.request.contextPath }/board/writeform/${board.no}/reply">답글 달기</a>			
				</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>

<c:if test='${param.modifyaccess == "fail" }'>
	<script>alert("수정 권한이 없습니다. 다시 로그인 해주세요.")</script>
</c:if>