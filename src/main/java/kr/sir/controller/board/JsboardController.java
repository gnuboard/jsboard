package kr.sir.controller.board;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.sir.common.CommonUtil;
import kr.sir.domain.Write;
import kr.sir.service.board.JsBoardService;

@Controller
@RequestMapping(value="/board")
public class JsboardController {
	
	private JsBoardService jsBoardService;
	
	@Autowired
	public void setBoardService(JsBoardService jsBoardService) {
		this.jsBoardService = jsBoardService;
	}

	@RequestMapping(value="/list/{pageNumber}")
	public String index(Model model, @PathVariable int pageNumber) {
		
		int paramCurrentPage = pageNumber - 1;					// 현재 몇 페이지 인지
		int paramPagePerPosts = 10;								// 한 페이지 당 게시물 수 
		int paramBoardId = 1;									// 어떤 게시판인지
		
		PageRequest pageRequest = new PageRequest(paramCurrentPage, paramPagePerPosts, new Sort(Direction.ASC, "id"));
		Page<Write> result = jsBoardService.findByBoardId(paramBoardId, pageRequest);	// Paging, sorting 한 게시판 가져오기
		
		model = CommonUtil.pagingInfo(result, model);

		return "/board/list";
		
	}

}
