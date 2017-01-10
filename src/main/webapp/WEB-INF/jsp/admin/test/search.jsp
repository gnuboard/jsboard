<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>검색 테스트</title>
    <link rel="stylesheet" href="./style.css">
 </head>
 <body>
 <div id="container">
    <div id="bo_list">
        <fieldset id="bo_sch">
            <legend>회원 검색</legend>

            <form name="member" action="./search" method="post">
            <label for="keyword" class="sound_only">검색대상</label>
            <select name="keyword">
                <option value="mb_id">회원아이디</option>
                <option value="mb_nick">닉네임</option>
                <option value="mb_name">이름</option>
                <option value="mb_level">권한</option>
                <option value="mb_email">E-MAIL</option>
                <option value="mb_tel">전화번호</option>
                <option value="mb_hp">휴대폰번호</option>
                <option value="mb_point">포인트</option>
                <option value="mb_datetime">가입일시</option>
                <option value="mb_ip">IP</option>
                <option value="mb_recommend">추천인</option>
            </select>
            <label name="searchWord" class="sound_only">검색어<strong class="sound_only"> 필수</strong></label>
            <input type="text" name="searchWord" class="frm_input required" size="15" maxlength="20">
            <label for="keyword" class="sound_only">정렬순서</label>
            <select name="orderBy">
                <option value="desc">내림차순</option>
                <option value="asc">오름차순</option>
            </select>
            <input type="submit" value="검색" class="btn_submit">
            </form>
        </fieldset>
    </div>
</div>
</body>
</html>
