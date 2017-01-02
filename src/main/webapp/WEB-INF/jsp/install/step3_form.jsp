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
<script>
function frm_install_submit(f)
{
    if (f.prefix.value == '')
    {
        alert('TABLE 접두어를 입력하십시오.'); f.mysql_db.focus(); return false;
    }
    else if (f.admin_id.value == '')
    {
        alert('최고관리자 ID 를 입력하십시오.'); f.admin_id.focus(); return false;
    }
    else if (f.admin_pass.value == '')
    {
        alert('최고관리자 비밀번호를 입력하십시오.'); f.admin_pass.focus(); return false;
    }
    else if (f.admin_name.value == '')
    {
        alert('최고관리자 이름을 입력하십시오.'); f.admin_name.focus(); return false;
    }
    else if (f.admin_email.value == '')
    {
        alert('최고관리자 E-mail 을 입력하십시오.'); f.admin_email.focus(); return false;
    }


    if(/^[a-z][a-z0-9]/i.test(f.admin_id.value) == false) {
        alert('최고관리자 회원 ID는 첫자는 반드시 영문자 그리고 영문자와 숫자로만 만드셔야 합니다.');
        f.admin_id.focus();
        return false;
    }

    return true;
}
</script>
<c:choose>
<c:when test="${isAgree eq 'on'}">
	<form id="frm_install" name="configeForm" action="/install/step/4" autocomplete="off" method="post" onsubmit="return frm_install_submit(this)">
		<label for="table_prefix">TABLE명 접두사 </label><input type="text" name="table_prefix" value="js_"/> <br>
		<span>가능한 변경하지 마십시오.</span>
		<h3>최고관리자 정보입력</h3>
		<label for="id">회원 ID</label><input type="text" name="admin_id" value="admin"/> <br>
		<label for="password">비밀번호</label><input type="text" name="admin_pass" value=""/> <br>
		<label for="name">이름</label><input type="text" name="admin_name" value="최고관리자"/> <br>
		<label for="email">E-mail</label><input type="text" name="admin_email" value="admin@domain.com"/> <br>
		<input type="submit" value="다음"/>
	</form>
</c:when>
<c:otherwise>
	라이센스(License) 내용에 동의하셔야 설치를 계속하실 수 있습니다.<br>
	<a href="/install/step/2">뒤로가기</a>
</c:otherwise>
</c:choose>
</body>
</html>