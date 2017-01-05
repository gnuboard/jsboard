package kr.sir.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
		List<MemberGroupCount> memberslist = memberService.getAllMembersList("js1_");
		
		model.addAttribute("memberslist", memberslist);
		
		return "admin/member/list";
	}
	
	//회원추가 폼
	@RequestMapping(value={"/form/{type}"})
	public String showAddMemberForm(Model model,@PathVariable String type){
		model.addAttribute("type", type);
		
		return "admin/member/form";
	}
	//회원 수정 폼
	@RequestMapping(value={"/form/{type}/memberId/{memberId}"})
	public String showUpdateMemberForm(Model model,@PathVariable String type,@PathVariable String memberId){
		model.addAttribute("type", type);
		
		Member member=memberService.getOneMemer(memberId);
		
		model.addAttribute("member",member);
		
	/*	System.out.println("member.getEmailCertify() : "+member.getEmailCertify());		*/
		
		return "admin/member/form";
	}
	
	@RequestMapping(value={"/add"})
	public String memberAdd(Member member,String zipCode,String isCertify){
	
		if(isCertify.equals("0")){
			member.setAdult(0);
		}else if(!member.getCertify().equals("ipin") && !member.getCertify().equals("hp")){
			member.setAdult(0);
		}		
		
		if(zipCode.length()>0 && !zipCode.equals("")){			
			/*System.out.println("우편번호1:"+zipCode.substring(0, 3));
			System.out.println("우편번호2:"+zipCode.substring(3));			
			member.setZipCode1(zipCode.substring(0, 3));
			member.setZipCode2(zipCode.substring(3));*/
		}
	/*	if(member.getAddressJibeon().length()<0){
			member.setAddressJibeon("");
		}		
		member.setBirth("0000-00-00");
		member.setCertify("");
		member.setDatetime(new Date());
		member.setDupInfo("");
		member.setEmailCertify(new Date());
		member.setEmailCertify2("");
		member.setIp("33");
		member.setLoginIp("");
		member.setLostCertify("");
		member.setMemoCall("");
		member.setNickDate(new Date());
		member.setOpenDate(new Date());
		System.out.println("지번:"+member.getAddressJibeon());*/
				
		memberService.adminSavesMember(member);
		return "redirect:./list";
	}
	
	@RequestMapping(value={"/update"})
	public String memberUpdate(Member member){
		
		memberService.adminUpdatesMember(member);
		return "adm/member/form";
	}
	
	
	//관리자페이지에서 회원 삭제하거나 체크수정하기
	@RequestMapping(value={"/updateordelete"})
	public String memberDelete(HttpServletRequest request,Model model,@RequestParam("act_button") String actButton,@RequestParam(value="chk[]") List<String> chk){
		
		
		if(actButton.equals("선택수정")){
			for (String number : chk) {
				System.out.println(number);
			}
		}else if (actButton.equals("선택삭제")){
			for (String id : chk) {
				memberService.adminDeletesMember(Integer.parseInt(id));
			}
		}		
		return "forward:./list";
	}
		
	//관리자페이지에서 회원들 포인트 관리 내역 보기
	@RequestMapping(value = { "/pointlist" })
	public String pointList(Model model) {

		//포인트 건 수
		model.addAttribute("countPointlist", memberService.getCountPointlist());
		
		//전체 포인트 합계
	/*	model.addAttribute("totalPoint",memberService.getTotalPoint("js1_"));*/
		
		//전체포인트내용
		model.addAttribute("allPointContent", memberService.getAllPointContent("js1_"));	
		
		return "admin/member/point_list";
	}
	
	
	//관리자페이지에서 회원에게 포인트 추가또는 삭제
	@RequestMapping(value={"/addpoint"})
	public String addPoint(Model model,Point point){
		memberService.addPoint(point);
		
		return "forward:/adm/member/pointlist";
	}
	
	
	
	
	

}

















