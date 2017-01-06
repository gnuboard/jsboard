package kr.sir.service.board.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kr.sir.domain.Write;
import kr.sir.domain.repository.board.BoardRepository;
import kr.sir.service.board.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardRepository boardRepository;

	@Autowired
	public void setBoardRepository(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	@Override
	public Page<Write> findByBoardId(int boardId, PageRequest pageRequest) {
		return boardRepository.findByBoardId(boardId, pageRequest);
	}

	@Override
	public String getTotalCount() {
		return boardRepository.getTotalCount();
	}
	
}
