<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Member Test</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>id</td>
			<td>group id</td>
			<td>member id</td>
		</tr>
		<tr>
			<td>${gMember.id}</td>
			<td>${gMember.groupId}</td>
			<td>${gMember.memberId}</td>
		</tr>
	</table>
</body>
</html>