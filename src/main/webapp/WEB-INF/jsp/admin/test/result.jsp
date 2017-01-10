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
    검색어 : ${searchWord} <br>
    검색 키워드 : ${keyword} <br>
    <c:forEach var="member" items="${searchMember}" varStatus="i">
    	memberId : ${member.memberId} <br>
    	nick : ${member.nick} <br>
    	datetime : ${member.datetime} <br>
    	
    </c:forEach>
</div>
</body>
</html>
