package kr.sir.controller.board;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.sir.common.CommonUtil;
import kr.sir.domain.Write;
import kr.sir.domain.module.BoardForm;
import kr.sir.service.board.JsBoardService;

@Controller
@RequestMapping(value="/board")
public class JsboardController {
	
	private JsBoardService jsBoardService;
	
	@Autowired
	public void setBoardService(JsBoardService jsBoardService) {
		this.jsBoardService = jsBoardService;
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		int paramCurrentPage = 0;								// 현재 몇 페이지 인지, 첫 페이지 = 0
		int paramPagePerPosts = 10;								// 한 페이지 당 게시물 수 
		int paramBoardId = 1;									// 어떤 게시판인지 (추후 입력값으로 변경)
		
		PageRequest pageRequest = new PageRequest(paramCurrentPage, paramPagePerPosts, new Sort(Direction.DESC, "id"));
		Page<Write> result = jsBoardService.findByBoardId(paramBoardId, pageRequest);	// Paging, sorting 한 게시판 가져오기
		
		model = CommonUtil.pagingInfo(result, model);
		model.addAttribute("pagePerPosts", paramPagePerPosts);	// 한 페이지 당 게시물 수 
		
		return "/board/list";
	}
	
	@RequestMapping(value="/list/{pageNumber}", method=RequestMethod.GET)
	public String listWithPageNumber(Model model, @PathVariable int pageNumber, HttpServletRequest request) {
		System.out.println("request.method : " + request.getMethod());
		int paramCurrentPage = pageNumber - 1;					// 현재 몇 페이지 인지	
		int paramPagePerPosts = 10;								// 한 페이지 당 게시물 수 
		int paramBoardId = 1;									// 어떤 게시판인지 (추후 입력값으로 변경)
		
		PageRequest pageRequest = new PageRequest(paramCurrentPage, paramPagePerPosts, new Sort(Direction.DESC, "id"));
		Page<Write> result = jsBoardService.findByBoardId(paramBoardId, pageRequest);	// Paging, sorting 한 게시판 가져오기
		
		model = CommonUtil.pagingInfo(result, model);
		model.addAttribute("pagePerPosts", paramPagePerPosts);	// 한 페이지 당 게시물 수 

		return "/board/list";
	}
	
	@DeleteMapping
	@RequestMapping(value="/list/{pageNumber}", method=RequestMethod.DELETE) 
	public String delete(@PathVariable int pageNumber, BoardForm boardForm, HttpServletRequest request) {
		System.out.println("request.method : " + request.getMethod());
		int result = 0;
		int totalCount = boardForm.getTotalCount();
		int pagePerPosts = boardForm.getPagePerPosts();
		int redirectPageNumber = 0;
		
		result = jsBoardService.deleteInIds(boardForm.getId());		// 삭제한 게시물 수만큼 return 됨
			
		// 삭제 후 전체 게시물 수 변화에 따라 redirect page를 재조정
		totalCount = totalCount - result;
		if(totalCount % pagePerPosts > 0) {
			redirectPageNumber = pageNumber;
		} else {
			redirectPageNumber = totalCount / pagePerPosts;
		}
		
		return "redirect:/board/list/"+redirectPageNumber;
	}

}
