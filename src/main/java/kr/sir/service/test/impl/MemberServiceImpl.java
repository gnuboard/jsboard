package kr.sir.service.test.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sir.domain.Member;
import kr.sir.domain.repository.test.MemberRepository;
import kr.sir.service.test.MemberService;

@Service
public class MemberServiceImpl implements MemberService{

	private MemberRepository memberRepository;
	
	@Autowired
	public void setMemberRepository(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	// findMemberById()
	public String findMemberById(String memberId) {
		return memberRepository.findById(memberId);
	}
	
	// Member findAll()
	public List<Member> findAll() {
		return memberRepository.findAll();
	}
	
}