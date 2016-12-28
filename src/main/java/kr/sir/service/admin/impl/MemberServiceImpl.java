package kr.sir.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sir.domain.Member;
import kr.sir.domain.repository.admin.MemberRepository;
import kr.sir.service.admin.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	//전체멤버목록
	@Override
	public List<Member> getAllMembers() {		
		return memberRepository.findAll();
	}
	
	//총회원수
	@Override
	public long getCountAllMember(){		
		return memberRepository.count();
	}
	
	//탈퇴회원수
	@Override
	public String getCountRetiredMembers(){
		
		return memberRepository.getCountRetiredMembers();
	} 
	
	//차단회원수
	@Override
	public int getCountBlockedMembers(){
		return 1;
	}
}
