package kr.sir.domain.repository.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.sir.domain.Write;

@Repository
public interface JsBoardRepository extends JpaRepository<Write, Integer>{
	
	// Paging 처리한 게시판 가져오기 + 댓글 제외
	public Page<Write> findByBoardIdAndIsComment(int boardId, Pageable pageable, int isComment);
	
	// 카테고리 선택한 게시물 가져오기 + 페이징 + 댓글 제외
	public Page<Write> findByBoardIdAndCategoryNameAndIsComment(int boardId, String categoryName, Pageable pageable, int isComment);

	// 게시글의 댓글 리스트 가져오기
	public List<Write> findByParentAndIsCommentOrderByCommentAscCommentReplyAsc(int articleNumber, int isComment);
	
}
