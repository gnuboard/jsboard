package kr.sir.service.test.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sir.domain.GroupMember;
import kr.sir.domain.repository.test.GroupMemberRepository;
import kr.sir.service.test.GroupMemberService;

@Service
public class GroupMemberServiceImpl implements GroupMemberService {
	
	private GroupMemberRepository groupMemberRepository; 
	
	@Autowired
	public void setGroupMemberRepository(GroupMemberRepository groupMemberRepository) {
		this.groupMemberRepository = groupMemberRepository;
	}

	// findMemberByNo()
	public GroupMember findMemberByNo(int id) {
		return groupMemberRepository.findById(id);
	}

	// GroupMember findAll()
	public List<GroupMember> AllGroupMember() {
		return groupMemberRepository.findAll();
	}
}
