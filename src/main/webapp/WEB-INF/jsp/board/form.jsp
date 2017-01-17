<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>${article.subject}</title>
	<link rel="stylesheet" href="/css/board.css">
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>

$(document).ready(function(){
	
	var saveType = '<%= request.getAttribute("saveType") %>';
	if(saveType == "answer") {
		// 원 글 내용에 줄마다 댓글 무늬 표시
		var text1 = "\n > \n > \n > ";
		var text2 = "\n > \n >"; 
		var originText = $('#content').text().replace(/\n/g, "\n > ");
		$('#content').html(text1);
		$('#content').append(originText);
		$('#content').append(text2);
	}
	
});

function submitSaveForm() {
	// form에 입력이 없을 시 기본값 셋팅
	if( $("#email").val() == null ) $("#email").val("");
	if( $("#homepage").val() == null ) $("#homepage").val("");
	if( $("#link11").val() == null ) $("#link11").val("");
	if( $("#link12").val() == null ) $("#link12").val("");
	if( $("#checkOption").is(":checked") ) {
		var optionValue = $("#checkOption").val();
		$("#option").val(optionValue);
	} else {
		$("#option").val("");
	}
	// http method 설정
	if(${saveType == "update"}) {	// 글 수정
		$("input:hidden[name=_method]").val("put");
	} else {						// 글 쓰기, 답변 글 쓰기
		$("input:hidden[name=_method]").val("post");
	}
}
</script>
<body>
<div id="container">
    <h1 class="container_tit">게시판01
    	<c:choose>
	    	<c:when test='${saveType eq "insert"}'> 
	    		글쓰기
	    	</c:when>
	    	<c:when test='${saveType eq "answer"}'> 
	    		글쓰기
	    	</c:when>
	    	<c:otherwise>
	    		글수정
	    	</c:otherwise>
    	</c:choose>
    </h1>

    <section id="bo_w">
        <h2>게시판01 
        <c:choose>
	    	<c:when test='${saveType eq "insert"}'> 
	    		글쓰기
	    	</c:when>
	    	<c:when test='${saveType eq "answer"}'> 
	    		글쓰기
	    	</c:when>
	    	<c:otherwise>
	    		글수정
	    	</c:otherwise>
    	</c:choose>
        </h2>
        <!-- 게시물 작성/수정 시작 { -->
   		<form action="/board/save" name="boardForm" id="boardForm" method="post" enctype="multipart/form-data">
        
        	<!-- http method 값 셋팅 -->
			<input type="hidden" name="_method"/>
	        <!-- 기본 입력 값 셋팅 -->
			<input type="hidden" name="option" id="option" />					<!-- V -->
			<input type="hidden" name="commentReply" value=""/>
			<input type="hidden" name="reply" value=""/>						<!-- V -->
			<input type="hidden" name="facebookUser" value=""/>
			<input type="hidden" name="twitterUser" value=""/>
			<input type="hidden" name="extra1" value=""/>
			<input type="hidden" name="extra2" value=""/>
			<input type="hidden" name="extra3" value=""/>
			<input type="hidden" name="extra4" value=""/>
			<input type="hidden" name="extra5" value=""/>
			<input type="hidden" name="extra6" value=""/>
			<input type="hidden" name="extra7" value=""/>
			<input type="hidden" name="extra8" value=""/>
			<input type="hidden" name="extra9" value=""/>
			<input type="hidden" name="extra10" value=""/>

			<input type="hidden" name="boardName" value="${boardName}"/>
			<input type="hidden" name="saveType" value="${saveType}"/>
			<c:if test='${saveType ne "insert"}'>
				<input type="hidden" name="currentCategory" value="${currentCategory}"/>
				<input type="hidden" name="currentPage" value="${currentPage}"/>
			</c:if>
			<c:if test='${saveType eq "update"}'>
				<input type="hidden" name="id" value="${article.id}"/>

			</c:if>
			<c:choose>
				<c:when test='${saveType eq "answer"}'>
					<input type="hidden" name="isReply" value="1"/>
					<input type="hidden" name="num" value="${article.num}"/>
					<!-- 기준 원글 id, 댓글에서 매핑하던  baseCommentId 사용 -->
					<input type="hidden" name="baseCommentId" value="${baseArticleId}"/>
				</c:when>
				<c:otherwise>
					<input type="hidden" name="isReply" value="0"/>
				</c:otherwise>
			</c:choose>
		
        <div class="table_basic table_form">
            <table>
            <tbody>
             <tr>
                <th scope="row"><label>이름<strong class="sound_only">필수</strong></label></th>
                <td><input type="text" name="name" id="name" required class="frm_input required" size="10" maxlength="20" value="${article.name}"></td>
            </tr>
            
            <tr>
                <th scope="row"><label>비밀번호<strong class="sound_only">필수</strong></label></th>
                <td><input type="password" name="password" id="password" required class="frm_input required" maxlength="20" value="${article.password}"></td>
            </tr>
            
            <tr>
                <th scope="row"><label>이메일</label></th>
                <td><input type="text" name="email" id="email" class="frm_input" size="50" maxlength="100" value="${article.email}"></td>
            </tr>
            
            <tr>
                <th scope="row"><label>홈페이지</label></th>
                <td><input type="text" name="homepage" id="homepage" class="frm_input" size="50" value="${article.homepage}"></td>
            </tr>
            
            <tr>
                <th scope="row">옵션</th>
                <td><input type="checkbox" name="checkOption" id="checkOption" value="html1"><label>html</label></td>
            </tr>
            
            <tr>
                <th scope="row"><label>분류</label></th>
                <td>
                    <select name="categoryName" id="categoryName">
                        <option value="">선택하세요</option>
                        <c:forEach var="category" items="${categoryList}">
                       		<c:choose> 
	                       		<c:when test="${category eq article.categoryName }">
	                       			<option selected="selected" value="${category}">${category}</option>
	                       		</c:when>
	                       		<c:when test="${category eq '' }">
	                       			<option value="">분류 없음</option>
	                       		</c:when>
	                       		<c:otherwise>
	                       			<option value="${category}">${category}</option>
	                       		</c:otherwise>
                       		</c:choose>
                       	</c:forEach>
                    </select>
                </td>
            </tr>            
            <tr>
                <th scope="row"><label>제목<strong class="sound_only">필수</strong></label></th>
                <td>
                    <input type="text" name="subject" id="subject" required class="frm_input required frm_input_full" size="50" value="${article.subject}">
                </td>
            </tr>

            <tr>
                <th scope="row"><label>내용<strong class="sound_only">필수</strong></label></th>
                <td class="wr_content">
                    <textarea name="content" id="content">${article.content}</textarea>
                 </td>
            </tr>

            <tr>
                <th scope="row"><label>링크 #1</label></th>
                <td><input type="text" name="link11" id="link11" class="frm_input" size="50" value="${article.link11}"></td>
            </tr>
            
            <tr>
                <th scope="row"><label>링크 #2</label></th>
                <td><input type="text" name="link12" id="link12" class="frm_input" size="50" value="${article.link12}"></td>
            </tr>

            <tr>
                <th scope="row">파일 #1</th>
                <td>
                    <input type="file" name="uploadFile" id="file1" class="frm_file frm_input">
                </td>
            </tr>
            
            <tr>
                <th scope="row">파일 #2</th>
                <td>
                    <input type="file" name="uploadFile" id="file2" class="frm_file frm_input">
                </td>
            </tr>

            </tbody>
            </table>
        </div>

        <div class="btn_confirm">
            <input type="submit" value="작성완료" class="btn_submit btn" onclick="submitSaveForm();"> 
            <a class="btn_cancel btn" onclick="history.back();">취소</a>
        </div>
        </form>
    </section>
</div>
</body>
</html>
