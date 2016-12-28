package kr.sir.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.sir.service.admin.MemberService;

@Controller
@RequestMapping("/adm/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping(value={"/list","/"})
	public String memberList(Model model){		
	
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
