<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- $sub_menu = "200100";
include_once('./_common.php');

auth_check($auth[$sub_menu], 'w');
 -->
 
 
 <c:choose>
 	<c:when test="${empty w}">
 		<c:set var="requiredMemberId" value="required"></c:set>
 		<c:set var="requiredMemberIdClass" value="required alnum_"></c:set>
 	</c:when>
 </c:choose>
 
 
 
 
 
 
 
<div id="container"><!--이 태그의 끝 태그는 admin/tail.jsp에있음  -->
    <!-- 중간 레이아웃 -->
    <h1 id="container_title">회원 추가</h1>

    <div id="container_wr">

        <form name="fmember" id="fmember" action="/admin/member/update" onsubmit="return fmember_submit(this);" method="post" enctype="multipart/form-data">
        <div class="table_basic table_form">
            <table>
            <caption>회원 추가</caption>
            <tbody>
            <tr>
                <th scope="row"><label for="mb_id">아이디</label></th>
                <td>
                    <input type="text" name="memberId" id="mb_id" class="frm_input" size="15" value="${member.memberId}">
                </td>
                <th scope="row"><label for="mb_password">비밀번호</label></th>
                <td><input type="password" name="password" id="mb_password" class="frm_input" size="15"></td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_name">이름(실명)<strong class="sound_only">필수</strong></label></th>
                <td><input type="text" name="name" id="mb_name" required class="required frm_input" size="15"></td>
                <th scope="row"><label for="mb_nick">닉네임<strong class="sound_only">필수</strong></label></th>
                <td><input type="text" name="nick" id="mb_nick" required class="required frm_input" size="15"></td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_level">회원 권한</label></th>
                <td>
                    <select>
                        <option>1</option>
                    </select>
                </td>
                <th scope="row">포인트</th>
                <td><a href="#" target="_blank">1,000</a> 점</td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_email">E-mail<strong class="sound_only">필수</strong></label></th>
                <td><input type="text" name="email" id="mb_email" required class="required frm_input email" size="30"></td>
                <th scope="row"><label for="mb_homepage">홈페이지</label></th>
                <td><input type="text" name="homepage" id="mb_homepage" class="frm_input" size="15"></td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_hp">휴대폰번호</label></th>
                <td><input type="text" name="hpNo" id="mb_hp" class="frm_input" size="15" maxlength="20"></td>
                <th scope="row"><label for="mb_tel">전화번호</label></th>
                <td><input type="text" name="tel" id="mb_tel" class="frm_input" size="15" maxlength="20"></td>
            </tr>
            <tr>
                <th scope="row">본인확인방법</th>
                <td colspan="3">
                    <input type="radio" name="mb_certify_case" value="ipin" id="mb_certify_ipin">
                    <label for="mb_certify_ipin">아이핀</label>
                    <input type="radio" name="mb_certify_case" value="hp" id="mb_certify_hp">
                    <label for="mb_certify_hp">휴대폰</label>
                </td>
            </tr>
            <tr>
                <th scope="row">본인확인</th>
                <td>
                    <input type="radio" name="mb_certify" value="1" id="mb_certify_yes">
                    <label for="mb_certify_yes">예</label>
                    <input type="radio" name="mb_certify" value="" id="mb_certify_no">
                    <label for="mb_certify_no">아니오</label>
                </td>
                <th scope="row"><label for="mb_adult">성인인증</label></th>
                <td>
                    <input type="radio" name="mb_adult" value="1" id="mb_adult_yes">
                    <label for="mb_adult_yes">예</label>
                    <input type="radio" name="mb_adult" value="0" id="mb_adult_no">
                    <label for="mb_adult_no">아니오</label>
                </td>
            </tr>
            <tr>
                <th scope="row">주소</th>
                <td colspan="3" class="td_addr_line">
                    <label for="mb_zip" class="sound_only">우편번호</label>
                    <input type="text" name="mb_zip" id="mb_zip" class="frm_input readonly" size="5" maxlength="6">
                    <button type="button" class="btn_frmline">주소 검색</button><br>
                    <input type="text" name="mb_addr1" id="mb_addr1" class="frm_input readonly" size="60">
                    <label for="mb_addr1">기본주소</label><br>
                    <input type="text" name="mb_addr2" id="mb_addr2" class="frm_input" size="60">
                    <label for="mb_addr2">상세주소</label>
                    <br>
                    <input type="text" name="mb_addr3" id="mb_addr3" class="frm_input" size="60">
                    <label for="mb_addr3">참고항목</label>
                    <input type="hidden" name="mb_addr_jibeon">
                </td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_icon">회원아이콘</label>
                    <span class="tooltip">
                        <button type="button" class="tooltip_btn"><span class="sound_only">도움말</span></button>
                        <span class="tooltip_area">이미지 크기는 <strong>넓이 22픽셀 높이 22픽셀</strong>로 해주세요.</span>
                    </span>
                </th>
                <td colspan="3">
                    <input type="file" name="mb_icon" id="mb_icon">
                    
                </td>
            </tr>
            <tr>
                <th scope="row">메일 수신</th>
                <td>
                    <input type="radio" name="mb_mailling" value="1" id="mb_mailling_yes">
                    <label for="mb_mailling_yes">예</label>
                    <input type="radio" name="mb_mailling" value="0" id="mb_mailling_no">
                    <label for="mb_mailling_no">아니오</label>
                </td>
                <th scope="row"><label for="mb_sms_yes">SMS 수신</label></th>
                <td>
                    <input type="radio" name="mb_sms" value="1" id="mb_sms_yes">
                    <label for="mb_sms_yes">예</label>
                    <input type="radio" name="mb_sms" value="0" id="mb_sms_no">
                    <label for="mb_sms_no">아니오</label>
                </td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_open">정보 공개</label></th>
                <td colspan="3">
                    <input type="radio" name="mb_open" value="1" id="mb_open_yes">
                    <label for="mb_open_yes">예</label>
                    <input type="radio" name="mb_open" value="0" id="mb_open_no">
                    <label for="mb_open_no">아니오</label>
                </td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_signature">서명</label></th>
                <td colspan="3"><textarea  name="mb_signature" id="mb_signature"></textarea></td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_profile">자기 소개</label></th>
                <td colspan="3"><textarea name="mb_profile" id="mb_profile"></textarea></td>
            </tr>
            <tr>
                <th scope="row"><label for="mb_memo">메모</label></th>
                <td colspan="3"><textarea name="mb_memo" id="mb_memo"></textarea></td>
            </tr>

            <tr>
                <th scope="row">회원가입일</th>
                <td>2016-11-11</td>
                <th scope="row">최근접속일</th>
                <td>2016-11-11</td>
            </tr>
            <tr>
                <th scope="row">IP</th>
                <td colspan="3">11.235.233.33</td>
            </tr>
            <tr>
                <th scope="row">인증일시
                    <span class="tooltip">
                        <button type="button" class="tooltip_btn"><span class="sound_only">도움말</span></button>
                        <span class="tooltip_area">회원님이 메일을 수신할 수 없는 경우 등에 직접 인증처리를 하실 수 있습니다.</span>
                    </span>
                </th>
                <td colspan="3">
                    <input type="checkbox" name="passive_certify" id="passive_certify">
                    <label for="passive_certify">수동인증</label>

                </td>
            </tr>

            <tr>
                <th scope="row">추천인</th>
                <td colspan="3">없음</td>
            </tr>

            <tr>
                <th scope="row"><label for="mb_leave_date">탈퇴일자</label></th>
                <td>
                    <input type="text" name="mb_leave_date" id="mb_leave_date" class="frm_input" maxlength="8">
                    <input type="checkbox" id="mb_leave_date_set_today">
                    <label for="mb_leave_date_set_today">탈퇴일을 오늘로 지정</label>
                </td>
                <th scope="row">접근차단일자</th>
                <td>
                    <input type="text" name="mb_intercept_date"  id="mb_intercept_date" class="frm_input" maxlength="8">
                    <input type="checkbox" id="mb_intercept_date_set_today">
                    <label for="mb_intercept_date_set_today">접근차단일을 오늘로 지정</label>
                </td>
            </tr>

			 <c:forEach begin="1" end="10" var="j">
            	<tr>
                	<th scope="row"><label for="mb_${j}">여분 필드 ${j}</label></th>
                	<td colspan="3"><input type="text" name="extra${j}" id="mb_${j}" class="frm_input" size="30" maxlength="255"></td>
            	</tr>
            </c:forEach>
            </tbody>
            </table>
        </div>
                
        <div class="btn_fixed_top">
            <input type="submit" class="btn_submit btn"  value="확인">
        </div>
        </form>
    </div>
 
 