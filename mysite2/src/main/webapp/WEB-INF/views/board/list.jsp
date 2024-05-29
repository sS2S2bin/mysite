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
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${fn:length(boardlist)}" />
					<c:forEach items='${boardlist }' var='vo' varStatus='status'>
						<tr>
							<td>${count-status.index}</td>
							<td style="text-align:left; padding-left:${20*vo.depth }px">
								<a href="${pageContext.request.contextPath}/board?a=view&bno=${vo.no}">${vo.title }</a>
							</td>
							<td>${vo.writer }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							

							<td>
 								<c:choose>
									<c:when test='${empty authUser }'>
										<div class="bottom">
											<p>삭제를 원하시면 로그인해 주세요.</p>
										</div>	
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/board?bno=${vo.no }&whoisit=${authUser.no}" class="del">삭제</a>
									</c:otherwise>
								</c:choose>
							</td><!-- td는 비우지말고 td안에 들어가는걸 비워 -->
						</tr>
					
					</c:forEach>			
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				<div class="bottom">
				<c:choose>
					<c:when test='${empty authUser }'>
						<p >글쓰기</p>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/board?a=writeform" id="new-book">글쓰기</a>
					</c:otherwise>
				</c:choose>
				</div>	
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>