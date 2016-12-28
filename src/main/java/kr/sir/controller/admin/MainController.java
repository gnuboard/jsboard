package kr.sir.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adm")
public class MainController {

	@RequestMapping("/")
	public String main(){
		return "admin/main/main";
	}
	
	
}
