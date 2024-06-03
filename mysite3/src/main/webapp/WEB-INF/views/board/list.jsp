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
							<c:if test="${vo.depth>0 }">
								<img src="${pageContext.request.contextPath}/statics/images/reply.png">
							</c:if>
								<a href="${pageContext.request.contextPath}/board?a=view&bno=${vo.no}">${vo.title }</a>
							</td>
							<td>${vo.writer }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							
							<!-- 로그인 구분 : 삭제 버튼 -->
							<td>
 								<c:choose>
									<c:when test='${empty authUser }'>
										<div class="bottom">
											<p>삭제를 원하시면 로그인해 주세요.</p>
										</div>	
									</c:when>
									<c:when test='${authUser.no==vo.writerNo }'>
										<a href="${pageContext.request.contextPath}/board?a=delete&bno=${vo.no}&bywho=${authUser.no}" class="del">삭제</a>
									</c:when>
									<c:otherwise>
										<p></p>
									</c:otherwise>
								</c:choose>
							</td>
							
						</tr>
					
					</c:forEach>			
				</table>
				
				<!-- pager 추가 -->
				 <c:set var="endPage" value="${start+4 }" />				
				<div class="pager">
					<ul>
						<!-- 페이지 ◀ 이동 -->
						<c:choose>
							<c:when test="${start !=1 }">
								<li><a href="${pageContext.request.contextPath }/board?p=${start-5}">◀</a></li>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
						
						<!-- 페이지 번호 -->
						<c:forEach begin="${start}" end="${start+4 }" step="1" var="page">
							<c:choose >
								<c:when test="${p==page }">
									<li class="selected"><a href="${pageContext.request.contextPath }/board?p=${page}">${page}</a></li>
								</c:when>
								<c:when test="${total< page }">
									<li class="disable"><a href="${pageContext.request.contextPath }/board?p=${page}">${page}</a></li>
								</c:when>
								<c:otherwise>
									<li class=""><a href="${pageContext.request.contextPath }/board?p=${page}">${page}</a></li>									
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<!-- 페이지 ▶ 이동 -->
						<c:choose>
							<c:when test="${total <= endPage }">
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath }/board?p=${start+5}">▶</a></li>							
							</c:otherwise>
						</c:choose>
					</ul>
				</div>		
				<!-- pager 추가 -->
				
				<div class="bottom">
				<c:choose>
					<c:when test='${empty authUser }'>
						<p style="text-align:right;">글쓰기를 원하신다면 로그인 하세요.</p>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/board?a=writeform&reply=FALSE" id="new-book">글쓰기</a>
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

<c:if test='${param.deleteresult == "success" }'>
	<script>alert("성공적으로 삭제되었습니다.")</script>
</c:if>

