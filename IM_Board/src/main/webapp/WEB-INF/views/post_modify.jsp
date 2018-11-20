<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정하기</title>
<link rel="stylesheet" type="text/css" href="resources/bootstrap.min.css" />
</head>
<body>
board_modify<br><hr>
<div id="wrapper">
<form action="postModify.do" id="boardwriteform" method="post">
<table border="1">
	<tr>
		<td style="width:100px;">작성자</td><td><input type="text" id="writer" name="writer" value="${writer }" readonly="readonly" style="color: gray;">
		<input type="hidden" id="postnum" name="postnum" value="${postnum }">
		</td>
		<td style="width:100px;">비밀번호</td><td><input type="password" id="password" name="password"></td>
	</tr>
	<tr>
		<td  style="width:100px;">제목</td><td colspan="3"><input type="text" id="subject" name="subject"  style="width:535px;" value="${subject }"> </td>
	</tr>
	<tr>
		<td colspan="4"><textarea name="content" id="content" form="boardwriteform" rows="20" cols="81" style="width: 640px; height: 365px;">${content }</textarea></td>
	</tr>
	<tr>
		<td colspan="5" align="right" ><button type="reset">리셋하기</button> <button>수정하기</button></td>
	</tr>
</table>
</form>
<a href="boardlist.go">목록으로</a>
</div>
</body>
</html>