<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
    $(function(){ 
        $(".contents li").hide();
        $(".contents li:first").show();
        $(".tab_01 li").click(function(){
            var tabType = $(this).index();
            $('.contents li').hide();
            $('.contents li').eq(tabType).show();
            $('.tab_01 li button').removeClass('selected');
            $('.tab_01 li button').eq(tabType).addClass('selected'); 
        })
     
     
        $(".tooltip_btn").mouseover(function(){
            $(this).next(".tooltip_area").fadeIn();
        }).mouseout(function(){
            $(".tooltip_area").fadeOut("fast");
        }).mousemove(function(e){
            $(".tooltip_area").css({
                top:e.pageY+0+"px",
                left:e.pageX+10+"px"
            });
        });
    });

</script>


    
 
 <noscript>
            <p>
                귀하께서 사용하시는 브라우저는 현재 <strong>자바스크립트를 사용하지 않음</strong>으로 설정되어 있습니다.<br>
                <strong>자바스크립트를 사용하지 않음</strong>으로 설정하신 경우는 수정이나 삭제시 별도의 경고창이 나오지 않으므로 이점 주의하시기 바랍니다.
            </p>
        </noscript>
<div id="ft">
        <p>
            Copyright © abc.com. All rights reserved.
        </p>
        <button type="button" class="scroll_top"><span class="top_img"></span><span class="top_txt">TOP</span></button>
    </div>
</div>  <!--다른 admin 페이지에서 안닫은 div태그를 닫아줌  -->
<script>
$(".scroll_top").click(function(){
     $("body,html").animate({scrollTop:0},400);
})
</script>