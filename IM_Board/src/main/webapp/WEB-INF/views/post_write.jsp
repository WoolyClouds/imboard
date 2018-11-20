<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 쓰기</title>
<link rel="stylesheet" type="text/css" href="resources/bootstrap.min.css" />
</head>
<body>
board_write<br><hr>
<div id="wrapper">
<form action="postWrite.do" id="boardwriteform" method="post">
<table border="1">
	<tr>
		<td style="width:100px;">작성자</td><td><input type="text" id="writer" name="writer"></td>
		<td style="width:100px;">비밀번호</td><td><input type="password" id="password" name="password"></td>
	</tr>
	<tr>
		<td  style="width:100px;">제목</td><td colspan="3"><input type="text" id="subject" name="subject"  style="width:535px;" > </td>
	</tr>
	<tr>
		<td colspan="4"><textarea name="content" id="content" form="boardwriteform" rows="20" cols="81" style="width: 640px; height: 365px;"></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="5" align="right" ><button type="reset">리셋하기</button> <button>작성하기</button></td>
	</tr>
</table>
</form>
<a href="boardlist.go">목록으로</a>
</div>
</body>
</html>