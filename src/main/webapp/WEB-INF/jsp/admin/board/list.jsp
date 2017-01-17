<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<jsp:include page="../main/head.jsp"></jsp:include>




<div id="container">
    <!-- 중간 레이아웃 -->
    <h1 id="container_title">게시판관리</h1>

    <div id="container_wr">

        <div class="local_wr">
            <div class="local_ov">
                <a href="/adm/board/list" class="btn_ov02">전체목록</a>
                <span class="btn_ov01"><span class="ov_txt">생성된 게시판수</span><span class="ov_num"><fmt:formatNumber value="${countBoards}" pattern="#,###.##"/>개</span></span>
            </div>
            
            <form id="fsearch" name="fsearch" class="local_sch" method="get">
            <label for="sfl" class="sound_only">검색대상</label>
            <select name="sfl" id="sfl">
                <option value="id">TABLE</option>
                <option value="subject">제목</option>
                <option value="groupId">그룹ID</option>
            </select>
            <label for="stx" class="sound_only">검색어<strong class="sound_only"> 필수</strong></label>
            <input type="text" name="stx" id="stx" required class="sch_input">
            <input type="submit" class="btn_sch" value="검색">

            </form>
        </div>
        <form name="fboardlist" action="/adm/board/updateordelete" method="post" onsubmit="return fboardlist_submit(this);">
        <input type="hidden" name="_method" value="">
        <div id="board_list" class="table_basic table_01">
            <table>
                <caption>회원관리 리스트</caption>
                <thead>
                <tr>
                    <th scope="col">
                        <label for="chkall" class="sound_only">게시판 전체</label>
                        <input type="checkbox" name="chkall" id="chkall" onclick="check_all(this.form)">
                    </th>
                    <th scope="col">그룹</th>
                    <th scope="col">TABLE</th>
                    <th scope="col">스킨</th>
                    <th scope="col">모바일스킨</th>
                    <th scope="col">제목</th>
                    <th scope="col">읽기P</th>
                    <th scope="col">쓰기P</th>
                    <th scope="col">댓글P</th>
                    <th scope="col">다운P</th>
                    <th scope="col">SNS<br>사용</th>
                    <th scope="col">검색<br>사용</th>
                    <th scope="col">출력순서</th>
                    <th scope="col">접속기기</th>
                    <th scope="col">관리</th>
                </tr>
                </thead>
                <tbody>
                
                <c:choose>
                <c:when test="${! empty allBoardGroupsList}">
                <c:forEach var="board" items="${allBoardGroupsList}" varStatus="i">
                
                
                <tr>
                    <td  class="td_chk">
                    	<input type="hidden" name="list[${i.index}].id" value="${board.id}">
                        <input type="checkbox" name="chk" value="${i.index}" id="chk_${i.index}"> 
                        <label class="sound_only" for="chk_${i.index}">${board.subject}</label>
                    </td>
         
                    <td>
                    	<c:choose>
                    	<c:when test="${is_admin eq 'super'}">
                        <select>
                            <option>선택</option>
                        </select>
                        </c:when>
                        <c:otherwise>
                        <input type="hidden" name="list[${i.index}].groupId" value="${board.groupId}">${board.groupId}	
                        </c:otherwise>
                        </c:choose>
                        
                    </td>
                    
                    <td class="td_left">
                    	<input type="hidden" name="list[${i.index}].table" value="${board.table}">
                    	<a href="#">${board.table}</a>
                    </td>
                    
                    <td>
                        <select name="list[${i.index}].skin">
                            <option value="${board.skin}" selected="selected">${board.skin}</option>
                        </select>
                    </td>
                    <td>
                        <select name="list[${i.index}].mobileSkin">
                            <option value="${board.mobileSkin}" selected="selected">${board.mobileSkin}</option>
                        </select>
                    </td>
                    <td>
                        <label class="sound_only">게시판 제목<strong class="sound_only"> 필수</strong></label>
                        <input type="text"  name="list[${i.index}].subject" value="${board.subject}" id="bo_subject_${i.index}" required class="required tbl_input bo_subject " size="10">
                    </td>
                    <td class="td_numsmall">
                        <label class="sound_only">읽기 포인트</label>
                        <input type="text" name="list[${i.index}].readPoint" value="${board.readPoint}" id="subject_${i.index}"  required class="tbl_input" size="3">
                    </td>
                    <td class="td_numsmall">
                        <label class="sound_only">쓰기 포인트</label>
                        <input type="text" name="list[${i.index}].writePoint" value="${board.writePoint}" id="bo_write_point_${i.index}"  class="tbl_input" size="3">
                    </td>
                    <td class="td_numsmall">
                        <label class="sound_only">댓글 포인트</label>
                        <input type="text" name="list[${i.index}].commentPoint" value="${board.commentPoint}" id="bo_comment_point_${i.index}" class="tbl_input" size="3">
                    </td>
                    <td class="td_numsmall">
                        <label class="sound_only">다운 포인트</label>
                        <input type="text" name="list[${i.index}].downloadPoint" value="${board.downloadPoint}" id="bo_download_point_${i.index}" class="tbl_input" size="3">
                    </td>
                    <td class="td_chk">
                        <label class="sound_only">SNS 사용</label>
                        <input type="checkbox" name="list[${i.index}].useSns" value="1" id="bo_use_sns_${i.index}" <c:if test="${board.useSns eq 1 }">checked</c:if> >
                    </td>
                    <td class="td_chk">
                        <label class="sound_only">검색 사용</label>
                        <input type="checkbox" name="list[${i.index}].useSearch" value="1" id="bo_use_search_${i.index}" <c:if test="${board.useSearch eq 1}">checked</c:if>  >
                    </td>
                    <td class="td_numsmall">
                        <label class="sound_only">출력 순서</label>
                        <input type="text" name="list[${i.index}].order" value="${board.order}" id="bo_order_${i.index}" class="tbl_input" size="3">
                    </td>
                    <td class="td_select">
                        <label class="sound_only">접속기기</label>
                        <select name="list[${i.index}].device" id="bo_device_${i.index}">
                            <option value="both">모두</option>
                            <option value="pc">PC</option>
                            <option value="mobile">모바일</option>
                        </select>
                    </td>
                    <td class="td_mng td_mng_m">
                   		 <a href="/adm/board/form/update" class="btn_01">수정</a>
                   		 <a href="/adm/board/form/copy" class="btn_02 board_copy" target="win_board_copy">복사</a>
                   	</td>
                </tr>
                
                
                <input type="hidden" name="list[${i.index}].admin" value="${board.admin}">
                <input type="hidden" name="list[${i.index}].listLevel" value="${board.listLevel}">
                <input type="hidden" name="list[${i.index}].readLevel" value="${board.readLevel}">
                <input type="hidden" name="list[${i.index}].categoryList" value="${board.categoryList}">
                
                
            
            
                
                </c:forEach>
                </c:when>
                <c:when test="${empty allBoardGroupsList }">         
                
                <tr><td colspan="15" class="empty_table">자료가 없습니다.</td></tr>
                </c:when>
                </c:choose>
                </tbody>
            </table>
        </div>
        <div class="btn_fixed_top">
            <input type="submit" value="선택수정" class="btn_02 btn" onclick="document.pressed=this.value">
           <%--  <c:if test="${is_admin eq 'super'}"> --%>
            <input type="submit" value="선택삭제" class="btn_02 btn" onclick="document.pressed=this.value">
            <%-- </c:if> --%>
            <a href="/adm/board/form/add" class="btn_submit btn">게시판 추가</a>
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
                <a href="#" class="next">다음</a>
                <a href="#" class="last">맨 마지막으로</a>
            </div>
        </div>
    </div>
<script>
    $(function(){
        $(".board_copy").click(function(){
            window.open(this.href, "win_board_copy", "left=10,top=10,width=500,height=400");
            return false;
        });
    });
    
    function fboardlist_submit(f)
    {
        if (!is_checked("chk")) {
            alert(document.pressed+" 하실 항목을 하나 이상 선택하세요.");
            return false;
        }

        if(document.pressed == "선택삭제") {
            if(!confirm("선택한 자료를 정말 삭제하시겠습니까?")) {
                return false;
            }
            alert("게시판삭제");
            $("input:hidden[name=_method]").val("DELETE");
        }else if(document.pressed == "선택수정"){
        	$("input:hidden[name=_method]").val("PUT");
        }

        return true;
    }
</script>



<jsp:include page="../main/tail.jsp"></jsp:include>