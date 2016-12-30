package kr.sir.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.sir.config.DataConfig;
import kr.sir.domain.Config;

@Controller
@RequestMapping("/adm/test")
public class TestAdminController {
	
	private DataConfig dataConfig;

	@Autowired
	public void setDataConfig(DataConfig dataConfig) {
		this.dataConfig = dataConfig;
	}

	@Autowired
	private String prefix;
	
	@RequestMapping("/config/load")
	public String testGetPrefix(Model model){
		
		Config config = dataConfig.getConfig();
		model.addAttribute("prefix", this.prefix);
		model.addAttribute("config", config);
		return "admin/test/config/load";
	}
}
