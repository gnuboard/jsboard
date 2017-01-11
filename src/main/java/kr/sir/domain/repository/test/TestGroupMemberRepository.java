package kr.sir.domain.repository.test;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.sir.domain.BoardGroupMember;

public interface TestGroupMemberRepository extends JpaRepository<BoardGroupMember, Integer>{

	BoardGroupMember findById(int id);

}
