package kr.sir.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.sir.model.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{
	Member findByMemberId(String mId);
}
