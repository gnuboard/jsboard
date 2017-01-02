package kr.sir.controller.install;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.sir.domain.module.AgreeForm;

@Controller
@RequestMapping(value = "/install")
public class InstallController {

	// 최초 설치 페이지로 이동
	@RequestMapping(value = {"/step/1", ""})
	public String install() {
		return "/install/step1_info";
	}
	
	// licence 동의 페이지로 이동
	@RequestMapping(value = "/step/2")
	public String licence() {
		return "/install/step2_licence";
	}
	
	// 설정 정보 입력 페이지로 이동
	@RequestMapping(value = "/step/3")
	public String writeConfigForm(Model model, AgreeForm agreeForm) {
		System.out.println("agree : " + agreeForm.getAgree());
		model.addAttribute("isAgree", agreeForm.getAgree());
		return "/install/step3_form";
	}
	
	// 설정 결과 페이지로 이동
	@RequestMapping(value = "/step/4")
	public String complete() {
		return "/install/step4_result";
	}
}
