<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- $sub_menu = "200100";
include_once('./_common.php');

auth_check($auth[$sub_menu], 'w');
 -->
 
 
<jsp:include page="../main/head.jsp"></jsp:include> 
 
 
<!--회원 추가시 필요한 변수들  -->
<c:choose>
 	<c:when test="${type eq 'add'}">
 		<c:set var="requiredMemberId" value="required"/>
 		<c:set var="requiredMemberIdClass" value="required alnum_"/>
 		<c:set var="required" value="required"></c:set>
 		<c:set var="soundOnly" value="<stong class='sound_only'>필수</strong>"/>
 		<c:set var="htmlTItle" value="추가"/> 
 		<c:set var="actionLink" value="/adm/member/add"/>	
 		
 		<c:set var="level" value="${config.registerLevel}"/>
 		 		
 	</c:when>
 	

 	<c:when test="${type eq 'update'}">
 		<c:set var="htmlTItle" value="수정"/> 	
 		<c:set var="actionLink" value="/adm/member/update"/>
 		 <c:if test="${is_admin != 'super' &&  member.level >= mylevel} " >
     		<script>alert('자신보다 권한이 높거나 같은 회원은 수정할 수 없습니다.')</script>
         </c:if>
 	</c:when>	
 		
 	<c:otherwise>
 		<script>
 			alert('제대로 된 값이 넘어오지 않았습니다.');
 		</script> 		
 	</c:otherwise>	 	
 </c:choose>
 
 
 
 
