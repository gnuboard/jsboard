package kr.sir.controller.board;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.sir.domain.Write;
import kr.sir.domain.form.BoardForm;
import kr.sir.service.board.BbsService;

@Controller
@RequestMapping(value="/board")
public class BbsController {
	
	private BbsService bbsService;
	
	@Autowired
	public void setBoardService(BbsService bbsService) {
		this.bbsService = bbsService;
	}

	// 글 목록 기본값(주소 뒷부분 생략 - 타 사이트 링크나 url로 들어올 경우) 
	@RequestMapping(value="/{boardName}/list", method=RequestMethod.GET)
	public String list(Model model, @PathVariable String boardName) throws Exception {

		List<String> categoryList = bbsService.getCategoryList();		// 카테고리 가져오기
		model.addAttribute("categoryList", categoryList);
		
		return "redirect:/board/" + URLEncoder.encode(boardName,"UTF-8")
					+ "/list/1/category/all";
	}
	
	// 글 목록
	@RequestMapping(value="/{boardName}/list/{pageNumber}/category/{categoryName}", method=RequestMethod.GET)
	public String listWithPageNumber(Model model, @PathVariable String boardName
			, @PathVariable int pageNumber, @PathVariable String categoryName) {
		
		model = bbsService.getListWithPaging(model, pageNumber, categoryName, boardName);
		model.addAttribute("boardName", boardName);
		
		return "/board/list";
	}
	
	// 글 목록에서 선택 삭제
	@DeleteMapping
	@RequestMapping(value="/list/{pageNumber}", method=RequestMethod.DELETE) 
	public String delete(@PathVariable int pageNumber, BoardForm boardForm) throws Exception {

		int result = bbsService.deleteArticleWithComment(boardForm.getSelectedId());	// 댓글까지 함께 삭제
		
		// 삭제 후 전체 게시물 수 변화에 따라 page를 재조정해서 redirect 
		return "redirect:/board/" + URLEncoder.encode(boardForm.getBoardName(),"UTF-8")
					+ "/list/" + boardForm.redirectPageNumber(result, pageNumber)
					+ "/category/" + URLEncoder.encode(boardForm.getCurrentCategory(),"UTF-8");
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
		
		model = bbsService.getArticleView(model, articleNumber, request, boardName);

		model.addAttribute("boardName", boardName);
		model.addAttribute("currentCategory", categoryName);
		model.addAttribute("currentPage", pageNumber);
		
		return "/board/view";
	}

	// 글 보기 중 삭제
	@DeleteMapping
	@RequestMapping(value="/view", method=RequestMethod.DELETE)
	public String delArticle(Write write, Model model, BoardForm boardForm) throws Exception {
		
		bbsService.deleteArticleWithComment(""+write.getId());	// 댓글까지 함께 삭제
		
		return "redirect:/board/" + URLEncoder.encode(boardForm.getBoardName(),"UTF-8")
					+ "/list/" + boardForm.getCurrentPage()
					+ "/category/" + URLEncoder.encode(boardForm.getCurrentCategory(),"UTF-8");
	}
	
	// 글 쓰기 양식으로 이동
	@RequestMapping(value="/{boardName}/save", method=RequestMethod.GET)
	public String goWriteForm(Model model, @PathVariable String boardName) {

		List<String> categoryList = bbsService.getCategoryList();		// 카테고리 가져오기
		
		model.addAttribute("boardName", boardName);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("saveType", "insert");
		
		return "/board/form";
	}
	
	// 글 쓰기 수행
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String writeArticle(Model model, Write write, BoardForm boardForm, 
			@RequestParam("uploadFile") MultipartFile[] files, HttpServletRequest request) throws Exception {
		
		Write article = bbsService.insertArticle(write, boardForm, files);	// 글 저장
		bbsService.saveFile(article, files, request);					// 업로드한 파일 정보 서버와 DB에 저장

		// 새 글쓰기와 답변글쓰기를 구분 - 답변글이면 페이지와 카테고리 기억해서 추후 목록이나 수정으로 갈때 값을 전해준다.
		String returnUrl =  "redirect:/board/" + URLEncoder.encode(boardForm.getBoardName(),"UTF-8") + "/view/" + article.getId();
		if(boardForm.getIsReply() == 1) {								
			returnUrl += "/page/" + boardForm.getCurrentPage() + "/category/" + URLEncoder.encode(boardForm.getCurrentCategory(),"UTF-8");		
		}
		
		return returnUrl;		
	}
	
