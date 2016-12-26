package kr.sir.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.sir.model.GroupMember;
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
	
	// member test
	@RequestMapping(value="/member_i", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String findMemberById(Model model) {
		String memberId = memberService.findAdmin();
		model.addAttribute("id", memberId);
		return "test";
	}

	// group member test
	@RequestMapping(value="/allGroupMember", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String findFirstMember(Model model) {
		List<GroupMember> allGroupMember = memberService.AllGroupMember();
		model.addAttribute("group_member", allGroupMember);
		return "test2";
	}
}
