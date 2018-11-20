<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
<link rel="stylesheet" type="text/css" href="resources/bootstrap.min.css" />
</head>
<body>
board_post<br><hr>
<div id="wrapper">
<table border="1">
	<tr>
		<td style="width:100px;">작성자</td><td style="width:100px;">${writer }</td>
		<td style="width:100px;">작성일</td><td style="width:150px;">${regdate }</td>
	</tr>
	<tr>
		<td style="width:100px;">제목</td><td colspan="3"  style="width:300px;">${subject }</td>
	</tr>
	<tr>
		<td colspan="4" ><div style="width: 450px; height: 330px;" >${content }</div></td>
	</tr>
</table>
<a href="boardlist.go">목록으로</a>
<a href="postModifyPWCheck.go?postnum=${postnum }">수정하기</a>
<a href="postDeletePWCheck.go?postnum=${postnum }">삭제하기</a>
</div>
</body>
</html>