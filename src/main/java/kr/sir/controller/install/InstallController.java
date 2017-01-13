package kr.sir.controller.install;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.sir.domain.InstallAdmin;
import kr.sir.domain.Member;
import kr.sir.domain.form.AgreeForm;
import kr.sir.service.install.InstallService;

@Controller
@RequestMapping(value = "/install")
public class InstallController {
	
	private InstallService installService;
	
	@Autowired
	public void setInstallService(InstallService installService) {
		this.installService = installService;
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
	public String result(Model model, InstallAdmin adminForm) throws FileNotFoundException, IOException {
		// 1. schema로 db 생성
		installService.createTable(new ClassPathResource("database.sql"), adminForm.getTable_prefix());
		
		// 2. application.yml에 table_prefix 등록
		installService.writeConfigToYaml(adminForm.getTable_prefix());
		
		// 3. member table에 관리자 정보 insert
		int adminInsertResult = adminInfoSave(adminForm.getTable_prefix(), adminForm);
		
		// 4. config table에 설정 정보 insert
		int configInsertResult = installService.writeConfigInfo(adminForm.getTable_prefix(), adminForm);
		
		model.addAttribute("adminInsertResult", adminInsertResult);
		model.addAttribute("configInsertResult", configInsertResult);
		
		return "/install/step4_result";
	}

	private int adminInfoSave(String prefix, InstallAdmin adminForm) throws UnknownHostException {
		Member member = new Member();
		
		member.setMemberId(adminForm.getMemberId());
		member.setPassword(adminForm.getPassword());
		member.setName(adminForm.getName());
		member.setEmail(adminForm.getEmail());
		
		return installService.writeAdminInfo(prefix, member);
	}
	
}
