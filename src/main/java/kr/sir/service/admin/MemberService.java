package kr.sir.service.admin;

import java.util.List;

import kr.sir.domain.Member;

public interface MemberService {

	public List<Member> getAllMembers();
	public String getCountRetiredMembers();
	public String getCountBlockedMembers();
	public long getCountAllMembers();	
	public List<Member >getAllMemberList(String prefix);
	public Member getOneMemer(String memberId);

}
