package kr.sir.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sir.common.util.CommonUtil;
import kr.sir.domain.Member;
import kr.sir.domain.MemberGroupCount;
import kr.sir.domain.Point;
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
	public List<MemberGroupCount>getAllMembersList() {
		// TODO Auto-generated method stub
		return memberEmRepository.getAllMembersList(CommonUtil.getTablePrefix());
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
	//관리자가 회원 수정(아직 위에메소드를 같이사용중)
	@Override
	public void adminUpdatesMember(Member member) {
		
	}
	
	//괸리자 회원 리스트에서 회원 추방
	@Override
	public void adminDeletesMembers(String id) {
		/*memberEmRepository.deleteMembers(id);*/
		
	}
	
	
	//포인트관리 건수
	@Override
	public long getCountPointlist() {		
		return pointRepository.count();
	}
	
	//모든 회원  포인트 합계
	public int getTotalPoint(){
		return memberEmRepository.getTotalPoint(CommonUtil.getTablePrefix());
	}
	
	//모든 포인트 내역
	@Override
	public List<PointJoinMember> getAllPointContent() {	
		return pointEmRepository.getAllPointContent(CommonUtil.getTablePrefix());
	}
	
	//관리자가 회원에게 포인트 주기
	@Override
	public String addPoint(Point point) {
		String msg="";

		//1.아이디가 존재하는지 체크
		Member member=memberRepository.findByMemberId(point.getMemberId());
		
		if(member==null || member.getMemberId().isEmpty()){
			msg="존재 하지 않은 회원 ";
		}else if( (point.getPoint()<0) && (point.getPoint()*-1 > member.getPoint())){
			//2.포인트를 깍는 경우 현재 포인트보다 작으면 안됨.
			msg="포인트를 깍는 경우 현재 포인트보다 작으면 안됨.";
		}else{
			//3.현재 포인트에 받은 포인트를 더해 포인트를 총합함.
			int totalPoint=member.getPoint()+point.getPoint();
			
			point.setMemberPoint(totalPoint);
			
			member.setPoint(totalPoint);

			
			//포인트관리창에 관리 건수 추가
			pointRepository.save(point);		
			
			//회원디비에 포인트만 수정
			memberRepository.save(member);
			
			 msg="포인트추가&삭감 성공";
		}		
		return msg;
	}
	
	//관리자가 포인트리스트 삭제
	@Override
	public void deletePointlist(int id) {
		pointRepository.delete(id);
		
	}
	
	
		
}
