<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>




<body>
<jsp:include page="../main/head.jsp"></jsp:include>

<div id="container">
    <!-- 중간 레이아웃 -->
    <h1 id="container_title">회원관리</h1>

    <div id="container_wr">
        
        <div class="local_desc">
            <p>
                회원자료 삭제 시 다른 회원이 기존 회원아이디를 사용하지 못하도록 회원아이디, 이름, 닉네임은 삭제하지 않고 영구 보관합니다.
            </p>
        </div>
        <div class="local_wr">
            <div class="local_ov">
                <a href="#" class="btn_ov02">전체목록</a>
                <span class="btn_ov01"><span class="ov_txt">총회원수</span><span class="ov_num"><fmt:formatNumber value="${countallmembers}"  pattern="#,###.##"/>명</span></span>
                <span class="btn_ov01"><a href="/adm/member/list?sst=interceptDate&amp;sod=desc&amp;sfl=${sfl}&amp;stx=${stx}" class="ov_txt"><span class="ov_num">차단 <fmt:formatNumber value="${countblockedmembers}" pattern="#,###.##"/>명</span></a></span>
                <span class="btn_ov01"><a href="/adm/member/list?sst=leaveDate&amp;sod=desc&amp;sfl=${sfl}&amp;stx=${stx}" class="ov_txt"><span class="ov_num"> 탈퇴 <fmt:formatNumber value="${countretiredmembers}" pattern="#,###.##"/>명</span></a></span>
            </div>
          
          
          
            <form id="fsearch" name="fsearch" class="local_sch" method="post">
           	 	<label for="sfl" class="sound_only">검색대상</label>
           			 <select name="sfl" id="sfl">
		                <option value="memberId">회원아이디</option>
		                <option value="nick">닉네임</option>
		                <option value="name">이름</option>
		                <option value="level">권한</option>
		                <option value="email">E-MAIL</option>
		                <option value="tel">전화번호</option>
		                <option value="hp">휴대폰번호</option>
		                <option value="point">포인트</option>
		                <option value="datetime">가입일시</option>
		                <option value="ip">IP</option>
		                <option value="recommend">추천인</option>
		            </select>
           		 <label for="stx" class="sound_only">검색어<strong class="sound_only"> 필수</strong></label>
           		 <input type="text" name="stx" id="stx" required class="sch_input">
           		 <input type="submit" class="btn_sch" value="검색">

            </form>
        </div><!--   local_wr  -->
       
       
       
        <form>
        <div id="member_list" class="table_basic table_01 table_2line">
            <table border=1>
                <caption>회원관리 리스트</caption>
                <thead>
                <tr>
                    <th scope="col" rowspan="2">
                        <label for="chkall" class="sound_only">회원 전체</label>
                        <input type="checkbox" name="chkall" id="chkall">
                    </th>
                    <th scope="col" colspan="2">아이디</th>
                    <th scope="col" rowspan="2">본인확인</th>
                    <th scope="col">메일인증</th>
                    <th scope="col">메일수신</th>
                    <th scope="col">정보공개</th>
                    <th scope="col">휴대폰</th>
                    <th scope="col">상태</th>
                    <th scope="col">최종접속</th>
                    <th scope="col">포인트</th>
                    <th scope="col" rowspan="2">관리</th>
                </tr>
                 <tr>
                    <th scope="col">이름</th>
                    <th scope="col">닉네임</th>
                    <th scope="col">성인인증</th>
                    <th scope="col">SMS수신</th>
                    <th scope="col">접근차단</th>
                    <th scope="col">전화번호</th>
                    <th scope="col">권한</th>
                    <th scope="col">가입일</th>
                    <th scope="col">접근그룹</th>
                </tr>
                </thead>
                <tbody>
                
                
                <c:choose>
                	<c:when test="${! empty memberslist}">
                		<c:forEach var="member" items="${memberslist}" varStatus="i">
                		
                		
                		<!--수정버튼 링크 -->
                        <c:choose>
							<c:when test="${is_admin eq 'group'}">
								<c:set var="s_mod" value="" />
							</c:when>
							<c:otherwise>								
								<c:set var="s_mod" value="<a href='/adm/member/form/update/memberId/${member.memberId}' class='btn_03'>수 정</a>"/>
							</c:otherwise>
						</c:choose>
						
						
						<!--그룹버튼링크  -->                       
                        <c:set var="s_grp" value="<a href='/adm/member/accessiblegroups/memberId/${member.memberId}' class='btn_02'>그 룹</a>"/>		
						
						<!--탈퇴일  -->
						<c:if test="${! empty member.leaveDate}">
							<c:set var="leave_date" value="서버에 저장된 현재 시간" />
						</c:if>

						<!--차단일  -->
						<c:if test="${! empty member.interceptDate}">
							<c:set var="intercept_date" value="Ymd 타입으로 서버 시간" />
						</c:if>
                		
                		<!--$mb_nick = get_sideview($row['mb_id'], get_text($row['mb_nick']), $row['mb_email'], $row['mb_homepage']); -->
                		
                		
                		<c:choose>
                			<c:when test="${! empty member.leaveDate }">
                				<c:set var="leaveMsg" value="<span class='td_txt_color1'>탈퇴</span>"></c:set>
                			</c:when>
                			
                			<c:when test="${! empty member.interceptDate}">
                				<c:set var="interceptMsg" value="<span class='td_txt_color1'>차단됨</span>"></c:set>
                				<c:set var="interceptTitle" value="차단해제"></c:set>
                			</c:when>
                		</c:choose>
                		
                		<c:if test="${ empty interceptTitle }">
                			<c:set var="interceptTitle" value="차단하기"></c:set>
                		</c:if>        		
                		
                		
                
                <tr>
                    <td rowspan="2" class="td_chk">
                        <label for="chk_${i}" class="sound_only">${member.name}</label>
                        <input type="checkbox" name="chk[]" id="chk_${i}">
                    </td>
                    <td colspan="2" class="td_left">${member.memberId}</td>
                    <td rowspan="2">
                        <input type="radio" name="mb_certify_${i}" value="ipin" id="mb_certify_ipin_${i}" <c:if test="${member.certify eq 'ipin'}">checked</c:if>  >
                        <label for="mb_certify_ipin_${i}">아이핀</label><br>
                        <input type="radio" name="mb_certify_${i}" value="hp" id="mb_certify_hp_${i}" <c:if test="${member.certify eq 'hp'}">checked</c:if> >
                        <label for="mb_certify_hp_${i}">휴대폰</label>
                    </td>
                    <td><c:if test="${fn:substring(member.emailCertify,0,2)=='20'}"><strong class="td_txt_color_1">인증</strong></c:if> <c:if test="${fn:substring(member.emailCertify,0,2)!='20'}">미인증</c:if></td>
                    <td>
                        <label for="mb_mailling_${i}" class="sound_only">메일수신</label>
                        <input type="checkbox" id="mb_mailling_[${i}]" value="1" <c:if test="${! empty member.mailling}">checked</c:if> >
                    </td>
                    <td>
                        <label for="mb_open_${i}" class="sound_only">정보공개</label>
                        <input type="checkbox" id="mb_open_[${i}]" value="1" <c:if test="${! empty member.open}">checked</c:if>  >
                    </td>
                    <td class="td_left">${member.hpNo }</td>
                    <td>
                    	<c:choose>
                      		<c:when test="${! empty leaveMsg || ! empty interceptMsg}">${leaveMsg}  ${interceptMsg}</c:when> 
           			  		<c:otherwise>정상</c:otherwise>
           			  	</c:choose> 
            		</td>
                    <td class="td_date">${fn:substring(member.todayLogin,2,10)}</td>
                    <td class="td_num"><a href="adm/member/pointlist?sfl=memberId&amp;stx=${member.memberId}"><fmt:formatNumber value="${member.point}" pattern="#,###.##" /></a></td>
                    <td rowspan="2" class="td_mng td_mng_s">                       
                    
                       ${s_mod}<!--수정버튼  -->
                       ${s_grp}<!--그룹버튼  --> 
                    </td>
                </tr>
                 <tr>
                    <td class="td_left">${member.name}</td>
                    <td class="td_left">${member.nick}</td>
                    <td>
                        <label for="mb_adult_${i}" class="sound_only">성인인증</label>
                        <input type="checkbox" name="mb_adult[${i}]" value="1" id="mb_adult_${i}" <c:if test="${! empty member.adult}">checked</c:if>>
                    </td>
                    <td>
                        <label for="mb_sms_${i}" class="sound_only">SMS수신</label>
                        <input type="checkbox" name="mb_sms[${i}]" id="mb_sms_${i}" value="1" <c:if test="${! empty member.sms}">checked</c:if>>
                    </td>
                    <td>
                        <label for="mb_intercept_date_${i}" class="sound_only">접근차단</label>
                        <input type="checkbox" <c:if test="${! empty member.interceptDate}">checked</c:if> value="${member.interceptDate}" id="mb_intercept_date_${i}">
                    </td>
                    <td class="td_left">${member.tel}<!--<?php echo get_text($row['mb_tel']);  --></td>
                    
                    <td>
                		${member.level}
                        <!--<?php echo get_member_level_select("mb_level[$i]", 1, $member['mb_level'], $row['mb_level']) ?>  --> 
                    </td>
                   
                    <td class="td_date">${fn:substring(member.datetime,2,10)}</td>
                    <td>${member.countGroupMember}</td>
                </tr>
               
               		</c:forEach>
              	 </c:when>
              	 <c:when test="${empty memberlist}">
					 <tr><td colspan="12" class="empty_table">자료가 없습니다.</td></tr>
				</c:when>
              	 
               </c:choose>
               
               
               
                </tbody>
            </table>
        </div>
        <div class="btn_fixed_top">
            <input type="submit" name="act_button" value="선택수정" class="btn_02 btn">
            <input type="submit" name="act_button" value="선택삭제" class="btn_02 btn">
            <a href="/adm/member/form/add" id="member_add" class="btn_submit btn">회원추가</a>
        </div>
        </form>
        
        <div class="pagination">
           
            <div class="pg_wr">
                <a href="#" class="first">맨 처음으로</a>
                <a href="#" class="prev">이전</a>
                <a href="#">1</a>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">4</a>
                <a href="#">5</a>
                <a href="#" class="active">6</a>
                <a href="#">7</a>
                <a href="#">8</a>
                <a href="#" class="next">다음</a>
                <a href="#" class="last">맨 마지막으로</a>
            </div>
        </div>

    </div>

 





<script>
	function fmemberlist_submit(f) {
		if (!is_checked("chk[]")) {
			alert(document.pressed + " 하실 항목을 하나 이상 선택하세요.");
			return false;
		}

		if (document.pressed == "선택삭제") {
			if (!confirm("선택한 자료를 정말 삭제하시겠습니까?")) {
				return false;
			}
		}

		return true;
	}
</script>

<jsp:include page="../main/tail.jsp"></jsp:include>
