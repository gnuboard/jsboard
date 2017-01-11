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
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		int pageNumber = 1;
		return "redirect:/board/list/" + pageNumber;
	}
	
	@GetMapping
	@RequestMapping(value="/list/{pageNumber}", method=RequestMethod.GET)
	public String listWithPageNumber(Model model, @PathVariable int pageNumber) {

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
	public String delete(@PathVariable int pageNumber, BoardForm boardForm) {

		int result = jsBoardService.deleteInIds(boardForm.getId());		// 삭제한 게시물 수만큼 return 됨
		
		// 삭제 후 전체 게시물 수 변화에 따라 page를 재조정해서 redirect 
		return "redirect:/board/list/" + boardForm.redirectPageNumber(result, pageNumber);
	}
	
	@GetMapping
	@RequestMapping(value="/view/{articleNumber}", method=RequestMethod.GET)
	public String view(Model model, @PathVariable int articleNumber) {
		int pageNumber = 1;
		return "redirect:/board/view/"+articleNumber + "/" + pageNumber;
	}
	
	@GetMapping
	@RequestMapping(value="/view/{articleNumber}/{pageNumber}", method=RequestMethod.GET)
	public String view(Model model, @PathVariable int articleNumber, @PathVariable int pageNumber, HttpServletRequest request) {
		
		Write article = jsBoardService.findOne(articleNumber);
		int prevArticle = jsBoardService.findPrevOrNextArticle(articleNumber, "prev");
		int nextArticle = jsBoardService.findPrevOrNextArticle(articleNumber, "next");
		
		jsBoardService.increaseHit(article, request);	// 조회수 증가 기능
		
		model.addAttribute("article", article);
		model.addAttribute("prevArticle", prevArticle);
		model.addAttribute("nextArticle", nextArticle);
		model.addAttribute("pageNumber", pageNumber);
		
		return "/board/view";
	}

	@DeleteMapping
	@RequestMapping(value="/view/{articleNumber}", method=RequestMethod.DELETE)
	public String delArticle(Model model, @PathVariable int articleNumber, BoardForm boardForm) {
		jsBoardService.delArticle(articleNumber);
		
		int redirectPage = boardForm.getCurrentPage();
		return "redirect:/board/list/"+redirectPage;
	}
	
	@GetMapping
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String writeArticle(Model model) {
		
		return "/board/form";
	}
}
