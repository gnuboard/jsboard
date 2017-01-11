package kr.sir.controller.board;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.sir.common.CommonUtil;
import kr.sir.domain.Write;
import kr.sir.domain.module.BoardForm;
import kr.sir.service.board.JsBoardService;

@Controller
@RequestMapping(value="/board")
public class JsboardController {
	
	private final int BOARD_ID = 1;
	private final int PAGE_PER_POSTS = 10;
	
	private JsBoardService jsBoardService;
	
	@Autowired
	public void setBoardService(JsBoardService jsBoardService) {
		this.jsBoardService = jsBoardService;
	}

	// 글 목록 기본값(주소 뒷부분 생략 - 타 사이트 링크나 url로 들어올 경우) 
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {

		List<String> categoryList = jsBoardService.findCategoryNames();		// 카테고리 가져오기
		model.addAttribute("categoryList", categoryList);
		
		return "redirect:/board/list/1/category/all";
	}
	
	// 글 목록
	@RequestMapping(value="/list/{pageNumber}/category/{categoryName}", method=RequestMethod.GET)
	public String listWithPageNumber(Model model, @PathVariable int pageNumber, @PathVariable String categoryName) {

		int paramCurrentPage = pageNumber - 1;								// 현재 몇 페이지 인지	
		
		PageRequest pageRequest = new PageRequest(paramCurrentPage, PAGE_PER_POSTS, new Sort(Direction.DESC, "id"));
		// 카테고리로 검색한 게시판 내용에 Paging, sorting 처리해서 가져오기
		Page<Write> result = jsBoardService.findByCategoryName(BOARD_ID, categoryName, pageRequest);	
		
		model = CommonUtil.pagingInfo(result, model);
		model.addAttribute("pagePerPosts", PAGE_PER_POSTS);					// 한 페이지 당 게시물 수 
		model.addAttribute("currentCategory", categoryName);				// 현재 선택한 카테고리 이름
		
		List<String> categoryList = new ArrayList<String>(); 
		categoryList.add("all");
		categoryList.addAll(jsBoardService.findCategoryNames());			// 카테고리 가져오기
		
		model.addAttribute("categoryList", categoryList);

		return "/board/list";
	}
	
	// 글 목록에서 선택 삭제 수행
	@DeleteMapping
	@RequestMapping(value="/list/{pageNumber}", method=RequestMethod.DELETE) 
	public String delete(@PathVariable int pageNumber, BoardForm boardForm) {

		int result = jsBoardService.deleteInIds(boardForm.getId());			// 삭제한 게시물 수만큼 return 됨
		
		// 삭제 후 전체 게시물 수 변화에 따라 page를 재조정해서 redirect 
		return "redirect:/board/list/" + boardForm.redirectPageNumber(result, pageNumber);
	}
	
	// 글 보기 기본값(주소 뒷부분 생략 - 타 사이트 링크나 url로 들어올 경우)
	@RequestMapping(value="/view/{articleNumber}", method=RequestMethod.GET)
	public String view(Model model, @PathVariable int articleNumber) {
		return "redirect:/board/view/"+articleNumber + "/page/1/category/all";
	}
	
	// 글 보기
	@RequestMapping(value="/view/{articleNumber}/page/{pageNumber}/category/{categoryName}", method=RequestMethod.GET)
	public String view(Model model, HttpServletRequest request, 
			@PathVariable int articleNumber, @PathVariable int pageNumber, @PathVariable String categoryName) {
		
		Write article = jsBoardService.findOne(articleNumber);
		int prevArticle = jsBoardService.findPrevOrNextArticle(articleNumber, "prev");
		int nextArticle = jsBoardService.findPrevOrNextArticle(articleNumber, "next");
		
		jsBoardService.increaseHit(article, request);						// 조회수 증가 기능
		
		model.addAttribute("currentCategory", categoryName);
		model.addAttribute("article", article);
		model.addAttribute("prevArticle", prevArticle);
		model.addAttribute("nextArticle", nextArticle);
		model.addAttribute("pageNumber", pageNumber);
		
		return "/board/view";
	}

	// 글 보기 중 삭제
	@DeleteMapping
	@RequestMapping(value="/view/{articleNumber}", method=RequestMethod.DELETE)
	public String delArticle(Model model, @PathVariable int articleNumber, BoardForm boardForm) {
		jsBoardService.delArticle(articleNumber);
		
		int redirectPage = boardForm.getCurrentPage();
		String currentCategory = boardForm.getCurrentCategory();
		
		return "redirect:/board/list/"+redirectPage + "/category/" + currentCategory;
	}
	
	// 글 쓰기 양식으로 이동
	@RequestMapping(value="/save", method=RequestMethod.GET)
	public String goWriteForm(Model model) {

		List<String> categoryList = jsBoardService.findCategoryNames();		// 카테고리 가져오기
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("save", "insert");
		
		return "/board/form";
	}
	
	// 글 쓰기 수행
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String writeArticle(Model model, Write write) throws UnknownHostException {
		
		write.setDatetime(new Date());
		write.setLast(CommonUtil.getToday(new Date()));
		write.setIp(CommonUtil.getIpAddress());
		write.setBoardId(BOARD_ID);	// 임시
		write.setFile(0);	// 임시
		// 세션의 로그인한 정보에서 이름을 찾아서 넣기
		write.setMemberId(write.getName());	// 임시
		
		Write article = jsBoardService.save(write);
		
		return "redirect:/board/view/" + article.getId();
	}
	
	// 글 수정 양식으로 이동
	@RequestMapping(value="/save/{articleNumber}/page/{pageNumber}/category/{categoryName}", method=RequestMethod.GET)
	public String goUpdateForm(Model model, @PathVariable int articleNumber, @PathVariable int pageNumber ,@PathVariable String categoryName) {
		
		Write article = jsBoardService.findOne(articleNumber);
		
		List<String> categoryList = jsBoardService.findCategoryNames();		// 카테고리 가져오기
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("currentCategory", categoryName);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("save", "update");
		model.addAttribute("article", article);
		
		return "/board/form";
	}
	
	// 글 수정 수행
	@PutMapping
	@RequestMapping(value="/save/{articleNumber}/page/{pageNumber}/category/{categoryName}", method=RequestMethod.PUT)
	public String updateArticle(Write write, @PathVariable int articleNumber, @PathVariable int pageNumber ,@PathVariable String categoryName) throws UnknownHostException {
		
		Date datetime = jsBoardService.findOne(articleNumber).getDatetime();	// 원 글 쓸 당시의 시간
		write.setDatetime(datetime);										// 원 글 쓸 당시의 시간을 그대로 넣는다.
		write.setIp(CommonUtil.getIpAddress());								// 수정한 곳 IP
		write.setLast(CommonUtil.getToday(new Date()));						// 수정한 시간
		
		Write article = jsBoardService.save(write);
		
		return "redirect:/board/view/" + article.getId() + "/page/" + pageNumber + "/category/" + categoryName;
	}
}
