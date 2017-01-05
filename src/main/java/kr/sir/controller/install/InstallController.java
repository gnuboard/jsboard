package kr.sir.controller.install;

import java.io.FileNotFoundException;
import java.io.IOException;

//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.sir.domain.Member;
import kr.sir.domain.module.AgreeForm;
import kr.sir.domain.module.ConfigForm;
import kr.sir.service.admin.MemberService;
import kr.sir.service.install.InstallService;

@Controller
@RequestMapping(value = "/install")
public class InstallController {
	
	private InstallService installService;
	
	private MemberService memberService;
	
	@Autowired
	public void setInstallService(InstallService installService) {
		this.installService = installService;
	}

	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	// 최초 설치 페이지로 이동
	@RequestMapping(value = {"", "/step","/step/1"})
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
		model.addAttribute("isAgree", agreeForm.getAgree());
		return "/install/step3_form";
	}
	
	// 설정 결과 페이지로 이동
	@RequestMapping(value = "/step/4")
	public String result(Model model, ConfigForm configForm) throws FileNotFoundException, IOException {
		// 1. schema로 db 생성
		installService.createTable(new ClassPathResource("database.sql"), configForm.getTable_prefix());
		
		// 2. application.yml에 table_prefix 등록
		installService.writeConfigToYaml(configForm.getTable_prefix());
		
		// 3. member table에 관리자 정보 insert
//		adminInfoSave(configForm);
		
		// 4. config table에 설정 정보 insert
		int result = installService.writeConfigInfo(configForm.getTable_prefix(), configForm);
		model.addAttribute("configResult", result);
		
		return "/install/step4_result";
	}

	private void adminInfoSave(ConfigForm configForm) {
		Member admin = new Member();
		
		admin.setMemberId(configForm.getAdmin_id());
		admin.setPassword(configForm.getAdmin_pass());
		admin.setName(configForm.getAdmin_name());
		admin.setEmail(configForm.getAdmin_email());
		
		memberService.adminSavesMember(admin);
	}
	
}
