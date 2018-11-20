<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="resources/bootstrap.min.css" />
</head>
<body>
boardlist<br><hr>
<div id="wrapper">
<table border="1">
	<tr>
		<td style="width:50px;">번호</td><td style="width:300px;">제목</td><td style="width:100px;">글쓴이</td><td style="width:180px;">작성일</td>
	</tr>
	<c:forEach var="p" items="${postnum }"  varStatus="status" >
	<tr>
		<td style="width:50px;">${p }</td>
		<td style="width:300px;"><a href="boardpost.go?postnum=${p }">${subject[status.index] }</a></td>
		<td style="width:100px;">${writer[status.index] }</td>
		<td style="width:150px;">${regdate[status.index] }</td>
	</tr>
	</c:forEach>
</table>
	<div style="text-align: center;">
	<jsp:include page="paging.jsp" flush="true">
	    <jsp:param name="firstPageNo" value="${paging.firstPageNo}" />
	    <jsp:param name="prevPageNo" value="${paging.prevPageNo}" />
	    <jsp:param name="startPageNo" value="${paging.startPageNo}" />
	    <jsp:param name="pageNo" value="${paging.pageNo}" />
	    <jsp:param name="endPageNo" value="${paging.endPageNo}" />
	    <jsp:param name="nextPageNo" value="${paging.nextPageNo}" />
	    <jsp:param name="finalPageNo" value="${paging.finalPageNo}" />
	</jsp:include>
	
	<a href="postWrite.go">글 작성하기</a>
	</div>
</div>
</body>
</html>



