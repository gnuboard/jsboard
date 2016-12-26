package kr.sir.controller.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.sir.model.Member;
import kr.sir.service.admin.TestService;

@RestController
@RequestMapping("/test")
public class TestController {
	
	private TestService testService;

	@RequestMapping(value="/", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String hello() {
		return "hello jsboard!!";
	}
	
	@RequestMapping(value="/member_admin", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String test() {
		Member admin = testService.findAdmin();
		return admin.toString();
	}
}
