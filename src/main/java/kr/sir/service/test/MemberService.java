package kr.sir.service.test;

import java.util.List;

import kr.sir.domain.Member;

public interface MemberService {
	public String findMemberById(String memberId);
	public List<Member> findAll();
}
