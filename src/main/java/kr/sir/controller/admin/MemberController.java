package kr.sir.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.sir.domain.Config;
import kr.sir.domain.Member;
import kr.sir.domain.MemberGroupCount;
import kr.sir.domain.Point;
import kr.sir.service.admin.ConfigService;
import kr.sir.service.admin.MemberService;;

@Controller
@RequestMapping("/adm/member")
public class MemberController {
	
	private MemberService memberService;
	private ConfigService configService;

	
	@Autowired
	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}
	@Autowired
	public void setMemberService(MemberService memberService){
		this.memberService=memberService;
	}
	
	//모든 컨트롤러에 config모델이 전달됨.
	@ModelAttribute("config")
	public Config getConfig(){
		return configService.getConfig();
	}
	
	
	//관리자페이지에서 회원목록 보기
	@RequestMapping(value={"/list","/"})
	public String memberList(Model model,String sfl,String sod,String stx,String sst){		
		
		//총회원수
		model.addAttribute("countallmembers", memberService.getCountAllMembers());
		//탈퇴회원수
		model.addAttribute("countretiredmembers",memberService.getCountRetiredMembers());
		//차단회원수
		model.addAttribute("countblockedmembers",memberService.getCountBlockedMembers());	                    
		
		// 멤버리스트 + 접근 가능 그룹 수
		List<MemberGroupCount> memberslist = memberService.getAllMembersList();
		
		model.addAttribute("memberslist", memberslist);
		
		return "admin/member/list";
	}
	
	//회원추가 폼
	@RequestMapping(value={"/form"})
	public String showAddMemberForm(Model model,String type){
		model.addAttribute("type", "add");
		
		return "admin/member/form";
	}
	
	//회원 수정 폼(추가폼이랑 같은 뷰를 사용)
	@RequestMapping(value={"/form/{memberId}"})
	public String showUpdateMemberForm(Model model,String type,@PathVariable String memberId){
		model.addAttribute("type", "update");
		
		Member member=memberService.getOneMemer(memberId);
		
		model.addAttribute("member",member);
		
	/*	System.out.println("member.getEmailCertify() : "+member.getEmailCertify());		*/
		
		return "admin/member/form";
	}
	
	//괸리자가 회원 추가
	@RequestMapping(value={"/add"},method=RequestMethod.POST)
	public String memberAdd(Member member,String zipCode,String isCertify){		
		
		System.out.println("member.id:="+member.getId()!=null?member.getId():"널이다");
		
		memberService.adminSavesMember(member,isCertify);
		return "redirect:./list";
	}
	
	
	//관리자가 회원 수정(회원 추가와 같은 메서드 사용)
	@RequestMapping(value={"/update"},method=RequestMethod.PUT) 
	public String memberUpdate(Member member,String isCertify){
		
		memberService.adminSavesMember(member,isCertify);
		return "redirect:./list";
	}
	
	
	//관리자 회원 리스트 페이지에서 회원 삭제
	@RequestMapping(value={"/updateordelete"},method=RequestMethod.DELETE)
	public String deleteMember(Model model,@RequestParam(value="chk[]") String chk){
			
	/*	System.out.println("chk="+chk);
			
		memberService.adminDeletesMember(chk);
		System.out.println("삭제완료");				
			
	 */
		return "redirect:../list";
	}
	
	//관리자 회원 리스트에서 회원 수정(미구현)
	@RequestMapping(value={"/updateordelete"},method=RequestMethod.PUT)
	public String updateMember(@RequestParam(value="chk[]") List<String> chk){
		
		System.out.println("수정완료");
		return "redirect:../list";
	}
	
	
		
	//관리자페이지에서 회원들 포인트 관리 내역 보기
	@RequestMapping(value={"/pointlist"})
	public String pointList(Model model) {

		//포인트 건 수
		model.addAttribute("countPointlist", memberService.getCountPointlist());
		
		//전체 포인트 합계 (sum)이안되서 대기중
		model.addAttribute("totalPoint",memberService.getTotalPoint());
		
		//전체포인트내용
		model.addAttribute("allPointContent", memberService.getAllPointContent());	
		
		return "admin/member/point_list";
	}
	
	
	//관리자페이지에서 회원에게 포인트증감	
	@RequestMapping(value={"/updatepoint"},method=RequestMethod.PUT)
	public String addPoint(Model model,Point point,HttpServletRequest request) {
		String msg=memberService.addPoint(point);
		
		System.out.println("포인트증감 메소드="+request.getMethod());
		System.out.println(" 포인트내용:"+point.getContent());
		System.out.println(" 회원아이디:"+point.getMemberId());
		System.out.println(" 회원포인트:"+point.getPoint());	
		
		//에러메시지를 출력하려고 했으나 실패함
	/*	model.addAttribute("msg",msg);		
		model.addAttribute("url", "/adm/member/pointlist");*/
		return "redirect:./pointlist";
	}
	
	
	
	
	
	// 포인트관리 내역 삭제
	@RequestMapping(value={"/deletepoint"}, method=RequestMethod.DELETE)
	public String deletePoint(Model model,@RequestParam(value="chk[]") List<String> chk,HttpServletRequest request ){
		
		//chk를 string으로 받아서 쿼리한번보내게 바꿔야함 em사용
		System.out.println("포인트내역삭제 method= " +request.getMethod());
		for (String id : chk) {
			memberService.deletePointlist(Integer.parseInt(id));
		}
		
		
		return "redirect:./pointlist";
	}
}

















