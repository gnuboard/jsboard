package kr.sir.service.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import kr.sir.domain.Write;

public interface JsBoardService {
	
	// 게시판 가져오기
	public Page<Write> findByBoardId(int boardId, PageRequest pageRequest);
	
	// 게시글 선택 삭제
	public void delete(int writeId);

	public int deleteInIds(String ids);
}
