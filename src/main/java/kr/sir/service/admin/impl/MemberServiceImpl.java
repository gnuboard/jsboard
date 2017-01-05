package kr.sir.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sir.domain.Member;
import kr.sir.domain.MemberGroupCount;
import kr.sir.domain.Point;
import kr.sir.domain.PointBaseEntity;
import kr.sir.domain.PointJoinMember;
import kr.sir.domain.repository.admin.MemberEmRepository;
import kr.sir.domain.repository.admin.MemberRepository;
import kr.sir.domain.repository.admin.PointEmRepository;
import kr.sir.domain.repository.admin.PointRepository;
import kr.sir.service.admin.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	
	private MemberRepository memberRepository;
	private MemberEmRepository memberEmRepository;
	private PointRepository pointRepository;
	private PointEmRepository pointEmRepository;
	
	@Autowired
	public void setMemberRepository(MemberRepository memberRepository){
		this.memberRepository=memberRepository;
	}
	@Autowired
	public void setMemberEmRepository(MemberEmRepository memberEmRepository){
		this.memberEmRepository=memberEmRepository;
	}
	@Autowired
	public void setPointRepository(PointRepository pointRepository) {
		this.pointRepository = pointRepository;
	}
	@Autowired
	public void setPointEmRepository(PointEmRepository pointEmRepository){
		this.pointEmRepository=pointEmRepository;
	}	
	
	//전체멤버목록
	@Override
	public List<Member> getAllMembers() {		
		return memberRepository.findAll();
	}
	
	//총회원수
	@Override
	public long getCountAllMembers(){		
		return memberRepository.count();
	}
	
	//탈퇴회원수
	@Override
	public String getCountRetiredMembers(){		
		return memberRepository.getCountRetiredMembers();
	} 
	
	//차단회원수
	@Override
	public String getCountBlockedMembers(){
		return memberRepository.getCountBlockedMembers();
	}
	
	@Override
	public List<MemberGroupCount>getAllMembersList(String prefix) {
		// TODO Auto-generated method stub
		return memberEmRepository.getAllMembersList(prefix);
	}
	
	//아이디로 회원 전체 정보 셀렉트
	@Override
	public Member getOneMemer(String memberId) {		
		return memberRepository.findByMemberId(memberId);
	}
	
	//관리자가 회원추가
	public void adminSavesMember(Member member,String isCertify){
		if(isCertify.equals("0")){
			member.setAdult(0);
		}else if(!member.getCertify().equals("ipin") && !member.getCertify().equals("hp")){
			member.setAdult(0);
		}	
		memberRepository.save(member);
	}
	
	//괸리자가 회원 추방
	@Override
	public void adminDeletesMember(int id) {
		memberRepository.delete(id);
		
	}
	//관리자가 회원 수정
	@Override
	public void adminUpdatesMember(Member member) {
		
	}
	
	
	//포인트관리 건수
	@Override
	public long getCountPointlist() {		
		return pointRepository.count();
	}
	
	//모든 회원  포인트 합계
	public int getTotalPoint(String prefix){
		return pointEmRepository.getTotalPoint(prefix);
	}
	
	//모든 포인트 내역
	@Override
	public List<PointJoinMember> getAllPointContent(String prefix) {	
		return pointEmRepository.getAllPointContent(prefix);
	}
	
	//관리자가 회원에게 포인트 주기
	@Override
	public String addPoint(Point point) {
		Member member=memberRepository.findByMemberId(point.getMemberId());
		String msg="";
		if(member==null){
			msg="존재하는 회원아이디가 아닙니다.";
		}
		
		if((point.getPoint()<0) &&( point.getPoint() * (-1) > point.getMemberPoint())){
			msg="포인트를 깍는 경우 현재 포인트보다 작으면 안됩니다.";
		}		
		
		pointRepository.save(point);
		return "";
	}
	
	
	
	
	
}
