package kr.sir.controller.board;

import java.net.URLEncoder;
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
import kr.sir.service.board.BbsService;

@Controller
@RequestMapping(value="/board")
public class BbsController {
	
	private int BOARD_ID = 1;											// 게시판 번호 (임시로 상수, board에서 가지고 와야 함)
	private final int PAGE_PER_POSTS = 10;									// 게시판 페이지 당 게시물 수 (임시로 상수, board에서 가지고 와야 함)
	
	private BbsService bbsService;
	
	@Autowired
	public void setBoardService(BbsService bbsService) {
		this.bbsService = bbsService;
	}

	// 글 목록 기본값(주소 뒷부분 생략 - 타 사이트 링크나 url로 들어올 경우) 
	@RequestMapping(value="/{boardName}/list", method=RequestMethod.GET)
	public String list(Model model, @PathVariable String boardName) throws Exception {

		List<String> categoryList = bbsService.findCategoryNames();		// 카테고리 가져오기
		model.addAttribute("categoryList", categoryList);
		
		return "redirect:/board/" + URLEncoder.encode(boardName,"UTF-8")
					+ "/list/1/category/all";
	}
	
	// 글 목록
	@RequestMapping(value="/{boardName}/list/{pageNumber}/category/{categoryName}", method=RequestMethod.GET)
	public String listWithPageNumber(Model model, @PathVariable String boardName
			, @PathVariable int pageNumber, @PathVariable String categoryName) {

		// boardName을 boardId로 변경
		
		int paramCurrentPage = pageNumber - 1;								// 현재 몇 페이지 인지	
		
		PageRequest pageRequest = new PageRequest(paramCurrentPage, PAGE_PER_POSTS, new Sort(Direction.DESC, "id"));
		// 카테고리로 검색한 게시판 내용에 Paging, sorting 처리해서 가져오기 (댓글 제외)
		Page<Write> result = bbsService.findByCategoryName(BOARD_ID, categoryName, pageRequest);
		
		model = CommonUtil.pagingInfo(result, model);
		model.addAttribute("pagePerPosts", PAGE_PER_POSTS);					// 한 페이지 당 게시물 수 
		model.addAttribute("currentCategory", categoryName);				// 현재 선택한 카테고리 이름
		
		List<String> categoryList = new ArrayList<String>(); 
		categoryList.add("all");
		categoryList.addAll(bbsService.findCategoryNames());				// 카테고리 가져오기
		
		model.addAttribute("boardName", boardName);
		model.addAttribute("categoryList", categoryList);

		return "/board/list";
	}
	
	// 글 목록에서 선택 삭제
	@DeleteMapping
	@RequestMapping(value="/list/{pageNumber}", method=RequestMethod.DELETE) 
	public String delete(@PathVariable int pageNumber, BoardForm boardForm) throws Exception {

		// 글 삭제할 때 글에 포함된 댓글까지 함께 지우기 위해 댓글 id들까지 함께 가져온다.
		String deleteIds = bbsService.findIdsWithCommentIds(boardForm.getSelectedId());
		
		int result = bbsService.deleteInIds(deleteIds);	// 삭제한 게시물 수만큼 return 됨
		String currentCategory = boardForm.getCurrentCategory();
		String boardName = boardForm.getBoardName();
		
		// 삭제 후 전체 게시물 수 변화에 따라 page를 재조정해서 redirect 
		return "redirect:/board/" + URLEncoder.encode(boardName,"UTF-8")
					+ "/list/" + boardForm.redirectPageNumber(result, pageNumber)
					+ "/category/" + URLEncoder.encode(currentCategory,"UTF-8");
	}
	
	// 글 보기 기본값(주소 뒷부분 생략 - 타 사이트 링크나 url로 들어올 경우)
	@RequestMapping(value="/{boardName}/view/{articleNumber}", method=RequestMethod.GET)
	public String view(Model model, @PathVariable int articleNumber, @PathVariable String boardName) throws Exception{
		return "redirect:/board/" + URLEncoder.encode(boardName,"UTF-8")
			+ "/view/"+articleNumber + "/page/1/category/all";
	}
	