	// 글 수정 양식으로 이동
	@RequestMapping(value="/{boardName}/save/{articleNumber}/page/{pageNumber}/category/{categoryName}", method=RequestMethod.GET)
	public String goUpdateForm(Model model, @PathVariable String boardName
			, @PathVariable int articleNumber, @PathVariable int pageNumber
			, @PathVariable String categoryName) {
		
		Write article = bbsService.findOne(articleNumber);
		
		List<String> categoryList = bbsService.getCategoryList();		// 카테고리 가져오기
		
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
	public String updateArticle(Write write, BoardForm boardForm
			, @RequestParam("uploadFile") MultipartFile[] files, HttpServletRequest request) throws Exception {
		
		Write article = bbsService.updateArticle(write);				// 변경된 부분만 변경된다.
		bbsService.saveFile(article, files, request);					// 업로드한 파일 정보 서버와 DB에 저장
		
		return "redirect:/board/" + URLEncoder.encode(boardForm.getBoardName(),"UTF-8")
					+ "/view/" + article.getId()
					+ "/page/" + boardForm.getCurrentPage()
					+ "/category/" + URLEncoder.encode(boardForm.getCurrentCategory(),"UTF-8");
	}
	
	// 답변 글 쓰기 양식으로 이동
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String goAnswerForm(Write write, Model model, BoardForm boardForm) {
		
		List<String> categoryList = bbsService.getCategoryList();		// 카테고리 가져오기
		Write answerArticle = bbsService.createAnswerArticle(new Write(), write);	// 원글 정보를 바탕으로 답변 글 양식 생성

		model.addAttribute("article", answerArticle);					// 원글로 기본값을 셋팅한 답변 글 화면으로.
		model.addAttribute("baseArticleId", write.getId());				// 기준 글 id
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
 
		int originId = comment.getId();
		String result = bbsService.insertComment(comment, boardForm);
		
		if( ("ERROR").equals(result) ) {
			String message1 = "더 이상 답변하실 수 없습니다.";
			String message2 = "답변은 26개 까지만 가능합니다.";
			model.addAttribute("errorType", "tooManyComment");
			model.addAttribute("msg1", message1);
			model.addAttribute("msg2", message2);
			return "ERROR";
		}
		
		return "redirect:/board/" + URLEncoder.encode(boardForm.getBoardName(),"UTF-8")
					+ "/view/" + originId
					+ "/page/" + boardForm.getCurrentPage()
					+ "/category/" + URLEncoder.encode(boardForm.getCurrentCategory(),"UTF-8");
	}
	 
	// 댓글 수정
	@PutMapping
	@RequestMapping(value="/view/comment", method=RequestMethod.PUT)
	public String updateComment(Write comment, BoardForm boardForm, Model model) throws Exception {
		
		bbsService.updateComment(comment, boardForm);
		
		return "redirect:/board/" + URLEncoder.encode(boardForm.getBoardName(),"UTF-8")
					+ "/view/" + comment.getId()
					+ "/page/"	+ boardForm.getCurrentPage()
					+ "/category/" + URLEncoder.encode(boardForm.getCurrentCategory(),"UTF-8");
	}
	
	// 댓글 삭제
	@PutMapping
	@RequestMapping(value="/view/comment", method=RequestMethod.DELETE)
	public String deleteComment(Write comment, BoardForm boardForm, Model model) throws Exception {
		
		bbsService.deleteComment(comment, boardForm);
		
		return "redirect:/board/" + URLEncoder.encode(boardForm.getBoardName(),"UTF-8")
					+ "/view/" + comment.getId()
					+ "/page/" + boardForm.getCurrentPage()
					+ "/category/" + URLEncoder.encode(boardForm.getCurrentCategory(),"UTF-8");
	}
}
