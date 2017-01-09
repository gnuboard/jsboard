package kr.sir.service.board.impl;

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


	@Override
	public Page<Write> findByBoardId(int boardId, PageRequest pageRequest) {
		return jsBoardRepository.findByBoardId(boardId, pageRequest);
	}

	@Override
	public void delete(int writeId) {
		jsBoardRepository.delete(writeId);
	}

	@Override
	public int deleteInIds(String ids) {
		return jsBoardEmRepository.delete(ids);
	}

}
