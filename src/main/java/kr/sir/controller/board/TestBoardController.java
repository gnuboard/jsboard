package kr.sir.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.sir.domain.Write;
import kr.sir.service.board.BbsService;

@Controller
@RequestMapping("/board/test")
public class TestBoardController {
	
	private BbsService jsBoardService;
	
	private final int COMMENT_GROUP = 1;
	private final int ORIGIN_ARTICLE = 225;
	
	@Autowired
	public void setJsBoardService(BbsService jsBoardService) {
		this.jsBoardService = jsBoardService;
	}

	@RequestMapping(value = "/makeCommentReply")
	public String testMakeCommentReply(Model model){
		Write comment = new Write();
		comment.setComment(1);
		comment.setIsComment(1);
		comment.setCommentReply("AB");
		String commentReply = jsBoardService.createCommentReply(ORIGIN_ARTICLE, COMMENT_GROUP, comment);
		
		model.addAttribute("originCommentReply", comment.getCommentReply());
		model.addAttribute("commentReply", commentReply);
		
		return "/board/test";
	}
}
