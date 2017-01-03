<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="/css/install.css">
<title>설치</title>
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
<div id="top">
    <h1>JSBOARD</h1>
    <p>INSTALLATION</p>
</div>
<div id="contents">
    <div class="ins_tab">
        <ol>
            <li>라이센스</li>
            <li class="on">정보입력</li>
            <li>설치진행</li>
            <li>설치완료</li>
        </ol>
    </div>
<c:choose>
	<c:when test="${isAgree eq 'on'}">
	    <form id="frm_install" name="configeForm" action="/install/step/4" autocomplete="off" method="post" onsubmit="return frm_install_submit(this)">
	    <div class="ins_inner">
	        <h2>정보입력</h2>
	        <table class="ins_frm">
	        <caption>TABLE 정보입력</caption>
	        <tbody>
	        <tr>
	            <th scope="row"><label for="table_prefix">TABLE명 접두사</label></th>
	            <td>
	                <input name="table_prefix" type="text" value="js_" id="table_prefix">
	                <span>가능한 변경하지 마십시오.</span>
	            </td>
	        </tr>
	        </tbody>
	        </table>
	
	        <table class="ins_frm">
	        <caption>최고관리자 정보입력</caption>
	
	        <tbody>
	        <tr>
	            <th scope="row"><label for="admin_id">회원 ID</label></th>
	            <td>
	                <input name="admin_id" type="text" value="admin" id="admin_id">
	            </td>
	        </tr>
	        <tr>
	            <th scope="row"><label for="admin_pass">비밀번호</label></th>
	            <td>
	                <input name="admin_pass" type="text" id="admin_pass">
	            </td>
	        </tr>
	        <tr>
	            <th scope="row"><label for="admin_name">이름</label></th>
	            <td>
	                <input name="admin_name" type="text" value="최고관리자" id="admin_name">
	            </td>
	        </tr>
	        <tr>
	            <th scope="row"><label for="admin_email">E-mail</label></th>
	            <td>
	                <input name="admin_email" type="text" value="admin@domain.com" id="admin_email">
	            </td>
	        </tr>
	        </tbody>
	        </table>
	
	        <p>
	            <strong class="st_strong">주의! 이미 그누보드5가 존재한다면 DB 자료가 망실되므로 주의하십시오.</strong><br>
           		주의사항을 이해했으며, 그누보드 설치를 계속 진행하시려면 다음을 누르십시오.
	        </p>
	
	        <div class="ins_btn">
	            <input type="submit" value="다음" class="btn_01">
	        </div>
	    </div>
	    </form>
	</c:when>
	<c:otherwise>
		<div class="ins_inner">
			라이센스(License) 내용에 동의하셔야 설치를 계속하실 수 있습니다.<br>
			<a href="/install/step/2" class="btn_01">뒤로가기</a>
		</div>
	</c:otherwise>
</c:choose>
</div>
</body>
</html>