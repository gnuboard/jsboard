package kr.sir.domain.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.sir.domain.BoardGroup;

public interface BoardGroupRepository extends JpaRepository<BoardGroup, String>{

	public BoardGroup findById(String id);
}
