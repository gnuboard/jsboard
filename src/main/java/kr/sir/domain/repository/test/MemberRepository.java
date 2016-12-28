package kr.sir.domain.repository.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.sir.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{
	@Query(value = "Select mb_no, mb_id from ahn.js5_member where mb_id = :memberId", nativeQuery = true)		// native SQL
//	@Query(value = "Select m.id, m.no, m.name from Member m where m.id = :memberId")		// JPQL	: return type = String
	String findById(@Param("memberId") String id);
	
	Member findByNo(int no);
}
