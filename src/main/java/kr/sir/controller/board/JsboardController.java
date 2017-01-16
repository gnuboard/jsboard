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
import kr.sir.domain.form.BoardForm;
import kr.sir.service.board.JsBoardService;

@Controller
@RequestMapping(value="/board")
public class JsboardController {
	
	private final int BOARD_ID = 1;											// 게시판 번호 (임시로 상수, board에서 가지고 와야 함)
	private final int PAGE_PER_POSTS = 10;									// 게시판 페이지 당 게시물 수 (임시로 상수, board에서 가지고 와야 함)
	
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
		// 카테고리로 검색한 게시판 내용에 Paging, sorting 처리해서 가져오기 (댓글 제외)
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

		int result = jsBoardService.deleteInIds(boardForm.getSelectedId());	// 삭제한 게시물 수만큼 return 됨
		
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
		
		// 댓글 list를 가져온다.
		List<Write> commentList = jsBoardService.findByParentAndIsComment(articleNumber, 1);

		model.addAttribute("currentCategory", categoryName);
		model.addAttribute("article", article);
		model.addAttribute("prevArticle", prevArticle);
		model.addAttribute("nextArticle", nextArticle);
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("commentList", commentList);
		
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
	public String writeArticle(Model model, Write write, BoardForm boardForm) throws UnknownHostException {
		
		int wrNum = jsBoardService.findMinNum();		// 게시판에서 가장 작은 wr_num 가져오기
		if(boardForm.getIsReply() == 1) {				// 답변 글 - 기존 wr_num 그대로
			wrNum = write.getNum();
		} else {										// 일반 글 - 기존 wr_num에 -1
			wrNum += -1;
		}
		
		write.setNum(wrNum);
		write.setParent(jsBoardService.findMaxId() + 1);
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
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("save", "update");
		model.addAttribute("article", article);
		
		return "/board/form";
	}
	
	// 글 수정 수행
	@PutMapping
	@RequestMapping(value="/save", method=RequestMethod.PUT)
	public String updateArticle(Write write, BoardForm boardForm) throws UnknownHostException {
		
		Date datetime = jsBoardService.findOne(write.getId()).getDatetime();// 원 글 쓸 당시의 시간
		write.setDatetime(datetime);										// 원 글 쓸 당시의 시간을 그대로 넣는다.
		write.setIp(CommonUtil.getIpAddress());								// 수정한 곳 IP
		write.setLast(CommonUtil.getToday(new Date()));						// 수정한 시간
		
		Write article = jsBoardService.save(write);
		
		return "redirect:/board/view/" + article.getId() + "/page/" + boardForm.getCurrentPage() + "/category/" + boardForm.getCurrentCategory();
	}
	
	// 댓글 쓰기
	@RequestMapping(value="/view/comment", method=RequestMethod.POST)
	public String writeComment(Write comment, BoardForm boardForm, Model model) throws UnknownHostException {
 
		// article : 원 글, comment : 댓글
		int originId = comment.getId();
		Write article = jsBoardService.findOne(originId);					// 원 글을 가져온다.
		Write baseComment = jsBoardService.findOne(boardForm.getBaseCommentId());	// 기준 댓글을 가져온다.
		
		comment.setId(jsBoardService.findMaxId()+1);
		comment.setNum(article.getNum());									// 원 글의 wr_num를 써줌으로 어떤 글의 댓글인지 표시한다.
		comment.setParent(article.getId());									// 원 글의 wr_id를 써줌으로 어떤 글의 댓글인지 표시한다.
		comment.setCategoryName(article.getCategoryName());					// 원 글의 카테고리 이름을 넣는다.
		comment.setIsComment(1);											// 댓글 1, 원글 0
		comment.setDatetime(new Date());
		comment.setLast(CommonUtil.getToday(new Date()));
		comment.setIp(CommonUtil.getIpAddress());
		comment.setFile(0);
		// 임시
		comment.setMemberId(comment.getName());					// (회원일 땐 세션에서 로그인한 ID 가져오기)
		comment.setBoardId(BOARD_ID);							
		
		int commentGroup = jsBoardService.appointComment(baseComment, article.getId());
		// 기준댓글(원글)의 comment_reply로 댓글의 commentReply를 만들어서 가져온다.
		String commentReply = jsBoardService.createCommentReply(originId, commentGroup, baseComment);
		
		if( ("ERROR").equals(commentReply) ) {
			String message1 = "더 이상 답변하실 수 없습니다.";
			String message2 = "답변은 26개 까지만 가능합니다.";
			model.addAttribute("errorType", "tooManyComment");
			model.addAttribute("msg1", message1);
			model.addAttribute("msg2", message2);
			return "/board/error";
		}
		
		comment.setComment(commentGroup);
		comment.setCommentReply(commentReply);
		
		comment = jsBoardService.save(comment);								// 댓글 저장

		article.setComment(article.getComment()+1);							// 원 글의 댓글 수(isComment) 수정
		jsBoardService.save(article);
		
		return "redirect:/board/view/" + originId + "/page/" + boardForm.getCurrentPage() + "/category/" + boardForm.getCurrentCategory();
	}
	
	// 댓글 수정
	@PutMapping
	@RequestMapping(value="/view/comment", method=RequestMethod.PUT)
	public String updateComment(Write comment, BoardForm boardForm, Model model) throws UnknownHostException {
		
		System.out.println("코멘트 수정!");
		
		Write article = jsBoardService.findOne(boardForm.getBaseCommentId());
		article.setName(comment.getName());
		article.setContent(comment.getContent());
		article.setLast(CommonUtil.getToday(new Date()));
		article.setIp(CommonUtil.getIpAddress());
		
		jsBoardService.save(article);
		
		return "redirect:/board/view/" + comment.getId() + "/page/" + boardForm.getCurrentPage() + "/category/" + boardForm.getCurrentCategory();
	}
	
}
