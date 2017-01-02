<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>js보드 초기환경설정</title>
</head>
<body>
<c:choose>
<c:when test="${isAgree eq 'on'}">
	TABLE과 Java Object 매핑을 위한 TABLE명 접두사 <input type="text" name="prefix" value="js_"/> <br>
	최고관리자 정보입력<br>
	회원 ID <input type="text" name="id" value="admin"/> <br>
	비밀번호 <input type="text" name="password" value=""/> <br>
	이름 <input type="text" name="name" value="최고관리자"/> <br>
	E-mail <input type="text" name="email" value="admin@domain.com"/> <br>
	<a href="/install/step/4">다음</a>
</c:when>
<c:otherwise>
	라이센스(License) 내용에 동의하셔야 설치를 계속하실 수 있습니다.<br>
	<a href="/install/step/2">뒤로가기</a>
</c:otherwise>
</c:choose>
</body>
</html>