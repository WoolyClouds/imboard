<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 - 비밀번호 확인</title>
<link rel="stylesheet" type="text/css" href="resources/bootstrap.min.css" />
</head>
<body>
post_delete_pwcheck<br><hr>
<div id="wrapper">
<form action="postDelete.do" method="post">
<input type="hidden" id="postnum" name="postnum" value="<%=request.getAttribute("postnum") %>">
비밀번호 입력 : <input type="password" id="password" name="password">
<button>확인</button>
</form>
<a href="boardlist.go">목록으로</a>
</div>
</body>
</html>