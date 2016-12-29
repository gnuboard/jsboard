package kr.sir.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.sir.service.admin.MemberService;

@Controller
@RequestMapping("/adm/member")
public class MemberController {
	
	private MemberService memberService;
	
	@Autowired
	public void setMemberService(MemberService memberService){
		this.memberService=memberService;
	}
	
	
	@RequestMapping(value={"/list","/"})
	public String memberList(Model model,String sfl,String sod,String stx,String sst){		
	
		model.addAttribute("memberlist", memberService.getAllMembers());
		model.addAttribute("countretiredmembers",memberService.getCountRetiredMembers());
		model.addAttribute("countallmember",memberService.getCountAllMember() );
		return "admin/member/list";
	}
	
	@RequestMapping(value={"/pointlist"})
	public String pointList(Model model){
		model.addAttribute("test","포인트리스트");
		return "admin/member/point_list";
	}
	

}
