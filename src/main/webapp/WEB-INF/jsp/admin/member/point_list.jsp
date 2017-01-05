<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../main/head.jsp"></jsp:include>



<div id="container">
    <!-- 중간 레이아웃 -->
    <h1 id="container_title">포인트관리</h1>

    <div id="container_wr">

        <div class="local_wr">
            <div class="local_ov">
                <a href="${pageContext.request.contextPath}/adm/member/pointlist" class="btn_ov02">전체목록</a>
                <span class="btn_ov01"><span class="ov_txt">전체 </span><span class="ov_num"> ${countPointlist} </span></span>
                <span class="btn_ov01"><span class="ov_txt">전체포인트 합계 </span><span class="ov_num"> <fmt:formatNumber value="${totalPoint}" pattern="#,###.##"/>포인트 </span></span>
            </div>                                                                                  
            <form id="fsearch" name="fsearch" class="local_sch" method="post">

            <label for="sfl" class="sound_only">검색대상</label>
            <select name="sfl" id="sfl">
                <option value="memberId">회원아이디</option>
                <option value="contetn">내용</option>                
            </select>
            
            <label for="stx" class="sound_only">검색어<strong class="sound_only"> 필수</strong></label>
            <input type="text" name="stx" id="${stx}" required class="sch_input">
            <input type="submit" class="btn_sch" value="검색">        
            </form>
        </div>

        <form name="fpointlist" id="fpointlist" method="post" action="/adm/member/pointlist/delete" onsubmit="return fpointlist_submit(this);">
        
			<input type="hidden" name="sst" value="${sst}">
			<input type="hidden" name="sod" value="${sod}">
			<input type="hidden" name="sfl" value="${sfl}">
			<input type="hidden" name="stx" value="${stx}">
			<input type="hidden" name="page" value="${page}">
			<input type="hidden" name="token" value="">
        <div class="table_01 table_basic">
            <table>
            <caption>포인트관리 목록</caption>
            <thead>
            <tr>
                <th scope="col">
                    <label class="sound_only">포인트 내역 전체</label>
                    <input type="checkbox">
                </th>
                <th scope="col">회원아이디</th>
                <th scope="col">이름</th>
                <th scope="col">닉네임</th>
                <th scope="col">포인트 내용</th>
                <th scope="col">포인트</th>
                <th scope="col">일시</th>
                <th scope="col">만료일</th>
                <th scope="col">포인트합</th>
            </tr>
            </thead>          
            <tbody>
            
            <c:forEach var="pointContent" items="${allPointContent}">
            	   
            	
            	
            	
            <tr>
                <td class="td_chk">
                    <label class="sound_only">2017-01-03 첫로그인 내역</label>
                    <input type="checkbox">
                </td>
                <td class="td_left">id123</td>
                <td class="td_left">이름</td>
                <td class="td_left">닉네임</td>
                <td class="td_left">2017-01-03 첫로그인</td>
                <td class="td_num">100</td>
                <td class="td_datetime">2017-01-03 09:07:31</td>
                <td class="td_datetime2">2017-01-11</td>
                <td class="td_num">13,680</td>
            </tr>
          	
          	</c:forEach>
          
            </tbody>
            </table>
        </div>
        <div class="btn_confirm">        	
            <input type="submit" value="선택삭제" class="btn_02 btn" onclick="document.pressed=this.value">
        </div>
        </form>

        <form>
        <div class="add_form">
            <h2 class="h2_frm">개별회원 포인트 증감 설정</h2>
            <div class="table_basic table_form">
                <table>
                    <caption>개별회원 포인트 증감 설정</caption>
                    <tbody>
                    <tr>
                        <th scope="row"><label>회원아이디<strong class="sound_only">필수</strong></label></th>
                        <td><input type="text" class="required frm_input" required></td>
                    </tr>
                    <tr>
                        <th scope="row"><label>포인트 내용<strong class="sound_only">필수</strong></label></th>
                        <td><input type="text" required class="required frm_input" size="80"></td>
                    </tr>
                    <tr>
                        <th scope="row"><label>포인트<strong class="sound_only">필수</strong></label></th>
                        <td><input type="text" required class="required frm_input"></td>
                    </tr>
                    <tr>
                        <th scope="row"><label>포인트 유효기간</label></th>
                        <td><input type="text" class="frm_input" size="5"> 일</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="btn_confirm">
                <input type="submit" value="추가" class="btn_submit btn">
            </div>
        </div>
        </form>
    </div>
    
    
<script>
function fpointlist_submit(f)
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