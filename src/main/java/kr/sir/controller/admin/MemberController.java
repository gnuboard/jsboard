package kr.sir.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.sir.config.DataConfig;
import kr.sir.domain.Member;
import kr.sir.domain.repository.admin.MemberRepository;
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
		model.addAttribute("countblockedmembers",memberService.getCountBlockedMembers());
		                    
		
		// 멤버리스트 + 접근 가능 그룹 수
		List<Member> memberslist = memberService.getAllMemberList(dataConfig.getPrefix());
		for (Member member : memberslist) {
			System.out.println(member.toString());
		}
		model.addAttribute("memberslist", memberslist);
		
		return "admin/member/list";
	}
	
	@RequestMapping(value={"/form/{type}"})
	public String showMemberForm(Model model,@PathVariable("type") String type,@RequestParam String memberId,@RequestParam String w){
		
		if(type.equals("update")){
			model.addAttribute("member",memberService.getOneMemer(memberId));	
		}else if (type.equals("add")){	
			
		}	
			
		model.addAttribute("w", w);				
		
		return "admin/member/form";
	}
	
	
	
	@RequestMapping(value={"/pointlist"})
	public String pointList(Model model){
		model.addAttribute("test","포인트리스트");
		return "admin/member/point_list";
	}
	

}
