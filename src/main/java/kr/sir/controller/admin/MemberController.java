package kr.sir.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.sir.model.Member;
import kr.sir.service.admin.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	private MemberService memberService;
	
	@RequestMapping(value="/", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String hello() {
		return "hello jsboard!!";
	}
	
	@RequestMapping(value="/member_admin", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String test() {
		Member admin = memberService.findAdmin();
		return admin.toString();
	}

}
