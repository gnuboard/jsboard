<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>라이센스 동의</title>
</head>
<script>
function frm_submit(f)
{
    if (!f.agree.checked) {
        alert("라이센스 내용에 동의하셔야 설치가 가능합니다.");
        return false;
    }
    return true;
}
</script>
<body>
라이센스(License) 내용을 반드시 확인하십시오.<br>
라이센스에 동의하시는 경우에만 설치가 진행됩니다.<br>

<form name="agreeForm" action="/install/step/3" method="post" onsubmit="return frm_submit(this);">
	<label for="agree">동의합니다.</label>
	<input type="checkbox" name="agree" value="on"/><br> 
	<input type="submit" value="다음"/>
</form>
</body>
</html>