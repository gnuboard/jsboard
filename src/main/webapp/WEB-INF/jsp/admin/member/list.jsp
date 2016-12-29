<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../main/head.jsp"></jsp:include>

<div class="local_ov01 local_ov">  
<a href="" class="ov_listall">전체목록</a>

    총회원수 <fmt:formatNumber value="${countallmember}" pattern="#,###.##"/>명 중, 
    
    <a href="/adm/member/list?sst=interceptDate&amp;sod=desc&amp;sfl=${sfl}&amp;stx=${stx}">
    차단 <fmt:formatNumber value="${countblockedmembers}" pattern="#,###.##"/></a>명,
    
    <a href="/adm/member/list?sst=leaveDate&amp;sod=desc&amp;sfl=${sfl}&amp;stx=${stx}">
    탈퇴 <fmt:formatNumber value="${countretiredmembers}" pattern="#,###.##"/></a>명
</div>


<form id="fsearch" name="fsearch" class="local_sch01 local_sch" method="POST">

<label for="sfl" class="sound_only">검색대상</label>
<select name="sfl" id="sfl">
    <option value="mb_id">회원아이디</option>
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

<c:if test="${is_admin eq 'super'}">
	<div class="btn_add01 btn_add">
	    <a href="./member_form.php" id="member_add">회원추가</a>
	</div>
</c:if>

<!--삭제나 수정버튼 눌렀을 때 서브밋 되는 폼.  -->
<form name="fmemberlist" id="fmemberlist" action="" onsubmit="return fmemberlist_submit(this);" method="post">
<input type="hidden" name="sst" value="${sst}">
<input type="hidden" name="sod" value="${sod}">
<input type="hidden" name="sfl" value="${sfl}">
<input type="hidden" name="stx" value="${stx}">
<input type="hidden" name="page" value="${page}">
<input type="hidden" name="token" value="">
</form>




<div class="tbl_head02 tbl_wrap">
    <table>
    <caption></caption>
    <thead>
    <tr>
        <th scope="col" rowspan="2" id="mb_list_chk">
            <label for="chkall" class="sound_only">회원 전체</label>
            <input type="checkbox" name="chkall" value="1" id="chkall" onclick="check_all(this.form)">
        </th>
        <th scope="col" rowspan="2" id="mb_list_id"><?php echo subject_sort_link('mb_id') ?>아이디</a></th>
        <th scope="col" id="mb_list_name"><?php echo subject_sort_link('mb_name') ?>이름</a></th>
        <th scope="col" colspan="6" id="mb_list_cert"><?php echo subject_sort_link('mb_certify', '', 'desc') ?>본인확인</a></th>
        <th scope="col" id="mb_list_mobile">휴대폰</th>
        <th scope="col" id="mb_list_auth">상태/<?php echo subject_sort_link('mb_level', '', 'desc') ?>권한</a></th>
        <th scope="col" id="mb_list_lastcall"><?php echo subject_sort_link('mb_today_login', '', 'desc') ?>최종접속</a></th>
        <th scope="col" rowspan="2" id="mb_list_grp">접근<br>그룹</th>
        <th scope="col" rowspan="2" id="mb_list_mng">관리</th>
    </tr>	
     <tr>
        <th scope="col" id="mb_list_nick"><?php echo subject_sort_link('mb_nick') ?>닉네임</a></th>
        <th scope="col" id="mb_list_mailc"><?php echo subject_sort_link('mb_email_certify', '', 'desc') ?>메일<br>인증</a></th>
        <th scope="col" id="mb_list_open"><?php echo subject_sort_link('mb_open', '', 'desc') ?>정보<br>공개</a></th>
        <th scope="col" id="mb_list_mailr"><?php echo subject_sort_link('mb_mailling', '', 'desc') ?>메일<br>수신</a></th>
        <th scope="col" id="mb_list_sms"><?php echo subject_sort_link('mb_sms', '', 'desc') ?>SMS<br>수신</a></th>
        <th scope="col" id="mb_list_adultc"><?php echo subject_sort_link('mb_adult', '', 'desc') ?>성인<br>인증</a></th>
        <th scope="col" id="mb_list_deny"><?php echo subject_sort_link('mb_intercept_date', '', 'desc') ?>접근<br>차단</a></th>
        <th scope="col" id="mb_list_tel">전화번호</th>
        <th scope="col" id="mb_list_point"><?php echo subject_sort_link('mb_point', '', 'desc') ?> 포인트</a></th>
        <th scope="col" id="mb_list_join"><?php echo subject_sort_link('mb_datetime', '', 'desc') ?>가입일</a></th>
    </tr>
    </thead>
    
    
    
    

</div><!-- tbl_head02 tbl_wrap -->






















