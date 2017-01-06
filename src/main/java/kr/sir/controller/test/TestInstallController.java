package kr.sir.controller.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.sir.common.CommonUtil;
import kr.sir.config.DataConfig;
import kr.sir.service.admin.MemberService;
import kr.sir.service.install.InstallService;

@RestController
@RequestMapping("/test")
public class TestInstallController {
	
	
	private InstallService installService;
	
	private MemberService memberService; 
	
	private DataConfig dataConfig;
	
	@Autowired
	public void setInstallService(InstallService installService) {
		this.installService = installService;
	}

	@Autowired
	public void setDataConfig(DataConfig dataConfig) {
		this.dataConfig = dataConfig;
	}
	
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping(value="/file/write/", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String testConfigFileWrite() throws FileNotFoundException, IOException {
		String prefix = "test22_";
		installService.writeConfigToYaml(prefix);
		return "success";
	}
	
	@RequestMapping(value="/config/prefix", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getPrefix() throws FileNotFoundException, IOException {
		return dataConfig.prefix();
	}
	
	@RequestMapping(value="/member/count", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getCountAllMembers() throws FileNotFoundException, IOException {
		String count = "count : " + memberService.getCountAllMembers();
		return count;
	}
	
	@RequestMapping(value="/date", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String date() throws FileNotFoundException, IOException {
		return new Date().toString();
	}
	
	@RequestMapping(value="/file/path", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String resourcePath() throws FileNotFoundException, IOException {
		return this.getClass().getResource("/application.yml").getFile();
	}
	
	@RequestMapping(value="/yml/prefix", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String yamlPrefix() throws FileNotFoundException {
		return CommonUtil.getTablePrefix();
	}
}
