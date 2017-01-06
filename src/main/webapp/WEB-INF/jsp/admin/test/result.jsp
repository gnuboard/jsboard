<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>검색결과</title>
    <link rel="stylesheet" href="./style.css">
 </head>
 <body>
 <div id="container">
    ${searchWord}, ${keyword}	<br>
    <c:forEach var="member" items="${searchMember}" varStatus="i">
    	${member.memberId} <br>
    	${member.nick} <br>
    	${member.datetime} <br>
    	
    </c:forEach>
</div>
</body>
</html>
