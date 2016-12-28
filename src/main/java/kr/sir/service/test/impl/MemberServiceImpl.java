package kr.sir.service.test.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sir.domain.Member;
import kr.sir.domain.repository.test.TestMemberRepository;
import kr.sir.service.test.MemberService;

@Service("testServiceImpl")
public class MemberServiceImpl implements MemberService{

	@Autowired
	private TestMemberRepository memberRepository;
	
	@Override
	// findMemberById()
	public String findMemberById(String memberId) {
		return memberRepository.findByMemberId(memberId);
	}
	
	@Override
	// Member findAll()
	public List<Member> findAll() {
		return memberRepository.findAll();
	}
	
}
