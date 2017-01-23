<%@ page import="java.util.ArrayList"%>
<%@ page import="kr.sir.domain.Write"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>${article.subject}</title>
	<link rel="stylesheet" href="/css/board.css">
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	// 글 내용 줄바꿈 처리
	var text = $('#bo_v_con').text().replace(/\n/g, "<br>");
	$('#bo_v_con').html(text);

	$('#delButton').click(function() {
		if(confirm("한번 삭제한 자료는 복구할 방법이 없습니다.\n\n"
				+"정말 삭제하시겠습니까?")) {
			$("input:hidden[name=_method]").val("DELETE");
			$("#viewForm").submit();
			return true;
		} else {
			return false;
		}
	});
	
	$('#answerButton').click(function() {
		// 답변 depth 제한(DB의 wr_reply의 size에 맞게 셋팅)
		var depth = $("#reply").val().length;
		if(depth < 10) {
			$("input:hidden[name=_method]").val("GET");
			$("#viewForm").submit();
			return true;
		} else {
			alert("더 이상 답변하실 수 없습니다.\n\n답변은 10단계 까지만 가능합니다.")
			return false;
		}
	});
	
	$("a[name='file']").click(function(e) {
		e.preventDefault();
	});
		
});

function getCommentForm(baseId) {
	$("#baseCommentId").val(baseId);
	$(".commentForm").remove();
	$("div[id=commentForm"+baseId+"]").load("/html/board/commentForm.html");
	$("input:hidden[name=_method]").val("POST");
	
}
function setDefaultBaseId() {
	// 댓글에서 기준이 원글인 경우 기준댓글에 원글번호를 넣는다.
	if($("#baseCommentId").val() == "") {
		var originId = $("#originId").val();
		$("#baseCommentId").val(originId);
	} 
}
function getCommentUpdateForm(commentId, name, content) {
	$("#baseCommentId").val(commentId);
	$(".commentForm").remove();
	$("div[id=commentForm"+commentId+"]").load("/html/board/commentForm.html");
	$("input:hidden[name=_method]").val("PUT");
	
	$.ajax({
		success : function(data) {
			$("#name").val(name);
			$("#content").val(content);
		}
	});
}

