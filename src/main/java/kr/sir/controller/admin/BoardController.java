package kr.sir.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adm/board")
public class BoardController {

	@RequestMapping(value={"/list"})
	public String boardList(Model model){
		model.addAttribute("test", "보드리스트");
		return "admin/board/list";
	}
	
	@RequestMapping(value={"/boardgrouplist"})
	public String boardgroupList(Model model){
		model.addAttribute("test", "보드그룹리스트");
		return "admin/board/boardgroup_list";
	}
	
}
