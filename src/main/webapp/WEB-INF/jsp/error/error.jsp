<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>오류 정보 안내 페이지</title>
</head>
<body>
	<h4>${message}</h4>
	<p>
		이용에 불편을 드려 죄송합니다.
	</p>
	<input type="button" onclick="history.back();" value="뒤로 가기">
</body>
</html>