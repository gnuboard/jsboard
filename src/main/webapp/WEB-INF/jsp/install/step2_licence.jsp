<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="/css/install.css">
<title>라이센스 동의</title>
</head>
<script>
function frm_submit(f)
{
    if (!f.agree.checked) {
        alert("라이센스 내용에 동의하셔야 설치가 가능합니다.");
        return false;
    }
    return true;
}
</script>
<body>
<div id="top">
    <h1>GNUBOARD</h1>
    <p>INSTALLATION</p>
</div>
<div id="contents">
    <div class="ins_tab">
        <ol>
            <li class="on">라이센스</li>
            <li>정보입력</li>
            <li>설치진행</li>
            <li>설치완료</li>
        </ol>
    </div>
	<form name="agreeForm" action="/install/step/3" method="post" onsubmit="return frm_submit(this);">
    <div class="ins_inner">
        <h2>라이센스</h2>
        <p>
            <strong>라이센스(License) 내용을 반드시 확인하십시오.</strong><br>
            라이센스에 동의하시는 경우에만 설치가 진행됩니다.
        </p>

        <div class="ins_license">
            <textarea name="textarea" readonly>내용</textarea>
        </div>

        <div id="ins_agree">
            <input type="checkbox" name="agree" value="on" id="agree">
            <label for="agree">동의합니다.</label>
        </div>

        <div class="ins_btn">
            <input type="submit" value="다음" class="btn_01">
        </div>
    </div>
    </form>
</div>
</body>
</html>