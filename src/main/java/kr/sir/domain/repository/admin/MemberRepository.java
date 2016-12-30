package kr.sir.domain.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.sir.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{
	
	//탈퇴 회원수
	//@Query(value = "Select mb_no, mb_id from ahn.js_member where mb_id = :memberId", nativeQuery = true)		// native SQL
	@Query(value = "select count(m.id) from Member m where m.leaveDate <> '' ")		// JPQL	: return type = String
	public String getCountRetiredMembers();
	
	//차단회윈수
	@Query(value="select count(m.id) from Member m where m.interceptDate <> '' ")
	public String getCountBlockedMembers();

}
