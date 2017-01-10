package kr.sir.domain.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.sir.domain.Group;

public interface BoardGroupRepository extends JpaRepository<Group, String>{

	public Group findById(String id);
}
