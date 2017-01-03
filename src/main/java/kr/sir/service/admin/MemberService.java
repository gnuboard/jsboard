package kr.sir.service.admin;

import java.util.List;

import kr.sir.domain.Member;
import kr.sir.domain.MemberGroupCount;

public interface MemberService {

	public List<Member> getAllMembers();
	public String getCountRetiredMembers();
	public String getCountBlockedMembers();
	public long getCountAllMembers();	
	public List<MemberGroupCount>getAllMembersList(String prefix);
	public Member getOneMemer(String memberId);
	public void adminSavesMember(Member member);
	

}
