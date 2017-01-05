package kr.sir.service.admin;

import java.util.List;

import kr.sir.domain.Member;
import kr.sir.domain.MemberGroupCount;
import kr.sir.domain.Point;
import kr.sir.domain.PointBaseEntity;
import kr.sir.domain.PointJoinMember;

public interface MemberService {

	//???
	public List<Member> getAllMembers();
	
	//탈퇴한 회원 명수
	public String getCountRetiredMembers();
	
	//차단된 회원 명수
	public String getCountBlockedMembers();
	
	//모든 회원 명수
	public long getCountAllMembers();	
	
	//모든 회원정보
	public List<MemberGroupCount>getAllMembersList(String prefix);
	
	//회원한명검색
	public Member getOneMemer(String memberId);
	
	//관리자가 회원 추가,수정
	public void adminSavesMember(Member member,String isCertify);
	
	//관리자가 회원 삭제
	public void adminDeletesMember(int id);
	
	//관리자가 회원정보수정
	public void adminUpdatesMember(Member member);
	
	//포인트관리 총 건수
	public long getCountPointlist();
	
	//모든 회원 포인트 합계
	public int getTotalPoint(String prefix);
	
	//모든 회원 포인트 내역
	public List<PointJoinMember> getAllPointContent(String prefix);
	
	//관리자가 회원에게 포인트 추기
	public String addPoint(Point point);
	

}
