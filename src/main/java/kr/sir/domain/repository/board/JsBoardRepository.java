package kr.sir.domain.repository.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.sir.domain.Write;

@Repository
public interface JsBoardRepository extends JpaRepository<Write, Integer>{
	
	// Paging 처리한 게시판 가져오기
	public Page<Write> findByBoardId(int boardId, Pageable pageable);
	
	// 카테고리 선택한 게시물 가져오기(+페이징)
	public Page<Write> findByBoardIdAndCategoryName(int boardId, String categoryName, Pageable pageable);
	
}