function delComment(commentId) {
	
	if(confirm("이 댓글을 삭제하시겠습니까?")) {
		$("input:hidden[name=_method]").val("DELETE");
		$("#baseCommentId").val(commentId);
		$("#commentForm").submit();
	} else {
		return false;
	}	
}
</script>
<body>
<div id="container">
    <h1 class="container_tit">${boardName} 게시판</h1>

    <article id="bo_v">
        <header>
            <h2 id="bo_v_title">
                <span class="bo_view_cate">${article.categoryName}</span>
                <strong>${article.subject}</strong>
            </h2>
        </header>

        <section id="bo_v_info">
            <h2>페이지 정보</h2>
            작성자 <strong><span class="sv_member">${article.name}</span>&nbsp;${article.ip}</strong>
            <span class="sound_only">작성일</span> <fmt:formatDate value="${article.datetime}" pattern="yy-MM-dd HH:mm"/>
            <span class="if_right">조회 <strong>${article.hit}회</strong>
            댓글<strong> ${article.comment}건</strong></span>
        </section>

        <section id="bo_v_atc">
            <h2 id="bo_v_atc_title">본문</h2>
            <!-- 본문 내용 시작  -->
            <div id="bo_v_con">
			    ${article.content}
            </div>
            
            <div id="bo_v_btn">
                <div class="bo_v_scrap">
                    <a href="" class="btn_scrap">스크랩</a>
                    <a href="" class="sns_f sns">페이스북으로 보내기</a>
                    <a href="" class="sns_t sns">트위터로 보내기</a>
                    <a href="" class="sns_g sns">구글플러스로 보내기</a>
                </div>
                <div class="bo_v_good">
                    <a href="" class="">추천 <strong>${article.good}</strong></a>
                    <a href="" class="">비추천 <strong>${article.nogood}</strong></a>
                </div>
            </div>

        </section>
           
        <c:if test="${fn:length(fileList) > 0}">
        <!-- 첨부파일  -->
        <section id="bo_v_file">
            <h2>첨부파일</h2>
            <ul>
            	<c:forEach var="file" items="${fileList}">
                <li>
                    <img src="/img/icon_file.gif" alt="첨부" title="">
                    <a href="#this" name="file" class="view_file_download">
                        <strong>${file.source}</strong>
                    </a>
                    (${file.filesize}B)
                    <span class="bo_v_file_cnt">${file.download}회 다운로드
                    DATE : <fmt:formatDate value="${file.fileDatetime}" pattern="yy-MM-dd HH:mm"/></span>
                </li>
                </c:forEach>
            </ul>
        </section>
        </c:if>
        
        <c:if test="${article.link11 != ''}">
        <section id="bo_v_link">
            <h2>관련링크1</h2>
            <ul>
                <li>
                    <img src="/img/icon_link.gif" alt="관련링크">
                    <a href='<c:url value="${article.link11}"></c:url>' target="_blank">
                        <strong>${article.link11}</strong>
                    </a>
                    <span class="bo_v_link_cnt">${article.link1Hit}회 연결</span>
                </li>
            </ul>
        </section>
        </c:if>
        <c:if test="${article.link12 != ''}">
        <section id="bo_v_link">
            <h2>관련링크2</h2>
            <ul>
                <li>
                    <img src="/img/icon_link.gif" alt="관련링크">
                    <a href="${article.link12}" target="_blank">
                        <strong>${article.link12}</strong>
                    </a>
                    <span class="bo_v_link_cnt">${article.link2Hit}회 연결</span>
                </li>
            </ul>
        </section>
        </c:if>

        <div class="bo_fx">
            <div class="bo_v_nb">
            	<c:if test="${prevArticle != 0}">
                	<a href="/board/${boardName}/view/${prevArticle}/page/${currentPage}/category/${currentCategory}" class="btn_b01 btn">이전글</a>
                </c:if>
                <c:if test="${nextArticle != 0}">	
                <a href="/board/${boardName}/view/${nextArticle}/page/${currentPage}/category/${currentCategory}" class="btn_b01 btn">다음글</a>
                </c:if>        
            </div>
        
            <div class="bo_v_com">
            <form action="/board/view" name="viewForm" id="viewForm" method="post">
            	<input type="hidden" name="_method" />
            	<input type="hidden" name="id" value="${article.id}"/>
            	<input type="hidden" name="boardName" value="${boardName}"/>
            	<input type="hidden" name="currentPage" value="${currentPage}"/>
            	<input type="hidden" name="currentCategory" value="${currentCategory}"/>
            	<input type="hidden" name="reply" id="reply" value="${article.reply}"/>
            	
                <a href="/board/${boardName}/save/${article.id}/page/${currentPage}/category/${currentCategory}" class="btn">수정</a>
                <input type="submit" id="delButton" class="btn_b01 btn" value="삭제">
                <a href="" class="btn_admin btn">복사</a>
                <a href="" class="btn_admin btn">이동</a>
                <a href="/board/${boardName}/list/${currentPage}/category/${currentCategory}" class="btn_b01 btn">목록</a>
                <input type="submit" id="answerButton" class="btn_b01 btn" value="답변">
                <a href="/board/${boardName}/save" class="btn_b02 btn">글쓰기</a>
            </form> 
            </div>
        </div>
        
      	<form action="/board/view/comment" name="commentForm" id="commentForm" method="post">
       		<input type="hidden" name="_method" value="post"/>
			<!-- 원글의 wr_id -->
			<input type="hidden" name="id" id="originId" value="${article.id}"/>
			<input type="hidden" name="boardName" value="${boardName}"/>
			<input type="hidden" name="currentCategory" value="${currentCategory}"/>
			<input type="hidden" name="currentPage" value="${currentPage}"/>
			<!-- 답변글 : 1, 원글 : 0 -->
