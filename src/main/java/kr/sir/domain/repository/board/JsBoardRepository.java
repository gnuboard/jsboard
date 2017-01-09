package kr.sir.domain.repository.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.sir.domain.Write;

@Repository
public interface JsBoardRepository extends JpaRepository<Write, Integer>{
	
	public Page<Write> findByBoardId(int boardId, Pageable pageable);
	
}
