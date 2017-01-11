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
		return jsBoardRepository.findByBoardId(boardId, pageRequest);
	}
	
	// 게시판 가져오기 ( 카테고리로 검색 )
	@Override
	public Page<Write> findByCategoryName(int boardId, String categoryName, PageRequest pageRequest) {
		if(categoryName.equals("all")) {
			return jsBoardRepository.findByBoardId(boardId, pageRequest);
		} else {
			return jsBoardRepository.findByBoardIdAndCategoryName(boardId, categoryName, pageRequest);
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

}