<!-- 			<input type="hidden" name="isReply" value="0"/> -->
			<!-- 기준 댓글의 wr_id -->
			<input type="hidden" name="baseCommentId" id="baseCommentId" value=""/>
			<input type="hidden" name="subject" value=""/>
			<input type="hidden" name="reply" value=""/>
			<input type="hidden" name="email" value=""/>
			<input type="hidden" name="homepage" value=""/>
			<input type="hidden" name="option" value=""/>
			<input type="hidden" name="link11" value=""/>
			<input type="hidden" name="link12" value=""/>
			<input type="hidden" name="link1Hit" value="0"/>
			<input type="hidden" name="link2Hit" value="0"/>
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
			        
        	<!-- 댓글 시작  -->
   			<section id="bo_vc">
			<h2>댓글목록</h2>
             <% List<Write> commentList= (List<Write>)request.getAttribute("commentList");
            	
            	int[] depthArr = new int[commentList.size()];
            	for(int i=0; i<commentList.size(); i++) {
            		depthArr[i] = commentList.get(i).getCommentReply().length();
					if(i==0) { %>			
			            <ul class="cmt_ul">
				<%	} 
					if(depthArr[i] == 0) {
						if(i > 0) {	
							int depthDiffer = depthArr[i-1] - depthArr[i];
							for(int j=0; j < depthDiffer; j++) { %>
						</li></ul>
					<%		}
						} %>			
						<li class="cmt_li">
					<% // depth가 전 댓글보다 작으면 닫기 시작
					} else if(depthArr[i] > 0 && depthArr[i-1] > depthArr[i]){
						for(int j=0; j<depthArr[i-1] - depthArr[i]; j++) { %>
						</li></ul>
			 		 <% } %>
						</li><li class="cmt_li_2">
				 <% } else if(depthArr[i] > 0 && depthArr[i-1] == depthArr[i]) { %>
						</li><li class="cmt_li_2">
				 <% } else { %>
		           		<ul><li class="cmt_li_2">
				 <% } %>
					<div class="cmt_info">
                        <span class="sound_only">작성자</span> <strong class="if_member"><%=commentList.get(i).getName() %></strong>
                        <span class="sound_only">IP</span><span class="if_ip"><%=commentList.get(i).getIp() %></span>
                        <span class="sound_only">작성일</span> <span class="if_date">
                        	 <fmt:formatDate value="<%=commentList.get(i).getDatetime() %>" pattern="yy-MM-dd HH:mm"/>
                        </span>
                    </div>
                    <div class="cmt_con">
                       	<%=commentList.get(i).getContent() %>
                    </div>
                    <div class="cmt_btn">
                    	<% if(commentList.get(i).getCommentReply().length() < 5) { %>
                        	<a onclick="getCommentForm('<%=commentList.get(i).getId() %>');">답변</a>
                        <% } %>	
                        <!-- 본인이거나 권한이 있어야 댓글 수정 가능 -->
                        <a onclick="getCommentUpdateForm('<%=commentList.get(i).getId() %>','<%=commentList.get(i).getName() %>','<%=commentList.get(i).getContent() %>');">
                       		수정
                    	</a>
                        <!-- 댓글이 달려있으면 삭제 버튼 안보이도록, 관리자면 삭제 가능 extra10 사용?-->
						<a onclick="delComment('<%=commentList.get(i).getId() %>');">삭제</a>
                    </div>
                    <div id="commentForm<%=commentList.get(i).getId() %>">
                    </div>
					<%	if(i == commentList.size()) { %>
						</ul>
					<%	}
					} %>	
		</section>

        <aside id="bo_vc_w" class="commentForm">
        <h2>댓글쓰기</h2>
            <div class="cmt_wt_if">
                <span>
                    <label>이름<strong class="sound_only"> 필수</strong></label>
                    <input type="text" name="name" id="name" class="frm_input required" required>
                </span>
                <span>
                    <label>비밀번호<strong class="sound_only"> 필수</strong></label>
                    <input type="password" name="password" class="frm_input required" required>
                </span>
                <span id="bo_vc_sns">
                    <input type="checkbox">
                    <label class="sns_f">페이스북과 SNS 동시등록</label>
                    <input type="checkbox">
                    <label  class="sns_t">트위터와 SNS 동시등록</label>

                </span>
            </div>
            <div class="cmt_wt_text">
                <span class="sound_only">내용</span>
                <div class="cmt_wt_wr">
                    <textarea id="content" name="content" maxlength="10000" required class="required" title="내용"></textarea>
                    <div class="btn_confirm">
                        <input type="submit" id="btn_submit" class="btn_submit" onclick="setDefaultBaseId();" value="댓글등록">
                    </div>
                </div>
                <div class="cmt_scr"><input type="checkbox" name="option"  id="option" value="secret"><label for="wr_secret">비밀글사용</label></div>
            </div>
        </aside>
	    </form>
    </article>
</div>
 </body>
</html>
