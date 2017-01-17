package kr.sir.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.sir.service.board.BbsService;

@Controller
@RequestMapping(value="/")
public class IndexController {
	
	private BbsService bbsService;
	
	@Autowired
	public void setBbsService(BbsService bbsService) {
		this.bbsService = bbsService;
	}

	@RequestMapping(value={"","index"})
	public String index(Model model) {
		
		model = bbsService.getNewArticleList(model);
		
		return "/board/index";
	}
}