<!--  add_javascript(G5_POSTCODE_JS, 0);    //다음 주소 js -->
 
 
<div id="container"><!--이 태그의 끝 태그는 admin/tail.jsp에있음  -->
    <!-- 중간 레이아웃 -->
    <h1 id="container_title">회원  ${htmlTItle}   </h1>

    <div id="container_wr">

        <form name="fmember" id="fmember" action="${actionLink}" onsubmit="return fmember_submit(this);" method="post" enctype="multipart/form-data">
       
        <input type="hidden" name="_method" value=""/>
        <div class="table_basic table_form">
            <table>
            <caption>회원 추가</caption>
            <tbody>
            <tr>
                <th scope="row"><label for="mb_id">아이디 ${soundOnly} </label></th>
                <td>
                    <input type="text" name="memberId" id="mb_id" class="frm_input" size="15" value="${member.memberId}">
                    <c:if test="${type eq 'update' }">
                    <input type="hidden" name="id" value="${member.id}" />
                    </c:if>
                </td>
                <th scope="row"><label for="mb_password">비밀번호 ${soundOnly}</label></th>
                <td><input type="password" name="password" id="mb_password" class="frm_input" size="15"></td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_name">이름(실명) ${soundOnly}</label></th>
                <td><input type="text" name="name" id="mb_name" required class="required frm_input" size="15" value="${member.name}"></td>
                <th scope="row"><label for="mb_nick">닉네임 ${soundOnly} </label></th>
                <td><input type="text" name="nick" id="mb_nick" required class="required frm_input" size="15" value="${member.nick}"></td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_level">회원 권한</label></th>
                <td>
                	<!--select태그대신 이걸 구현해야함 <?php echo get_member_level_select('mb_level', 1, $member['mb_level'], $mb['mb_level']) ?> -->
                    <select>
                        <option>1</option>                        
                    </select>
                </td>
                <th scope="row">포인트</th>
                <td><a href="/adm/member/poinlist/sfl/memberId/stx/${member.memberId}" target="_blank"><fmt:formatNumber value="${member.point}" pattern="#,###.##"/></a>점</td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_email">E-mail ${soundOnly}</label></th>
                <td><input type="text" name="email" id="mb_email" required class="required frm_input email" size="30" value="${member.email}"></td>
                <th scope="row"><label for="mb_homepage">홈페이지</label></th>
                <td><input type="text" name="homePage" id="mb_homepage" class="frm_input" size="15" value="${member.homePage}"></td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_hp">휴대폰번호</label></th>
                <td><input type="text" name="hpNo" id="mb_hp" class="frm_input" size="15" maxlength="20" value="${member.hpNo}"></td>
                <th scope="row"><label for="mb_tel">전화번호</label></th>
                <td><input type="text" name="tel" id="mb_tel" class="frm_input" size="15" maxlength="20" value="${member.tel}"></td>
            </tr>
            <tr>
                <th scope="row">본인확인방법</th>
                <td colspan="3">
                    <input type="radio" name="certify" value="ipin" id="mb_certify_ipin" <c:if test="${member.certify eq 'ipin' }">checked</c:if>>
                    <label for="mb_certify_ipin">아이핀</label>
                    <input type="radio" name="certify" value="hp" id="mb_certify_hp" <c:if test="${member.certify eq 'hp' }">checked</c:if>>
                    <label for="mb_certify_hp">휴대폰</label>
                </td>
            </tr>
            <tr>
                <th scope="row">본인확인</th>
                <td>
                    <input type="radio" name="isCertify" value="1" id="mb_certify_yes" <c:if test="${!empty member.certify}">checked</c:if> >
                    <label for="mb_certify_yes">예</label>
                    <input type="radio" name="isCertify" value="0" id="mb_certify_no" <c:if test="${empty member.certify}">checked</c:if> >
                    <label for="mb_certify_no">아니오</label>
                </td>
                <th scope="row"><label for="mb_adult">성인인증</label></th>
                <td>
                    <input type="radio" name="adult" value="1" id="mb_adult_yes" <c:if test="${member.adult=='1'}">checked</c:if>>
                    <label for="mb_adult_yes">예</label>
                    <input type="radio" name="adult" value="0" id="mb_adult_no" <c:if test="${empty member.adult||member.adult=='0'}">checked</c:if>>
                    <label for="mb_adult_no">아니오</label>
                </td>
            </tr>
            <tr>
                <th scope="row">주소</th>
                <td colspan="3" class="td_addr_line">
                    <label for="mb_zip" class="sound_only">우편번호 ${soundOnly}</label>
                    <input type="text" name="zipCode" id="mb_zip" class="frm_input readonly" size="5" maxlength="6" value="${member.zipCode1}${member.zipCode2}">
                    <button type="button" class="btn_frmline" onclick="win_zip('fmember', 'mb_zip', 'mb_addr1', 'mb_addr2', 'mb_addr3', 'mb_addr_jibeon')">주소 검색</button><br>
                    <input type="text" name="address1" id="mb_addr1" class="frm_input readonly" size="60" value="${member.address1}">
                    <label for="mb_addr1">기본주소</label><br>
                    <input type="text" name="address2" id="mb_addr2" class="frm_input" size="60" value="${member.address2 }">
                    <label for="mb_addr2">상세주소</label>
                    <br>
                    <input type="text" name="address3" id="mb_addr3" class="frm_input" size="60" value="${member.address3}">
                    <label for="mb_addr3">참고항목</label>
                    <input type="hidden" name="addressJibeon" value="${member.addressJibeon}">
                    
                </td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_icon">회원아이콘</label>
                    <span class="tooltip">
                        <button type="button" class="tooltip_btn"><span class="sound_only">도움말</span></button>
                        <span class="tooltip_area">이미지 크기는 <strong>넓이 ${config.memberIconWidth}픽셀 높이 ${config.memberIconHeight}픽셀</strong>로 해주세요.</span>
                    </span>
                </th>
                <td colspan="3">
                    <input type="file" name="mb_icon" id="mb_icon">
                    <!-- 파일업로드 -->
                </td>
            </tr>
            <tr>
                <th scope="row">메일 수신</th>
                <td>
                    <input type="radio" name="mailling" value="1" id="mb_mailling_yes" <c:if test="${member.mailling==1}">checked</c:if>  >
                    <label for="mb_mailling_yes">예</label>
                    <input type="radio" name="mailling" value="0" id="mb_mailling_no" <c:if test="${member.mailling==0}">checked</c:if>>
                    <label for="mb_mailling_no">아니오</label>
                </td>
                <th scope="row"><label for="mb_sms_yes">SMS 수신</label></th>
                <td>
                    <input type="radio" name="sms" value="1" id="mb_sms_yes" <c:if test="${member.sms==1}">checked</c:if> >
                    <label for="mb_sms_yes">예</label>
                    <input type="radio" name="sms" value="0" id="mb_sms_no" <c:if test="${member.sms==0}">checked</c:if>>
                    <label for="mb_sms_no">아니오</label>
                </td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_open">정보 공개</label></th>
                <td colspan="3">
                    <input type="radio" name="open" value="1" id="mb_open_yes" <c:if test="${member.open==1}">checked</c:if>>
                    <label for="mb_open_yes">예</label>
                    <input type="radio" name="open" value="0" id="mb_open_no" <c:if test="${member.open==0}">checked</c:if>>
                    <label for="mb_open_no">아니오</label>
                </td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_signature">서명</label></th>
                <td colspan="3"><textarea  name="signature" id="mb_signature">${member.signature}</textarea></td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_profile">자기 소개</label></th>
                <td colspan="3"><textarea name="profile" id="mb_profile">${member.profile}</textarea></td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_memo">메모</label></th>
                <td colspan="3"><textarea name="memo" id="mb_memo">${member.memo }</textarea></td>
            </tr>

		<c:if test="${type eq 'update'}">
            <tr>
                <th scope="row">회원가입일</th>
                <td><fmt:formatDate value="${member.datetime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                <th scope="row">최근접속일</th>
                <td><fmt:formatDate value="${member.todayLogin}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
            </tr>       
            <tr>
                <th scope="row">IP</th>
                <td colspan="3"> ${member.ip} </td>
            </tr>    
	    </c:if>	   
	    
	    <c:choose>
	    	<c:when test="${! empty config.useEmailCertify && type eq 'update'}">
            <tr>
                <th scope="row">인증일시
                    <span class="tooltip">
                        <button type="button" class="tooltip_btn"><span class="sound_only">도움말</span></button>
                        	<c:if test="${member.emailCertify eq '0000-00-00 00:00:00.0'}">
                        <span class="tooltip_area">회원님이 메일을 수신할 수 없는 경우 등에 직접 인증처리를 하실 수 있습니다.</span>
                        	</c:if>
                    </span>
                </th>
                <td colspan="3">
                    <input type="checkbox" name="passive_certify" id="passive_certify">
                    <label for="passive_certify">수동인증</label>
						<c:if test="${member.emailCertify ne '0000-00-00 00:00:00.0'}">
							<fmt:formatDate value="${member.emailCertify}" pattern="yyyy-MM-dd hh:mm:ss"/>
						</c:if>
											
                </td>
            </tr>
			</c:when>
		</c:choose>
		
		<c:if test="${config.useRecommend==1}">		
            <tr>
                <th scope="row">추천인</th>
                <td colspan="3"><c:choose><c:when test="${config.useRecommend==1}">${config.useRecommend}</c:when><c:otherwise>없음</c:otherwise></c:choose></td>
            </tr>
		</c:if>
		
		
            <tr>
                <th scope="row"><label for="mb_leave_date">탈퇴일자</label></th>
                <td>
                    <input type="text" name="leaveDate" id="mb_leave_date" class="frm_input" maxlength="8" value="${member.leaveDate}">
                    <input type="checkbox" id="mb_leave_date_set_today" value="" onclick=""  >
                    <label for="mb_leave_date_set_today">탈퇴일을 오늘로 지정</label>
                </td>
                <th scope="row">접근차단일자</th>
                <td>
                    <input type="text" name="interceptDate"  id="mb_intercept_date" class="frm_input" maxlength="8">
                    <input type="checkbox" id="mb_intercept_date_set_today" onclick="">
                    <label for="mb_intercept_date_set_today">접근차단일을 오늘로 지정</label>
                </td>
            </tr>

            <tr>
               	<th scope="row"><label for="mb_1">여분 필드 1</label></th>
               	<td colspan="3"><input type="text" name="extra1" id="mb_1" class="frm_input" size="30" maxlength="255"></td>
            </tr>
            <tr>
               	<th scope="row"><label for="mb_2">여분 필드 2</label></th>
               	<td colspan="3"><input type="text" name="extra2" id="mb_2" class="frm_input" size="30" maxlength="255"></td>
            </tr>
            <tr>
               	<th scope="row"><label for="mb_3">여분 필드 3</label></th>
               	<td colspan="3"><input type="text" name="extra3" id="mb_3" class="frm_input" size="30" maxlength="255"></td>
            </tr>
            <tr>
               	<th scope="row"><label for="mb_4">여분 필드 4</label></th>
               	<td colspan="3"><input type="text" name="extra4" id="mb_4" class="frm_input" size="30" maxlength="255"></td>
            </tr>
            <tr>
               	<th scope="row"><label for="mb_5">여분 필드 5</label></th>
               	<td colspan="3"><input type="text" name="extra5" id="mb_5" class="frm_input" size="30" maxlength="255"></td>
            </tr>
            <tr>
               	<th scope="row"><label for="mb_6">여분 필드 6</label></th>
               	<td colspan="3"><input type="text" name="extra6" id="mb_6" class="frm_input" size="30" maxlength="255"></td>
            </tr>
            <tr>
               	<th scope="row"><label for="mb_7">여분 필드 7</label></th>
               	<td colspan="3"><input type="text" name="extra7" id="mb_7" class="frm_input" size="30" maxlength="255"></td>
            </tr>
            <tr>
               	<th scope="row"><label for="mb_8">여분 필드 8</label></th>
               	<td colspan="3"><input type="text" name="extra8" id="mb_8" class="frm_input" size="30" maxlength="255"></td>
            </tr>
            <tr>
               	<th scope="row"><label for="mb_9">여분 필드 9</label></th>
               	<td colspan="3"><input type="text" name="extra9" id="mb_9" class="frm_input" size="30" maxlength="255"></td>
            </tr>
            <tr>
               	<th scope="row"><label for="mb_10">여분 필드 10</label></th>
               	<td colspan="3"><input type="text" name="extra10" id="mb_10" class="frm_input" size="30" maxlength="255"></td>
            </tr>
            </tbody>
            </table>
        </div>
                
        <div class="btn_fixed_top">        	
            <input type="submit" class="btn_submit btn"  value="${htmlTItle}" onclick="document.pressed='${htmlTItle}'">  
        </div>
        
        </form>
    </div>
    
     
 <script>
function fmember_submit(f) {

		if(document.pressed == '추가') {
	
		}else if (document.pressed == '수정') {
			$("input:hidden[name=_method]").val("PUT");
			
		}
		return true;
	}
</script>
 <jsp:include page="../main/tail.jsp"></jsp:include>