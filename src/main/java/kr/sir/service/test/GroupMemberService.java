package kr.sir.service.test;

import java.util.List;

import kr.sir.domain.GroupMember;

public interface GroupMemberService {
	public GroupMember findMemberByNo(int id);
	public List<GroupMember> AllGroupMember();
}
