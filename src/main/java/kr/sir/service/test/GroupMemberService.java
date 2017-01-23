package kr.sir.service.test;

import java.util.List;

import kr.sir.domain.BoardGroupMember;

public interface GroupMemberService {
	public BoardGroupMember findMemberByNo(int id);
	public List<BoardGroupMember> AllGroupMember();
}
