<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
    <link rel="stylesheet" href="/css/board.css">
 </head>
 <body>
 <div id="container">
    <h1 class="container_tit">게시판 이름.(bo_id로 board테이블에서 가져오기)</h1>
    <div class="bo_cate">
        <h2>게시판01 카테고리</h2>
        <ul>
            <li class="on"><a href="#">전체</a></li>
            <li><a href="#">카테고리1</a></li>
            <li><a href="#">카테고리2</a></li>
        </ul>
    </div>
    <div id="bo_list">
        <div id="bo_list_total">
            Total <strong>${totalCount}</strong>건
            <strong>${currentPage}</strong> 페이지
        </div>
        <div class="bo_fx">
            <div class="btn_bo_user">
                <a href="" class="btn_admin btn">관리자</a>
                <a href="./add" class="btn_b02 btn">글쓰기</a>
            </div>
        </div>
        <div class="table_basic">
            <table>
                <caption>게시판01(bo_id로 board테이블에서 가져오기) 목록</caption>
                <thead>
                <tr>
                    <th scope="col">번호</th>
                    <th scope="col">
                        <label class="sound_only">현재 페이지 게시물 전체</label>
                        <input type="checkbox">
                    </th>
                    <th scope="col">제목</th>
                    <th scope="col">글쓴이</th>
                    <th scope="col">날짜</th>
                    <th scope="col">조회</th>
                </tr>
                </thead>

                <tbody>
				<c:forEach var="write" items="${writeList}" varStatus="i">                
<!--                 <tr class="bo_notice"> -->
<!--                     <td class="td_num_c"><span class="notice_icon">공지</span></td> -->
<!--                     <td class="td_chk"> -->
<!--                         <label class="sound_only">제목1 게시물</label> -->
<!--                         <input type="checkbox"> -->
<!--                     </td> -->
<!--                     <td><a href="#" class="bo_cate_link">[카테고리1]</a> <a href="#">제목1</a></td> -->
<!--                     <td class="td_name">글쓴이</td> -->
<!--                     <td class="td_date">10-12</td> -->
<!--                     <td class="td_num_c">195</td> -->
<!--                 </tr> -->
                <tr>
                    <td class="td_num_c">${write.id}</td>
                    <td class="td_chk">
                        <label class="sound_only">${write.subject} 게시물</label>
                        <input type="checkbox">
                    </td>
                    <td><a href="#" class="bo_cate_link">[${write.categoryName}]</a> <a href="#">${write.subject}</a></td>
                    <td class="td_name">${write.name}</td>
                    <td class="td_date"><fmt:formatDate value="${write.datetime}" pattern="yy/MM/dd"/></td>
                    <td class="td_num_c">${write.hit}</td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="bo_fx">
            <div class="btn_bo_adm">
                <input type="submit" value="선택삭제" class="btn">
                <input type="submit" value="선택복사" class="btn">
                <input type="submit" value="선택이동" class="btn">
            </div>
        
            <div class="btn_bo_user">
                <a href="./add" class="btn_b02 btn">글쓰기</a>    
            </div>
        </div>
        
        <div class="pagination">
            <h2>페이징</h2>
			            
            <div class="pg_wr">
            	<c:if test = "${ currentPage > PageGroupPerSize}" >
	                <a href="./1" class="first">맨 처음으로</a>
                	<a href="./${prevPageGroupLastPage}" class="prev">이전</a>
                </c:if>
                <c:forEach var="i" begin="${currentPageGroupFirstPage}" end="${currentPageGroupLastPage}">
                	<c:choose>
	                	<c:when test = "${ currentPage eq i}" >
			                <a href="./${i}" class="active">${i}</a>
		                </c:when>
		                <c:otherwise>
		                	<a href="./${i}">${i}</a>
		                </c:otherwise>
	                </c:choose>
                </c:forEach>
                <c:if test = "${ currentPage <= totalPages - (totalPages % PageGroupPerSize) }" >
	                <a href="./${nextPageGroupFirstPage}" class="next">다음</a>
	                <a href="./${totalPages}" class="last">맨 마지막으로</a>
	            </c:if>
            </div>
            
        </div>

        <fieldset id="bo_sch">
            <legend>게시물 검색</legend>

            <form>
            <label class="sound_only">검색대상</label>
            <select>
                <option>제목</option>
                <option>내용</option>
                <option>제목+내용</option>
                <option>회원아이디</option>
                <option>회원아이디(코)</option>
                <option>글쓴이</option>
                <option>글쓴이(코)</option>
            </select>
            <label class="sound_only">검색어<strong class="sound_only"> 필수</strong></label>
            <input type="text" class="frm_input required" size="15" maxlength="20">
            <input type="submit" value="검색" class="btn_submit">
            </form>
        </fieldset>
    </div>
</div>
 </body>
</html>
