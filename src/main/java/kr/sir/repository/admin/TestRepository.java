package kr.sir.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.sir.model.Member;

public interface TestRepository extends JpaRepository<Member, Long>{
	Member findByMemberId(String mId);
}
	