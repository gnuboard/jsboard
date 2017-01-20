package kr.sir.domain.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.sir.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

	// 게시판 이름으로 게시판을 가져온다.
	Board findByTable(String boardName);
	
}
