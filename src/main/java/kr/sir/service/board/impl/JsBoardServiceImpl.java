package kr.sir.service.board.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kr.sir.domain.Write;
import kr.sir.domain.repository.board.JsBoardEmRepository;
import kr.sir.domain.repository.board.JsBoardRepository;
import kr.sir.service.board.JsBoardService;

@Service
public class JsBoardServiceImpl implements JsBoardService {

	private JsBoardRepository jsBoardRepository;
	private JsBoardEmRepository jsBoardEmRepository;

	@Autowired
	public void setBoardRepository(JsBoardRepository jsBoardRepository) {
		this.jsBoardRepository = jsBoardRepository;
	}
	
	@Autowired
	public void setJsBoardEmRepository(JsBoardEmRepository jsBoardEmRepository) {
		this.jsBoardEmRepository = jsBoardEmRepository;
	}

	// 게시판 가져오기
	@Override
	public Page<Write> findByBoardId(int boardId, PageRequest pageRequest) {
		return jsBoardRepository.findByBoardIdAndIsComment(boardId, pageRequest, 0);
	}
	
	// 게시판 가져오기 ( 카테고리로 검색 )
	@Override
	public Page<Write> findByCategoryName(int boardId, String categoryName, PageRequest pageRequest) {
		if(categoryName.equals("all")) {
			return jsBoardRepository.findByBoardIdAndIsComment(boardId, pageRequest, 0);
		} else {
			return jsBoardRepository.findByBoardIdAndCategoryNameAndIsComment(boardId, categoryName, pageRequest, 0);
		}
	}

	// 게시글 선택 삭제
	@Override
	public int deleteInIds(String ids) {
		return jsBoardEmRepository.delete(ids);
	}

	// 게시글 보기
	@Override
	public Write findOne(int articleNumber) {
		return jsBoardRepository.findOne(articleNumber);
	}

	// 이전 글 or 다음 글 번호 가져오기	
	@Override
	public int findPrevOrNextArticle(int articleNumber, String prevOrNext) {
		return jsBoardEmRepository.findPrevOrNextArticle(articleNumber, prevOrNext);
	}

	// 조회수 증가(경우에 따라 증가시키지 않음)
	@Override
	public void increaseHit(Write article, HttpServletRequest request) {
		
		String sessionName = "ARTICLE_VIEW_" + article.getBoardId() + "_" + article.getId();
		
		if(positiveIncreaseHitBySession(sessionName, article, request)) {
			article.setHit(article.getHit() + 1);
			jsBoardRepository.save(article);
			saveSession(sessionName, request);
		}

	}

	// 세션을 검사해서 조회수를 증가시킬 수 있는지 검사
	private boolean positiveIncreaseHitBySession(String sessionName, Write article, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if(session.getAttribute(sessionName) == null 
				|| (boolean) session.getAttribute(sessionName) == false) {
			return true;
		}
		return false;
		
	}

	// 조회수를 증가시킨 후 세션 저장
	private void saveSession(String sessionName, HttpServletRequest request) {
		request.getSession().setAttribute(sessionName, true);
	}

	// 뷰에서 게시글 삭제
	@Override
	public void delArticle(int articleId) {
		jsBoardRepository.delete(articleId);
	}

	// 카테고리 이름 리스트 가져오기
	@Override
	public List<String> findCategoryNames() {
		List<String> categoryList = jsBoardEmRepository.findCategoryNames();
		return categoryList;
	}

	// 글쓰기, 수정 기능
	@Override
	public Write save(Write write) {
		return jsBoardRepository.save(write);
	}

	// 게시판에서 가장 작은 wr_num 가져오기
	@Override
	public int findMinNum() {
		return jsBoardEmRepository.findMinNum();
	}

	// 게시판에서 가장 큰 wr_id 가져오기
	@Override
	public int findMaxId() {
		return jsBoardEmRepository.findMaxId();
	}

	// 게시글의 댓글 리스트 가져오기
	@Override
	public List<Write> findByParentAndIsComment(int articleNumber, int isComment) {
		return jsBoardRepository.findByParentAndIsCommentOrderByCommentAscCommentReplyAsc(articleNumber, isComment);
	}

	// 댓글에 들어갈 comment를 지정
	@Override
	public int appointComment(Write baseComment, int articleId) {
		int depth = baseComment.getCommentReply().length();					// 기준 댓글의 depth를 가져온다.
		if(depth == 0 && baseComment.getIsComment() == 0) {					// 기준이 원글인경우
			// 해당 글의 comment 중 가장 큰 값 + 1(comment단락을 나누기 위해)
			return jsBoardEmRepository.findMaxCommentById(articleId) + 1;	
		} else {
			// 기준 댓글의 comment그룹에 속하므로 값을 그대로 따라간다.
			return baseComment.getComment();
		}
	}
	
	// 작성할 댓글의 commentReply를 생성
	@Override
	public String createCommentReply(int articleNumber, int comment, Write baseComment) {
		// ex) 	기준 댓글의 comment_reply : AA, AAA에 댓글이 존재함. 결과 : AAB 
		String baseCommentReply = baseComment.getCommentReply();			// 기준 댓글의 commentReply
		int length = baseCommentReply.length();								// 기준 댓글의 commentReply의 길이
		if(length == 0 && baseComment.getIsComment() == 0) {				// 기준이 원글일 경우				
			return "";
		} else {
			String maxCommentReply = jsBoardEmRepository.findMaxCommentReplyByBaseComment(articleNumber, comment, baseCommentReply, 1);
			if(baseCommentReply.equals(maxCommentReply)) {
				return baseCommentReply + "A";
			}
			char lastChar = maxCommentReply.charAt(maxCommentReply.length() - 1);
			if(lastChar == 'G') {
				return "ERROR";
			}
			lastChar++;														// 마지막 문자 한자리 더함.
			return baseCommentReply + lastChar;
		}
		
	}

}
