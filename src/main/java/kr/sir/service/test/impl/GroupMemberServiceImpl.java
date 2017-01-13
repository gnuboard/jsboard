package kr.sir.service.test.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sir.domain.BoardGroupMember;
import kr.sir.domain.repository.test.TestGroupMemberRepository;
import kr.sir.service.test.GroupMemberService;

@Service
public class GroupMemberServiceImpl implements GroupMemberService {
	
	private TestGroupMemberRepository groupMemberRepository; 
	
	@Autowired
	public void setGroupMemberRepository(TestGroupMemberRepository groupMemberRepository) {
		this.groupMemberRepository = groupMemberRepository;
	}

	// findMemberByNo()
	public BoardGroupMember findMemberByNo(int id) {
		return groupMemberRepository.findById(id);
	}

	// GroupMember findAll()
	public List<BoardGroupMember> AllGroupMember() {
		return groupMemberRepository.findAll();
	}
}