	// 글 보기
	@RequestMapping(value="/{boardName}/view/{articleNumber}/page/{pageNumber}/category/{categoryName}", method=RequestMethod.GET)
	public String view(Model model, HttpServletRequest request 
			, @PathVariable int articleNumber, @PathVariable String boardName
			, @PathVariable int pageNumber, @PathVariable String categoryName) {
		
		Write article = bbsService.findOne(articleNumber);
		int prevArticle = bbsService.findPrevOrNextArticle(articleNumber, "prev");
		int nextArticle = bbsService.findPrevOrNextArticle(articleNumber, "next");
		
		bbsService.increaseHit(article, request);						// 조회수 증가 기능
		
		// 댓글 list를 가져온다.
		List<Write> commentList = bbsService.findByParentAndIsComment(articleNumber, 1);

		model.addAttribute("boardName", boardName);
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
	@RequestMapping(value="/view", method=RequestMethod.DELETE)
	public String delArticle(Write write, Model model, BoardForm boardForm) throws Exception {
		
		// 글 삭제할 때 글에 포함된 댓글까지 함께 지우기 위해 댓글 id들까지 함께 가져온다.
		String deleteIds = bbsService.findIdsWithCommentIds(""+write.getId());
		
		bbsService.deleteInIds(deleteIds);	// 댓글까지 함께 삭제
		
		int redirectPage = boardForm.getCurrentPage();
		
		String boardName = boardForm.getBoardName();
		String category = boardForm.getCurrentCategory();
		return "redirect:/board/" + URLEncoder.encode(boardName,"UTF-8")
					+ "/list/" + redirectPage
					+ "/category/" + URLEncoder.encode(category,"UTF-8");
	}
	
	// 글 쓰기 양식으로 이동
	@RequestMapping(value="/{boardName}/save", method=RequestMethod.GET)
	public String goWriteForm(Model model, @PathVariable String boardName) {

		List<String> categoryList = bbsService.findCategoryNames();		// 카테고리 가져오기
		
		model.addAttribute("boardName", boardName);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("saveType", "insert");
		
		return "/board/form";
	}
	
	// 글 쓰기 수행
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String writeArticle(Model model, Write write, BoardForm boardForm) throws Exception {
		
		String reply = "";
		int wrNum = 0;									
		if(boardForm.getIsReply() == 1) {				// 답변 글 - 기존 wr_num 그대로
			wrNum = write.getNum();
//			reply = bbsService.createReply()
		} else {										// 일반 글 - 기존 wr_num에 -1
			wrNum += bbsService.findMinNum() -1;		// 게시판에서 가장 작은 wr_num 가져와서 -1
		}
		write.setNum(wrNum);
		write.setParent(0);
		write.setDatetime(new Date());
		write.setLast(CommonUtil.getToday(new Date()));
		write.setIp(CommonUtil.getIpAddress());
		write.setBoardId(BOARD_ID);	// 임시
		write.setFile(0);	// 임시
		// 세션의 로그인한 정보에서 이름을 찾아서 넣기
		write.setMemberId(write.getName());	// 임시

		Write article = bbsService.save(write);
		
		// 원글일 때 parent 값을 id값과 동일하게 저장
		article.setParent(article.getId());				
		bbsService.save(article);						

		String boardName = boardForm.getBoardName();
		return "redirect:/board/" + URLEncoder.encode(boardName,"UTF-8")
					+ "/view/" + article.getId();
	}
	
	// 글 수정 양식으로 이동
	@RequestMapping(value="/{boardName}/save/{articleNumber}/page/{pageNumber}/category/{categoryName}", method=RequestMethod.GET)
	public String goUpdateForm(Model model, @PathVariable String boardName
			, @PathVariable int articleNumber, @PathVariable int pageNumber
			, @PathVariable String categoryName) {
		
		Write article = bbsService.findOne(articleNumber);
		
		List<String> categoryList = bbsService.findCategoryNames();		// 카테고리 가져오기
		
		model.addAttribute("boardName", boardName);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("currentCategory", categoryName);
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("saveType", "update");
		model.addAttribute("article", article);
		
		return "/board/form";
	}
	
	// 글 수정 수행
	@PutMapping
	@RequestMapping(value="/save", method=RequestMethod.PUT)
	public String updateArticle(Write write, BoardForm boardForm) throws Exception {
		
		Write article = bbsService.findOne(write.getId());					// 수정할 글을 가져온다.
		article.setIp(CommonUtil.getIpAddress());							// 수정한 곳 IP
		article.setLast(CommonUtil.getToday(new Date()));					// 수정한 시간
		article.setName(write.getName());
		article.setEmail(write.getEmail());
		article.setHomepage(write.getHomepage());
		article.setOption(write.getOption());
		article.setCategoryName(write.getCategoryName());
		article.setSubject(write.getSubject());
		article.setContent(write.getContent());
		article.setLink11(write.getLink11());
		article.setLink12(write.getLink12());
		
		article = bbsService.save(article);									// 변경된 부분만 변경된다.
		
		String boardName = boardForm.getBoardName();
		String category = boardForm.getCurrentCategory();
		return "redirect:/board/" + URLEncoder.encode(boardName,"UTF-8")
					+ "/view/" + article.getId() + "/page/" + boardForm.getCurrentPage()
					+ "/category/" + category;
	}
	
	// 답변 글 쓰기 양식으로 이동
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String goAnswerForm(Write write, Model model, BoardForm boardForm) {
		
		Write baseArticle = bbsService.findOne(write.getId());			// 원글 가져오기
		// 원글의 정보 그대로 가져다 쓰는 데이터 셋팅 
		
		String subject = "Re: " + baseArticle.getSubject();
		String content = baseArticle.getContent();
		
		// 내용 가공
		System.out.println("content : " + content);
		
		Write answerArticle = new Write();
		
		answerArticle.setNum(baseArticle.getNum());						// num을 원글과 동일하게 
		answerArticle.setSubject(subject);
		answerArticle.setContent(content);
		answerArticle.setCategoryName(baseArticle.getCategoryName());
		answerArticle.setOption(baseArticle.getOption());
		
		model.addAttribute("article", answerArticle);					// 원글로 기본값을 셋팅한 답변 글 화면으로.
		
		List<String> categoryList = bbsService.findCategoryNames();		// 카테고리 가져오기
		
		model.addAttribute("boardName", boardForm.getBoardName());
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("currentCategory", boardForm.getCurrentCategory());
		model.addAttribute("currentPage", boardForm.getCurrentPage());
		model.addAttribute("saveType", "answer");
		
		return "/board/form";
	}
	
	// 댓글 쓰기
	@RequestMapping(value="/view/comment", method=RequestMethod.POST)
	public String writeComment(Write comment, BoardForm boardForm, Model model) throws Exception {
 
		// article : 원 글, comment : 댓글
		int originId = comment.getId();
		Write article = bbsService.findOne(originId);					// 원 글을 가져온다.
		Write baseComment = bbsService.findOne(boardForm.getBaseCommentId());	// 기준 댓글을 가져온다.
		
		comment.setId(bbsService.findMaxId()+1);
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
		
		int commentGroup = bbsService.appointComment(baseComment, article.getId());
		// 기준댓글(원글)의 comment_reply로 댓글의 commentReply를 만들어서 가져온다.
		String commentReply = bbsService.createCommentReply(originId, commentGroup, baseComment);
		
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
		
		comment = bbsService.save(comment);								// 댓글 저장

		article.setComment(article.getComment() + 1);						// 원 글의 댓글 수(isComment) 수정
		bbsService.save(article);
		
		String boardName = boardForm.getBoardName();
		String category = boardForm.getCurrentCategory();
		return "redirect:/board/" + URLEncoder.encode(boardName,"UTF-8")
					+ "/view/" + originId + "/page/" + boardForm.getCurrentPage()
					+ "/category/" + URLEncoder.encode(category,"UTF-8");
	}
	 
	// 댓글 수정
	@PutMapping
	@RequestMapping(value="/view/comment", method=RequestMethod.PUT)
	public String updateComment(Write comment, BoardForm boardForm, Model model) throws Exception {
		
		Write toUpdateComment = bbsService.findOne(boardForm.getBaseCommentId());
		toUpdateComment.setName(comment.getName());
		toUpdateComment.setContent(comment.getContent());
		toUpdateComment.setLast(CommonUtil.getToday(new Date()));
		toUpdateComment.setIp(CommonUtil.getIpAddress());
		
		bbsService.save(toUpdateComment);								// 댓글 수정
		
		String boardName = boardForm.getBoardName();
		String category = boardForm.getCurrentCategory();
		return "redirect:/board/" + URLEncoder.encode(boardName,"UTF-8")
					+ "/view/" + comment.getId() + "/page/"	+ boardForm.getCurrentPage()
					+ "/category/" + URLEncoder.encode(category,"UTF-8");
	}
	
	// 댓글 삭제
	@PutMapping
	@RequestMapping(value="/view/comment", method=RequestMethod.DELETE)
	public String deleteComment(Write comment, BoardForm boardForm, Model model) throws Exception {
		
		bbsService.delArticle(boardForm.getBaseCommentId());			// 댓글 삭제
		
		int originId = comment.getId();
		Write article = bbsService.findOne(originId);					// 원 글을 가져온다.
		
		article.setComment(article.getComment() - 1);						// 원 글의 댓글 수(isComment) 수정
		bbsService.save(article);
		
		String boardName = boardForm.getBoardName();
		String category = boardForm.getCurrentCategory();
		return "redirect:/board/" + URLEncoder.encode(boardName,"UTF-8")
					+ "/view/" + originId + "/page/" + boardForm.getCurrentPage()
					+ "/category/" + URLEncoder.encode(category,"UTF-8");
	}
}
