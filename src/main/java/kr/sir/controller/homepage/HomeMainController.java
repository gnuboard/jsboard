package kr.sir.controller.homepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/index")
public class HomeMainController {

	@RequestMapping(value="")
	public String index() {
		return "/index";
	}
}
