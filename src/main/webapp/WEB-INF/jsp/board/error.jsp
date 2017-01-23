<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>오류 안내 페이지</title>
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	// 댓글 한 레벨에서 26개 이상 달았을 때 에러
	if('<%=request.getAttribute("errorType")%>' == 'tooManyComment') {
		alert("<%=request.getAttribute("msg1")%>\n\n<%=request.getAttribute("msg2")%>");
		history.back();
	}
});
</script>
<body>

</body>
</html>