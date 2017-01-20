<%@page import="kr.sir.domain.Board"%>
<%@page import="kr.sir.domain.Write"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>커뮤니티 메인</title>
    <link rel="stylesheet" href="/css/board.css">
 </head>
 <body>
 <div id="container">
    <div id="lt_wr">
    	<%
    		List<List<Write>> newArticleList = (List<List<Write>>) request.getAttribute("newArticleList");
    		List<Board> boardList = (List<Board>) request.getAttribute("boardList");
    	
    		int index = 0;
    		for(List<Write> articleList : newArticleList) {	%>
    			  
    	<%		if(index % 2 == 1) { %>
   					<div class="lt lt_right">
    	<%		} else { %>
    				<div class="lt">
    	<%		} %>						
    			<strong class="lt_title"><a href="/board/<%=boardList.get(index).getTable() %>/list"><%=boardList.get(index).getTable() %>게시판</a></strong>
    	<%		for(Write article : articleList) {	%>			
	            <ul>
	                <li>
	                    <a href="/board/<%=boardList.get(index).getTable() %>/view/<%=article.getId() %>"><%=article.getSubject() %> <span class="cnt_cmt"><%=article.getComment() %></span> </a> 
	                    <img src="/img/icon_new.gif" alt="새글">
	                    <% if(article.getFile() > 0) { %>
	                    	<img src="/img/icon_file.gif" alt="첨부파일">
	                    <% } 
	                       if(article.getLink11() != "") { %>	
	                    	<img src="/img/icon_link.gif" alt="관련링크">
	                    <% } %>	
	                </li>
	            </ul>
	            	
    	<%		} 	%>
    			<div class="lt_more"><a href="/board/<%=boardList.get(index).getTable() %>/list"><span class="sound_only">게시판</span>더보기</a></div>
    			</div>		
    	<%		index++;
    		}	%>
    </div>
</div>
</body>
</html>
