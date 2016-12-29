<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../main/head.jsp"></jsp:include>

<div class="local_ov01 local_ov">  
    총회원수 <fmt:formatNumber value="${countallmember}" pattern="#,###.##"/>명 중, 
<<<<<<< HEAD
    <a href="?sst=mb_intercept_date&amp;sod=desc&amp;sfl=<?php echo $sfl ?>&amp;stx=<?php echo $stx ?>">
    차단 <?php echo number_format($intercept_count) ?></a>명,
    
    <a href="?sst=mb_leave_date&amp;sod=desc&amp;sfl=<?php echo $sfl ?>&amp;stx=<?php echo $stx ?>">
    탈퇴 <fmt:formatNumber value="${countretiredmembers}" pattern="#,###.##"/></a>명
</div>


<form id="fsearch" name="fsearch" class="local_sch01 local_sch" method="get">

<label for="sfl" class="sound_only">검색대상</label>
<select name="sfl" id="sfl">
    <option value="mb_id" >회원아이디</option>
    <option value="mb_nick">닉네임</option>
    <option value="mb_name">이름</option>
    <option value="mb_level">권한</option>
    <option value="mb_email">E-MAIL</option>
    <option value="mb_tel">전화번호</option>
    <option value="mb_hp">휴대폰번호</option>
    <option value="mb_point">포인트</option>
    <option value="mb_datetime">가입일시</option>
    <option value="mb_ip">IP</option>
    <option value="mb_recommend">추천인</option>
</select>

<label for="stx" class="sound_only">검색어<strong class="sound_only">필수</strong></label>
<input type="text" name="stx" value="${stx}" id="stx" required class="required frm_input">
<input type="submit" class="btn_submit" value="검색">
</form>

<div class="local_desc01 local_desc">
    <p>
        회원자료 삭제 시 다른 회원이 기존 회원아이디를 사용하지 못하도록 회원아이디, 이름, 닉네임은 삭제하지 않고 영구 보관합니다.
    </p>
</div>

if ($is_admin == 'super')
<div class="btn_add01 btn_add">
    <a href="./member_form.php" id="member_add">회원추가</a>
</div>
=======
    <a href="?sst=mb_intercept_date&amp;sod=desc&amp;sfl=<?php echo $sfl ?>&amp;stx=<?php echo $stx ?>">차단 <?php echo number_format($intercept_count) ?></a>명,
    <a href="?sst=mb_leave_date&amp;sod=desc&amp;sfl=<?php echo $sfl ?>&amp;stx=<?php echo $stx ?>">탈퇴 <fmt:formatNumber value="${countretiredmembers}" pattern="#,###.##"/></a>명
</div>

	<table border="1">
		<tr>
			<td>id</td>
			<td>memberId</td>
			<td>name</td>
			<td>nick</td>
			<td>sex</td>
			<td>memo</td>
		</tr>
	<c:forEach var="member" items="${memberlist}" varStatus="loop">
		<tr>
			<td>${member.id}</td>
			<td>${member.memberId}</td>
			<td>${member.name}</td>
			<td>${member.nick}</td>
			<td>${member.sex}</td>
			<td>${member.memo}</td>
		</tr>
	</c:forEach>
	</table>
>>>>>>> branch 'master' of https://github.com/gnuboard/jsboard.git
