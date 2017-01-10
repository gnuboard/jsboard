package kr.sir.service.board;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import kr.sir.domain.Write;

public interface JsBoardService {
	
	// 게시판 가져오기
	public Page<Write> findByBoardId(int boardId, PageRequest pageRequest);
	
	// 게시글 선택 삭제
	public int deleteInIds(String ids);

	// 게시글 보기
	public Write findOne(int articleNumber);

	// 이전 글 or 다음 글 번호 가져오기
	public int findPrevOrNextArticle(int articleNumber, String prevOrNext);

	// 조회수 증가(경우에 따라 증가시키지 않음)
	public void increaseHit(Write article, HttpServletRequest request);

	// 뷰에서 게시글 삭제
	public void delArticle(int articleId);

}
