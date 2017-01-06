package kr.sir.controller.board;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.sir.domain.Write;
import kr.sir.service.board.BoardService;

@Controller
@RequestMapping(value="/board")
public class JsboardController {
	
	private BoardService boardService;
	
	@Autowired
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	@RequestMapping(value="/list")
	public String index(Model model, Write write) {
		
		PageRequest pageRequest = new PageRequest(0, 10, new Sort(Direction.ASC, "id"));
		
		Page<Write> result = boardService.findByBoardId(1, pageRequest);
		List<Write> writeList = result.getContent();
		int totalPages = result.getTotalPages();
		long totalCount = result.getTotalElements();
		int currentPage = result.getNumber();
//		boolean hasNextPage = result.hasNext();
		
//		String totalCount = boardService.getTotalCount();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
//		model.addAttribute("hasNextPage", hasNextPage);
		model.addAttribute("writeList", writeList);
		model.addAttribute("totalCount", totalCount);
		
		return "/board/list";
	}
}
