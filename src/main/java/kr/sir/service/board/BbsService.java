package kr.sir.service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import kr.sir.domain.Write;

public interface BbsService {
	
	// 게시판 가져오기
//	public Page<Write> findByBoardId(int boardId, PageRequest pageRequest);
	
	// 게시판 가져오기 ( 카테고리로 검색 )
	public Page<Write> findByCategoryName(int boardId, String categoryName, PageRequest pageRequest);
	
	// 글 삭제할 때 글에 포함된 댓글까지 함께 지우기 위해 댓글 id들까지 함께 가져온다.
	public String findIdsWithCommentIds(String selectedId);
	
	// 게시글 선택 삭제
	public int deleteInIds(String ids);

	// 게시글 보기
	public Write findOne(int articleNumber);

	// 이전 글 or 다음 글 번호 가져오기
	public int findPrevOrNextArticle(int articleNumber, String prevOrNext);

	// 조회수 증가(경우에 따라 증가시키지 않음)
	public void increaseHit(Write article, HttpServletRequest request);

	// 뷰에서 게시글 삭제
	public void delArticle(int articleNumber);

	// 카테고리 이름 리스트 가져오기 
	public List<String> findCategoryNames();
	
	// 글쓰기, 수정
	public Write save(Write write);
	
	// 게시판에서 가장 작은 wr_num 가져오기
	public int findMinNum();
	
	// 게시판에서 가장 큰 wr_id 가져오기
	public int findMaxId();

	// 게시글의 댓글 리스트 가져오기
	public List<Write> findByParentAndIsComment(int articleNumber, int isComment);
	
	// 작성할 댓글의 commentReply를 생성
	public String createCommentReply(int articleNumber, int comment, Write baseComment);

	// 댓글에 들어갈 comment를 지정
	public int appointComment(Write baseComment, int articleId);

}
