package kr.sir.service.admin;

import java.util.List;

import kr.sir.domain.Member;

public interface MemberService {

	public List<Member> getAllMembers();
	public String getCountRetiredMembers();
	public int getCountBlockedMembers();
	public long getCountAllMember();

}
