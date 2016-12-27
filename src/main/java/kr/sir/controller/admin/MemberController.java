package kr.sir.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.sir.service.admin.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private MemberService memberService;
	
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping(value="/", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String hello() {
		return "hello jsboard!!!";
	}
	
	// test - member
	@RequestMapping(value="/view/MemberById", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String findMemberByIdUseJPQL(Model model) {
		String memberId = memberService.findMemberById("admin");
		model.addAttribute("id", memberId);
		return "test";
	}
	
}
