<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script type="text/javascript">
function delArticle() {
	if(confirm("한번 삭제한 자료는 복구할 방법이 없습니다.\n\n"
			+"정말 삭제하시겠습니까?") == true) {
		$("input:hidden[name=_method]").val("DELETE");
	} else {
		return;
	}
}
</script>
<body>
<div id="container">
    <h1 class="container_tit">board.bo_table</h1>

    <article id="bo_v">
        <header>
            <h2 id="bo_v_title">
                <span class="bo_view_cate">${article.categoryName}</span>
                <strong>${article.subject}</strong>
            </h2>
        </header>

        <section id="bo_v_info">
            <h2>페이지 정보</h2>
            작성자 <strong><span class="sv_member">${article.memberId}</span>&nbsp;${article.ip}</strong>
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
           
        <!-- 첨부파일  -->
<!--         <section id="bo_v_file"> -->
<!--             <h2>첨부파일</h2> -->
<!--             <ul> -->
<!--                 <li> -->
<!--                     <img src="/img/icon_file.gif" alt="첨부" title=""> -->
<!--                     <a href="" class="view_file_download"> -->
<!--                         <strong>iexplore.exe</strong> -->
<!--                     </a> -->
<!--                     (740.2K) -->
<!--                     <span class="bo_v_file_cnt">1회 다운로드 -->
<!--                     DATE : 2017-01-04 10:01:21</span> -->
<!--                 </li> -->
<!--                  <li> -->
<!--                     <img src="/img/icon_file.gif" alt="첨부" title=""> -->
<!--                     <a href="" class="view_file_download"> -->
<!--                         <strong>iexplore.exe</strong> -->
<!--                     </a> -->
<!--                     (740.2K) -->
<!--                     <span class="bo_v_file_cnt">1회 다운로드 -->
<!--                     DATE : 2017-01-04 10:01:21</span> -->
<!--                 </li> -->
<!--             </ul> -->
<!--         </section> -->
        
        <!-- 관련링크 -->
        <section id="bo_v_link">
            <h2>관련링크</h2>
            <ul>
                <li>
                    <img src="/img/icon_link.gif" alt="관련링크">
                    <a href="#" target="_blank">
                        <strong>${article.link11}</strong>
                    </a>
                    <span class="bo_v_link_cnt">${article.link1Hit}회 연결</span>
                </li>
            </ul>
        </section>

         

        <div class="bo_fx">
            <div class="bo_v_nb">
            	<c:if test="${prevArticle != 0}">
                	<a href="/board/view/${prevArticle}/${pageNumber}" class="btn_b01 btn">이전글</a>
                </c:if>
                <c:if test="${nextArticle != 0}">	
                <a href="/board/view/${nextArticle}/${pageNumber}" class="btn_b01 btn">다음글</a>
                </c:if>        
            </div>
        
            <div class="bo_v_com">
            <form action="/board/view/${article.id}" name="boardForm" id="boardForm" method="post">
            	<input type="hidden" name="_method" />
            	<input type="hidden" name="currentPage" value="${pageNumber}"/>
            	
                <a href="" class=" btn">수정</a>
                <input type="submit" class="btn_b01 btn" value="삭제" onclick="delArticle();"/>
                <a href="" class="btn_admin btn">복사</a>
                <a href="" class="btn_admin btn">이동</a>
                <a href="/board/list/${pageNumber}" class="btn_b01 btn">목록</a>
                <a href="" class="btn_b01 btn">답변</a>
                <a href="/board/add" class="btn_b02 btn">글쓰기</a>
            </form> 
            </div>
        </div>
        
        <!-- 댓글 시작 { -->
<!--         <section id="bo_vc"> -->
<!--             <h2>댓글목록</h2> -->
<!--             <ul class="cmt_ul"> -->
<!--                 <li class="cmt_li"> -->
<!--                     <div class="cmt_info"> -->
<!--                         <span class="sound_only">작성자</span> <strong class="if_member">작성자</strong> -->
<!--                         <span class="sound_only">IP</span><span class="if_ip">(106.245.92.19)</span> -->
<!--                         <span class="sound_only">작성일</span> <span class="if_date">17-01-04 09:30</span> -->
<!--                     </div> -->
<!--                     <div class="cmt_con"> -->
<!--                         댓글내용 -->
<!--                     </div> -->
<!--                     <div class="cmt_btn"> -->
<!--                         <a href="#">답변</a> -->
<!--                         <a href="#">수정</a> -->
<!--                         <a href="#">삭제</a> -->
<!--                     </div> -->
<!--                     <ul> -->
<!--                         <li class="cmt_li_2"> -->
<!--                             <div class="cmt_info"> -->
<!--                                 <span class="sound_only">작성자</span> <strong class="if_member">작성자</strong> -->
<!--                                 <span class="sound_only">IP</span><span class="if_ip">(106.245.92.19)</span> -->
<!--                                 <span class="sound_only">작성일</span> <span class="if_date">17-01-04 09:30</span> -->
<!--                             </div> -->
<!--                             <div class="cmt_con"> -->
<!--                                 댓글내용 -->
<!--                             </div> -->
<!--                             <div class="cmt_btn"> -->
<!--                                 <a href="#">답변</a> -->
<!--                                 <a href="#">수정</a> -->
<!--                                 <a href="#">삭제</a> -->
<!--                             </div> -->
<!--                             <ul> -->
<!--                                 <li class="cmt_li_2"> -->
<!--                                     <div class="cmt_info"> -->
<!--                                         <span class="sound_only">작성자</span> <strong class="if_member">작성자</strong> -->
<!--                                         <span class="sound_only">IP</span><span class="if_ip">(106.245.92.19)</span> -->
<!--                                         <span class="sound_only">작성일</span> <span class="if_date">17-01-04 09:30</span> -->
<!--                                     </div> -->
<!--                                     <div class="cmt_con"> -->
<!--                                         댓글내용 -->
<!--                                     </div> -->
<!--                                     <div class="cmt_btn"> -->
<!--                                         <a href="#">답변</a> -->
<!--                                         <a href="#">수정</a> -->
<!--                                         <a href="#">삭제</a> -->
<!--                                     </div> -->
<!--                                 </li> -->
<!--                             </ul> -->
<!--                         </li> -->
<!--                         <li class="cmt_li_2"> -->
<!--                             <div class="cmt_info"> -->
<!--                                 <span class="sound_only">작성자</span> <strong class="if_member">작성자</strong> -->
<!--                                 <span class="sound_only">IP</span><span class="if_ip">(106.245.92.19)</span> -->
<!--                                 <span class="sound_only">작성일</span> <span class="if_date">17-01-04 09:30</span> -->
<!--                             </div> -->
<!--                             <div class="cmt_con"> -->
<!--                                 댓글내용 -->
<!--                             </div> -->
<!--                             <div class="cmt_btn"> -->
<!--                                 <a href="#">답변</a> -->
<!--                                 <a href="#">수정</a> -->
<!--                                 <a href="#">삭제</a> -->
<!--                             </div> -->
<!--                         </li> -->
<!--                     </ul> -->
<!--                 </li> -->
<!--                 <li class="cmt_li"> -->
<!--                     <div class="cmt_info"> -->
<!--                         <span class="sound_only">작성자</span> <strong class="if_member">작성자</strong> -->
<!--                         <span class="sound_only">IP</span><span class="if_ip">(106.245.92.19)</span> -->
<!--                         <span class="sound_only">작성일</span> <span class="if_date">17-01-04 09:30</span> -->
<!--                     </div> -->
<!--                     <div class="cmt_con"> -->
<!--                         댓글내용 -->
<!--                     </div> -->
<!--                     <div class="cmt_btn"> -->
<!--                         <a href="#">답변</a> -->
<!--                         <a href="#">수정</a> -->
<!--                         <a href="#">삭제</a> -->
<!--                     </div> -->
<!--                 </li> -->
<!--             </ul> -->
<!--         </section> -->

<!--         <aside id="bo_vc_w"> -->
<!--         <h2>댓글쓰기</h2> -->
<!--             <div class="cmt_wt_if"> -->
<!--                 <span> -->
<!--                     <label>이름<strong class="sound_only"> 필수</strong></label> -->
<!--                     <input type="text" class="frm_input required"> -->
<!--                 </span> -->
<!--                 <span> -->
<!--                     <label>비밀번호<strong class="sound_only"> 필수</strong></label> -->
<!--                     <input type="text" class="frm_input required"> -->
<!--                 </span> -->
<!--                 <span id="bo_vc_sns"> -->
<!--                     <input type="checkbox"> -->
<!--                     <label class="sns_f">페이스북과 SNS 동시등록</label> -->
<!--                     <input type="checkbox"> -->
<!--                     <label  class="sns_t">트위터와 SNS 동시등록</label> -->

<!--                 </span> -->
<!--             </div> -->
<!--             <div class="cmt_wt_text"> -->
<!--                 <span class="sound_only">내용</span> -->
<!--                 <div class="cmt_wt_wr"> -->
<!--                     <textarea id="wr_content" name="wr_content" maxlength="10000" required="" class="required" title="내용"></textarea> -->
<!--                     <div class="btn_confirm"> -->
<!--                         <input type="submit" id="btn_submit" class="btn_submit" value="댓글등록"> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="cmt_scr"><input type="checkbox" name="wr_secret" value="secret" id="wr_secret"><label for="wr_secret">비밀글사용</label></div> -->
<!--             </div> -->
<!--         </aside> -->
    </article>
</div>
 </body>
</html>
