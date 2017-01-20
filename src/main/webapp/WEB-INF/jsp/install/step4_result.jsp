<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>설치</title>

<link rel="stylesheet" href="/css/install.css">
</head>
<body>

<div id="top">
    <h1>BOARD</h1>
    <p>INSTALLATION</p>
</div>
<div id="contents">
    <div class="ins_tab">
        <ol>
            <li>라이센스</li>
            <li>정보입력</li>
            <li>설치진행</li>
            <li class="on">설치완료</li>
        </ol>
    </div>
    <form>
    <div class="ins_inner">
        <h2>설치완료</h2>
        <div>
            <h3>설치가 완료되었습니다.</h3>
            <ol>
            	<li>전체 테이블 생성 완료</li>
            	<li>config.yml에 prefix 정보 저장</li>
            	<li>관리자 회원 등록 완료</li>
            	<li>config table에 설정 정보 저장</li>
            </ol>
            <p>환경설정 변경은 다음의 과정을 따르십시오.</p>
            <ol>
                <li>메인화면으로 이동</li>
                <li>관리자 로그인</li>
                <li>관리자 모드 접속</li>
                <li>환경설정 메뉴의 기본환경설정 페이지로 이동</li>

            </ol>
        </div>
        <div class="ins_btn">
<!--             <a href="/install/restart" class="btn btn_01">서버 재시작하고 새로운 게시판으로 이동</a> -->
            <a class="btn btn_01">TABLE 접두어 적용을 위해 Tomcat container를 재시작해 주십시오.</a>
        </div>
    </div>

    </form>
</div>

</body>
</html>