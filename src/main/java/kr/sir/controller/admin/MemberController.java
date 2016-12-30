package kr.sir.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.sir.config.DataConfig;
import kr.sir.domain.Member;
import kr.sir.service.admin.MemberService;

@Controller
@RequestMapping("/adm/member")
public class MemberController {
	
	private MemberService memberService;
	private DataConfig dataConfig;

	@Autowired
	public void setDataConfig(DataConfig dataConfig) {
		this.dataConfig = dataConfig;
	}
	@Autowired
	public void setMemberService(MemberService memberService){
		this.memberService=memberService;
	}
	
	
	@RequestMapping(value={"/list","/"})
	public String memberList(Model model,String sfl,String sod,String stx,String sst){		
		
		//설정파일
		model.addAttribute("config", dataConfig.getConfig());
		//총회원수
		model.addAttribute("countallmembers", memberService.getCountAllMembers());
		//탈퇴회원수
		model.addAttribute("countretiredmembers",memberService.getCountRetiredMembers());
		//차단회원수
		model.addAttribute("countblockmembers",memberService.getCountBlockedMembers());
		
	
		
		//기본. 모든 회원정보 가져오기. 검색이나 정렬조건이  없을 때
		if(sfl==null && sod==null && stx==null && sst==null){
			List<Member> memberslist=memberService.getAllMembers();			
			model.addAttribute("memberslist", memberslist);
		}
		
		
		
		
		
		
		return "admin/member/list";
	}
	
	@RequestMapping(value={"/pointlist"})
	public String pointList(Model model){
		model.addAttribute("test","포인트리스트");
		return "admin/member/point_list";
	}
	

}
