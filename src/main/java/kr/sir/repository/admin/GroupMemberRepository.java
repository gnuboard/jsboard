package kr.sir.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.sir.model.GroupMember;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Integer>{

}
