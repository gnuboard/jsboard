package kr.sir.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sir.model.GroupMember;
import kr.sir.model.Member;
import kr.sir.repository.admin.GroupMemberRepository;
import kr.sir.repository.admin.MemberRepository;

@Service
public class MemberService {

	private MemberRepository memberRepository;
	private GroupMemberRepository groupMemberRepository; 
	
	@Autowired
	public void setMemberRepository(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Autowired
	public void setGroupMemberRepository(GroupMemberRepository groupMemberRepository) {
		this.groupMemberRepository = groupMemberRepository;
	}

	public String findAdmin() {
		return memberRepository.findById("admin");
	}

	public List<Member> findAll() {
		return memberRepository.findAll();
	}
	
	public List<GroupMember> AllGroupMember() {
		return groupMemberRepository.findAll();
	}

}
