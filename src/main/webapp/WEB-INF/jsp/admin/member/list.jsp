<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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





<div class="tbl_head02 tbl_wrap">
    <table border=1>
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
    
    <c:forEach var="member" items="${memberslist}" varStatus="i">  
    <!-- 접근가능한 그룹수  미구현 -->   
    
    	<!--접근가능한 그룹수가 1개 이상 조건 써야함  -->
   			<c:set var="group" value="<a href='/adm/member/accessiblegroups?memberId=${member.memberId}'> .$row2['cnt']</a>"/>
   		
   		<!--수정글자에 링크달기  -->
   		<c:choose>
   			<c:when test="${is_admin eq 'group}">
   				<c:set var="s_mod" value=""/>
   			</c:when>
   			<c:otherwise>
   				<c:set var="s_mod" value="<a href='/adm/member/form?${qstr}&amp;w=u&amp;memberId=${member.memberId}'>수정</a>"/>
   			</c:otherwise>   				
   		</c:choose>
   		
   		
   		<!--그룹글자에 링크달기  -->	
   		<c:set var="s_grp" value="<a href='/adm/member/accessiblegroups?memberId=${member.memberId}'>그룹</a>"/>	
   			
   		<!--탈퇴일,차단일   -->
   		
   		<c:if test="${! empty member.mb_leave_date}">
   			<c:set var="leave_date" value=""/>	
   			
   		</c:if>
   			
   		
   		
   
   
   
   
   
   
    
    <tr class="${bg}">
        <td headers="mb_list_chk" class="td_chk" rowspan="2">
            <input type="hidden" name="mb_id[${i}]" value="${member.memberId}" id="mb_id_${i}">
            <label for="chk_${i}" class="sound_only">${member.name} ${member.nick}님</label>
            <input type="checkbox" name="chk[]" value="${i}" id="chk_${i}" class="check">
        </td>
        <td headers="mb_list_id" rowspan="2" class="td_name sv_use">${member.memberId}</td>
        <td headers="mb_list_name" class="td_mbname">${member.name}</td>
        <td headers="mb_list_cert" colspan="6" class="td_mbcert">
            <input type="radio" name="mb_certify[${i}]" value="ipin" id="mb_certify_ipin_${i}" <c:if test="${member.certify eq 'ipin'}">checked</c:if> >
            <label for="mb_certify_ipin_${i}">아이핀</label>
            <input type="radio" name="mb_certify[${i}]" value="hp" id="mb_certify_hp_${i}" <c:if test="${member.certify eq 'hp'}">checked</c:if>>
            <label for="mb_certify_hp_${i}">휴대폰</label>
        </td>
        <td headers="mb_list_mobile" class="td_tel">${member.hpNo}</td>
        <td headers="mb_list_auth" class="td_mbstat">
         <!--     <?php
            if ($leave_msg || $intercept_msg) echo $leave_msg.' '.$intercept_msg;
            else echo "정상";
            ?>
            <?php echo get_member_level_select("mb_level[$i]", 1, $member['mb_level'], $row['mb_level']) ?>
            -->
        </td>
        <td headers="mb_list_lastcall" class="td_date">${fn:substring(member.todayLogin,2,10)}</td><!--yy-mm-dd 형식 -->
        <td headers="mb_list_grp" rowspan="2" class="td_numsmall">${group}</td>
        <td headers="mb_list_mng" rowspan="2" class="td_mngsmall">${s_mod} ${s_grp}</td>
    </tr>
    <tr class="${bg}">
        <td headers="mb_list_nick" class="td_name sv_use"><div>${member.nick}</div></td>
        <td headers="mb_list_mailc" class="td_chk"><c:if test="${fn:substring(member.emailCertify,0,2)=='20'}"><span class="txt_true">Yes</span></c:if><c:if test="${fn:substring(member.emailCertify,0,2)!='20'}"><span class="txt_false">No</span></c:if></td>
        <td headers="mb_list_open" class="td_chk">
            <label for="mb_open_${i}" class="sound_only">정보공개</label>
            <input type="checkbox" name="mb_open[${i}]" <c:if test="${! empty member.open}">checked</c:if> value="1" id="mb_open_${i}">
        </td>
        <td headers="mb_list_mailr" class="td_chk">
            <label for="mb_mailling_${i}" class="sound_only">메일수신</label>
            <input type="checkbox" name="mb_mailling[${i}]" <c:if test="${! empty member.mailling}">checked</c:if> value="1" id="mb_mailling_${i}">
        </td>
        <td headers="mb_list_sms" class="td_chk">
            <label for="mb_sms_${i}" class="sound_only">SMS수신</label>
            <input type="checkbox" name="mb_sms[${i}]" <c:if test="${! empty member.sms}">checked</c:if> value="1" id="mb_sms_${i}">
        </td>
        <td headers="mb_list_adultc" class="td_chk">
            <label for="mb_adult_${i}" class="sound_only">성인인증</label>
            <input type="checkbox" name="mb_adult[${i}]" <c:if test="${! empty member.adult}">checked</c:if> value="1" id="mb_adult_${i}">
        </td>
        <td headers="mb_list_deny" class="td_chk">
            <c:if test="${empty member.leaveDate}">
            	<input type="checkbox" name="mb_intercept_date[${i}]" <c:if test="${! empty member.interceptDate}">checked</c:if> value="${member.interceptDate}" id="mb_intercept_date_${i}" title="">
           		<label for="mb_intercept_date_${i}" class="sound_only">접근차단</label>
            </c:if>
        </td>
        <td headers="mb_list_tel" class="td_tel">${member.tel}<!--<?php echo get_text($row['mb_tel']);  --> </td>
        <td headers="mb_list_point" class="td_num"><a href="adm/member/pointlist?sfl=mb_id&amp;stx=${member.memberId}"> <fmt:formatNumber value="${member.point}" pattern="#,###.##"/></a></td>
        <td headers="mb_list_join" class="td_date">${fn:substring(mb_datetime,2,8)}</td>
    </tr>
    
    </c:forEach>
    
    <c:if test="${empty memberlist }">
    	<tr><td colspan="16" class="empty_table">자료가 없습니다.</td></tr>
    </c:if>

    
    
    
	</table>
</div><!-- tbl_head02 tbl_wrap -->

<div class="btn_list01 btn_list">
    <input type="submit" name="act_button" value="선택수정" onclick="document.pressed=this.value">
    <input type="submit" name="act_button" value="선택삭제" onclick="document.pressed=this.value">
</div>



</form>


<script>
function fmemberlist_submit(f)
{
    if (!is_checked("chk[]")) {
        alert(document.pressed+" 하실 항목을 하나 이상 선택하세요.");
        return false;
    }

    if(document.pressed == "선택삭제") {
        if(!confirm("선택한 자료를 정말 삭제하시겠습니까?")) {
            return false;
        }
    }

    return true;
}

</script>

<jsp:include page="../main/tail.jsp"></jsp:include>