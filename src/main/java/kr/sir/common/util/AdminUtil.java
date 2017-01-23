package kr.sir.common.util;

import org.springframework.stereotype.Service;

@Service
public class AdminUtil {
	public String getMemberLevelSelectBoxTag(String name,int startLevel,int endLevel,int currentLevel,String event){
		String selectBoxTag="";
		
		selectBoxTag="\n<select id='"+name+"' name='"+name+"' ";
		if(event!=null ){
			selectBoxTag+=event;
		}
		selectBoxTag+=">\n";
		
		for(int i=startLevel; i<=endLevel;i++){
			selectBoxTag+="<option value='"+i+"'";
			if(i==currentLevel){
				selectBoxTag+=" selected='selected'";
			}
			
			selectBoxTag+=">"+i+"</option>\n";			
		}
		selectBoxTag+="</select>\n";
		
		
		return selectBoxTag;
	}
	
	/*
	  환경설정에서 에디터 선택이 없는 경우에 사용하는 라이브러리 입니다. 에디터 선택시 "선택없음"이 아닌 경우 plugin/editor
	  하위 디렉토리의 각 에디터이름/editor.lib.php 를 수정하시기 바랍니다.
	*/
	public String editorHtml(String id,String content){
		return "<textarea id='"+id+"' name='"+id+"' styel='width:100%;' maxlength='65536'>"+content+"</textarea>";
	}
	
	
}
