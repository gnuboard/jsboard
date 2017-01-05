package kr.sir.controller.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.sir.config.DataConfig;
import kr.sir.service.admin.ConfigService;
import kr.sir.service.admin.MemberService;
import kr.sir.service.install.InstallService;

@RestController
@RequestMapping("/test")
public class InstallTestController {
	
	
	private InstallService installService;
	
	private MemberService memberService; 
	
	private DataConfig dataConfig;
	
	private ConfigService configService;
	
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

	@Autowired
	public void setConfigService(ConfigService configService) {
		this.configService = configService;
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
	
	@RequestMapping(value="/config", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String getConfig() throws FileNotFoundException, IOException {
		return configService.getConfig().toString();
	}
}
