package kr.sir.domain.repository.test;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.sir.domain.GroupMember;

public interface TestGroupMemberRepository extends JpaRepository<GroupMember, Integer>{

	GroupMember findById(int id);

}
