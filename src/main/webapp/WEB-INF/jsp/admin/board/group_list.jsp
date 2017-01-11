<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="../main/head.jsp"></jsp:include>

<div id="container">
    <!-- 중간 레이아웃 -->
    <h1 id="container_title">게시판그룹설정</h1>

    <div id="container_wr">
        <div class="local_desc">
            <p>
                접근사용 옵션을 설정하시면 관리자가 지정한 회원만 해당 그룹에 접근할 수 있습니다.<br>접근사용 옵션은 해당 그룹에 속한 모든 게시판에 적용됩니다.
            </p>
        </div>
        <div class="local_wr">
            <div class="local_ov">
                <a href="#" class="btn_ov02">전체목록</a>
                <span class="btn_ov01"><span class="ov_txt">전체그룹</span><span class="ov_num"> ${countBoardGroupsList}개</span></span>
            </div>
            <form id="fsearch" name="fsearch" action="" class="local_sch" method="get">
            
            <label for="sfl" class="sound_only">검색대상</label>
            <select name="sfl" id="sfl">
                <option value="subject">제목</option>
                <option value="id">ID</option>
                <option value="admin">그룹관리자</option>
            </select>
            <label for="stx" class="sound_only">검색어<strong class="sound_only"> 필수</strong></label>
            <input type="text" name="stx" id="stx" value="${stx}" required class="sch_input">
            <input type="submit" class="btn_sch" value="검색">
            </form>
        </div>

        <form name="fgrouplistform" action="/adm/board/delete/group" onsubmit="return fboardgrouplist_submit(this);" method="POST">
        <input type="hidden" name="_method" value=""/>
        <div class="table_basic table_01">
            <table>
                <caption>게시판그룹설정</caption>
                <thead>
                <tr>
                    <th scope="col">
                        <label for="chkall" class="sound_only">게시판 전체</label>
                        <input type="checkbox" name="chkall" id="chkall" value="1" onclick="check_all(this.form)">
                    </th>
                    <th scope="col">그룹아이디</th>
                    <th scope="col">제목</th>
                    <th scope="col">그룹관리자</th>
                    <th scope="col">게시판</th>
                    <th scope="col">접근사용</th>
                    <th scope="col">접근회원수</th>
                    <th scope="col">출력순서</th>
                    <th scope="col">접속기기</th>
                    <th scope="col">관리</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                <c:when test="${! empty allBoardGroupsList}">
                <c:forEach var="boardGroup" items="${allBoardGroupsList}" varStatus="i">
                <tr>
                    <td class="td_chk">
                    	<input type="hidden" name="id[${i.index}]" value="${boardGroup.id}">
                        <label class="sound_only" for="chk_${i.index}">그룹제목</label>
                        <input type="checkbox" name="chk[]" value="${boardGroup.id}" id="chk_${i.index}">
                    </td>
                    <td><a href="#">${boardGroup.id}</a></td>
                    <td class="td_input">
                        <label class="sound_only">${boardGroup.id}</label>
                        <input type="text" class="tbl_input" name="subject[${i.index}]" value="${boardGroup.subject}">
                    </td>
                    <td class="td_input">
                    	<c:choose>
                    	<c:when test="${is_admin eq 'super' }">
                        <label class="sound_only">${boardGroup.admin}</label>
                        <input type="text" class="tbl_input" size="10" maxlength="20" name="admin[${i.index}]" value="${boardGroup.admin}">
                        </c:when>
                        <c:otherwise>
                        <input type="hidden" name="admin[${i.index}]" value="${boardGroup.admin}">${boardGroup.admin}
                        </c:otherwise>
                        </c:choose>
                    </td>
                    <td><a href="/adm/board/board_list/${boardGroup.id}">${boardGroup.countIncludedBoards}개</a></td>
                    <td class="td_chk2">
                        <label class="sound_only" for="gr_use_access_${i.index}">접근회원 사용</label>
                        <input type="checkbox" value="1" name="useAccess" id="gr_use_acces_${i.index}" <c:if test="${boardGroup.useAccess==1}">checked</c:if> > 
                    </td>
                    <td><a href="/adm/board/groupmemberlist/${boardGroup.id}">${boardGroup.countAccessibleMembers}</a></td>
                    <td>
                        <label class="sound_only" >메인메뉴 출력순서</label>
                        <input type="text" class="tbl_input" name="order[${i.index}]"size="2" value="${boardGroup.order}">
                    </td>
                    <td class="td_select">
                        <label class="sound_only" for="device_${i.index}">접속기기</label>
                        <select name="device_${i.index}">
                            <option value="both">모두사용</option>
                            <option value="pc">PC</option>
                            <option value="mobile">모바일</option>
                        </select>
                    </td>
                    <td class="td_mng_s td_mng"><a href="/adm/board/form/updategroup/${boardGroup.id}" class="btn_03">수정</a></td>
                </tr>
				</c:forEach>
				</c:when>
				
				<c:when test="${empty allBoardGroupsList }">
					<tr><td colspan="10" class="empty_table">자료가 없습니다.</td></tr>
				</c:when>
				</c:choose>	
                               
                </tbody>
            </table>
        </div>
        <div class="btn_fixed_top">
            <input type="submit" name="act_button" value="선택수정" class="btn_02 btn" onclick="document.pressed=this.value">
            <input type="submit" name="act_button" value="선택삭제" class="btn_02 btn" onclick="document.pressed=this.value">
            <a href="/adm/board/form/addgroup" class="btn_submit btn">게시판그룹 추가</a>
        </div>
        </form>
        <div class="pagination">
            <h2>페이징</h2>
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
                <a href="#">9</a>
                <a href="#" class="next">다음</a>
                <a href="#" class="last">맨 마지막으로</a>
            </div>
        </div>        
    </div>
    
<script>
function fboardgrouplist_submit(f)
{
    if (!is_checked("chk[]")) {
        alert(document.pressed+" 하실 항목을 하나 이상 선택하세요.");
        return false;
    }

    if(document.pressed == "선택삭제") {
        if(!confirm("선택한 자료를 정말 삭제하시겠습니까?")) {
            return false;
        }
        $("input:hidden[name=_method]").val("DELETE");
    }else if (document.pressed == "선택수정") {
    	$("input:hidden[name=_method]").val("PUT");
    }
   
    return true;
}
</script>

<jsp:include page="../main/tail.jsp"></jsp:include>