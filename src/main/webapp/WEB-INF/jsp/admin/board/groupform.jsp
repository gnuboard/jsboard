<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../main/head.jsp"></jsp:include>   

<c:if test="${ is_admin ne 'super' && type eq 'add'}">
	alert("최고관리자만 접근 가능합니다.")
</c:if>


<c:choose>

<c:when test="${type eq 'add'}">
	<c:set var="gr_id_attr" value="required"/>
	<c:set var="soundOnly" value="<strong class='sound_only'>필수</strong>"/>
	<c:set var="htmlTitle" value="추가"/>
	<c:set var="actionLink" value="/adm/board/addgroup"/>
	</c:when>

<c:when test="${type eq 'update'}">
	<c:set var="gr_id_attr" value="readonly"/>
	<c:set var="htmlTitle" value="수정"/>
	<c:set var="actionLink" value="/adm/board/updategroup"/>
</c:when>

<c:otherwise>
	alert("제대로된 값이 넘어오지 않았습니다.");
</c:otherwise>

</c:choose>






    
    
<div id="container">
    <!-- 중간 레이아웃 -->
    <h1 id="container_title">게시판그룹${htmlTitle}</h1>

    <div id="container_wr">
        <form action="${actionLink}" method="post" onsubmit="return fboardgroup_submit(this);">
        <div class="table_basic table_form">
            <table>
            <caption>게시판그룹${htmlTitle}</caption>
            <tbody>
            
            <tr>
                <th scope="row"><label for="gr_id">그룹 ID${soundOnly}</label></th>
                <td><input type="text" name="id" id="gr_id" class="frm_input" ${gr_id_attr} maxlength="10" value="${boardGroup.id}">
                    <a href="#" class="btn_frmline">게시판그룹 바로가기</a>
                </td>
            </tr>
            <tr>
                <th scope="row"><label for="gr_subject">그룹 제목<strong class="sound_only"> 필수</strong></label></th>
                <td>
                    <input type="text" name="subject" id="gr_subject" value="${boardGroup.subject}" required class="required frm_input" size="80">
                    <a href="#" class="btn_frmline">게시판생성</a>
                </td>
            </tr>
            <tr>
                <th scope="row"><label for="gr_device">접속기기</label>
                    <span class="tooltip">
                        <button type="button" class="tooltip_btn"><span class="sound_only">도움말</span></button>
                        <span class="tooltip_area">PC 와 모바일 사용을 구분합니다.</span>
                    </span>
                </th>
                <td>
                    <select id="gr_device" name="device">
                        <option value="both">PC와 모바일에서 모두 사용</option>
                        <option value="pc">PC 전용</option>
                        <option value="mobile">모바일 전용</option>
                    </select>
                </td>
            </tr>
            
            
            <tr>
                <th scope="row"><c:if test="${is_admin eq 'super'}">  <label for="gr_admin">그룹 관리자</label></c:if></th>
                <td>                	
                	<c:choose>
                	<c:when test="${is_admin eq 'super'}">
                    	<input type="text" id="admin" name="admin" class="frm_input" value="${boardGroup.admin}" maxlength="20">            
                	</c:when>
                	<c:otherwise>
                		
                		<input type="hidden" id="gr_admin" name="admin" value="${boardGroup.admin}">${boardGroup.admin}
                	</c:otherwise>
                	</c:choose>
                </td>
                
            </tr>
            
            
            <tr>
                <th scope="row"><label for="gr_use_access">접근회원사용</label>
                    <span class="tooltip">
                        <button type="button" class="tooltip_btn"><span class="sound_only">도움말</span></button>
                        <span class="tooltip_area">사용에 체크하시면 이 그룹에 속한 게시판은 접근가능한 회원만 접근이 가능합니다.</span>
                    </span>
                </th>
                <td>
                    <input type="checkbox" name="useAccess" value="1" id="gr_use_access" <c:if test="${boardGroup.useAccess==1}">checked</c:if>>사용
                </td>
            </tr>
            <tr>
                <th scope="row">접근회원수</th>
                <td><a href="/adm/board/boardgroupmemberlist/gr_id/${boardGroup.id}"> ${countAccessibleMembers} </a>
                </td>
            </tr>
          	
          	
          	
          <%-- 	<c:forEach begin="1" end="10" step="1" varStatus="i"> --%>
            <tr>
                <th scope="row">여분필드1</th>
                <td class="td_extra">
                    <label for="gr_1_subj">여분필드1 제목</label>
                    <input type="text" name="extra1Key" id="gr_1_subj" value="${boardGroup.extra1Key}" class="frm_input">
                    <label for="gr_1">여분필드 1 내용</label>
                    <input type="text" name="extra1Value" id="gr_1" value="${boardGroup.extra1Value}" class="frm_input" >
                </td>
            </tr>
         <%--    </c:forEach> --%>
             <tr>
                <th scope="row">여분필드2</th>
                <td class="td_extra">
                    <label for="gr_2_subj">여분필드 2 제목</label>
                    <input type="text" name="gr_2_subj" id="gr_2_subj" value="${boardGroup.extra2Key}" class="frm_input">
                    <label for="gr_2">여분필드 2 내용</label>
                    <input type="text" name="gr_2" id="gr_2" value="${boardGroup.extra2Value}"class="frm_input">
                </td>
            </tr>
            <tr>
                <th scope="row">여분필드3</th>
                <td class="td_extra">
                    <label for="gr_3_subj">여분필드 3 제목</label>
                    <input type="text" name="gr_3_subj" id="gr_3_subj" value="${boardGroup.extra3Key}" class="frm_input">
                    <label for="gr_3">여분필드 3 내용</label>
                    <input type="text" name="gr_3" id="gr_3" value="${boardGroup.extra3Value}" class="frm_input">
                </td>
            </tr>
            <tr>
                <th scope="row">여분필드4</th>
                <td class="td_extra">
                    <label for="gr_4_subj">여분필드 4 제목</label>
                    <input type="text" name="gr_4_subj" id="gr_4_subj" value="${boardGroup.extra4Key}" class="frm_input">
                    <label for="gr_4">여분필드 4 내용</label>
                    <input type="text" name="gr_4" id="gr_4" value="${boardGroup.extra4Value}" class="frm_input" >
                </td>
            </tr>
            <tr>
                <th scope="row">여분필드5</th>
                <td class="td_extra">
                    <label for="gr_5_subj">여분필드 5 제목</label>
                    <input type="text" name="gr_5_subj" id="gr_5_subj" value="${boardGroup.extra5Key}" class="frm_input">
                    <label for="gr_5">여분필드 5 내용</label>
                    <input type="text" name="gr_5" id="gr_5" value="${boardGroup.extra5Value}"class="frm_input">
                </td>
            </tr>
            <tr>
                <th scope="row">여분필드6</th>
                <td class="td_extra">
                    <label for="gr_6_subj">여분필드 6 제목</label>
                    <input type="text" name="gr_6_subj" id="gr_6_subj" value="${boardGroup.extra6Key}" class="frm_input" >
                    <label for="gr_6">여분필드 6 내용</label>
                    <input type="text" name="gr_6" id="gr_6" value="${boardGroup.extra6Value}" class="frm_input">
                </td>
            </tr>
            <tr>
                <th scope="row">여분필드7</th>
                <td class="td_extra">
                    <label for="gr_7_subj">여분필드 7 제목</label>
                    <input type="text" name="gr_7_subj" id="gr_7_subj" value="${boardGroup.extra7Key}" class="frm_input">
                    <label for="gr_7">여분필드 7 내용</label>
                    <input type="text" name="gr_7" id="gr_7" value="${boardGroup.extra7Value}" class="frm_input">
                </td>
            </tr>
            <tr>
                <th scope="row">여분필드8</th>
                <td class="td_extra">
                    <label for="gr_8_subj">여분필드 8 제목</label>
                    <input type="text" name="gr_8_subj" id="gr_8_subj" value="${boardGroup.extra8Key}" class="frm_input">
                    <label for="gr_8">여분필드 8 내용</label>
                    <input type="text" name="gr_8" id="gr_8" value="${boardGroup.extra8Value}" class="frm_input">
                </td>
            </tr>
            <tr>
                <th scope="row">여분필드9</th>
                <td class="td_extra">
                    <label for="gr_9_subj">여분필드 9 제목</label>
                    <input type="text" name="gr_9_subj" id="gr_9_subj" value="${boardGroup.extra9Key}" class="frm_input">
                    <label for="gr_9">여분필드 9 내용</label>
                    <input type="text" name="gr_9" id="gr_9" value="${boardGroup.extra9Value}" class="frm_input">
                </td>
            </tr>
            <tr>
                <th scope="row">여분필드10</th>
                <td class="td_extra">
                    <label for="gr_10_subj">여분필드 10 제목</label>
                    <input type="text" name="gr_10_subj" id="gr_10_subj" value="${boardGroup.extra10Key}" class="frm_input">
                    <label for="gr_10">여분필드 10 내용</label>
                    <input type="text" name="gr_10" id="gr_10" value="${boardGroup.extra10Value}" class="frm_input">
                </td>
            </tr> 

            </tbody>
            </table>
        </div>
        
        <div class="btn_fixed_top">
        	<input type="hidden" name="_method" value=""/>
        	<c:if test="${type eq 'add'}">
            <input type="submit" class="btn_submit btn"  value="추가" onclick="document.pressed='추가'">
            </c:if>
            
            <c:if test="${type eq 'update'}">
            <input type="submit" class="btn_submit btn"  value="수정" onclick="document.pressed='수정'">
            </c:if>
            
        </div>
        </form>
	</div>
	
	
	
<script>
function fboardgroup_submit(f)
{
    
	 if(document.pressed == "추가") {
		 
		 
	 }else if (document.pressed == "수정") {
		 
	    $("input:hidden[name=_method]").val("PUT");
	 }
}
</script>	
	
	
	
	
	
<jsp:include page="../main/tail.jsp"></jsp:include>    