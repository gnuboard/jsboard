package kr.sir.controller.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/index")
public class IndexController {

	@RequestMapping(value="")
	public String index() {
		return "/board/index";
	}
}
