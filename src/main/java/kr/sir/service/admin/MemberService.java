package kr.sir.service.admin;

import kr.sir.model.Member;
import kr.sir.repository.admin.MemberRepository;

public class MemberService {

	private MemberRepository memberRepository;
	
	public void setMemberRepository(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Member findAdmin() {
		return memberRepository.findByMemberId("admin");
	}
}
