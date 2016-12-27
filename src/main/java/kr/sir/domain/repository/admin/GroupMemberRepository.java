package kr.sir.domain.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.sir.domain.GroupMember;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Integer>{

	GroupMember findById(int id);

}
