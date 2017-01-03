package kr.sir.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.sir.config.DataConfig;
import kr.sir.domain.Member;
import kr.sir.domain.MemberGroupCount;
import kr.sir.service.admin.MemberService;;

@Controller
@RequestMapping("/adm/member")
public class MemberController {
	
	private MemberService memberService;
	private DataConfig dataConfig;

	@Autowired
	public void setDataConfig(DataConfig dataConfig) {
		this.dataConfig = dataConfig;
	}
	@Autowired
	public void setMemberService(MemberService memberService){
		this.memberService=memberService;
	}
	
/*	@ModelAttribute("config")
	public Config getConfig(){
		return dataConfig.getConfig();
	}
	*/
	
	
	@RequestMapping(value={"/list","/"})
	public String memberList(Model model,String sfl,String sod,String stx,String sst){		
		

	

		//설정파일
		/*model.addAttribute("config", dataConfig.config());*/

		//총회원수
		model.addAttribute("countallmembers", memberService.getCountAllMembers());
		//탈퇴회원수
		model.addAttribute("countretiredmembers",memberService.getCountRetiredMembers());
		//차단회원수
		model.addAttribute("countblockedmembers",memberService.getCountBlockedMembers());
		                    
		
		// 멤버리스트 + 접근 가능 그룹 수
		List<MemberGroupCount> memberslist = memberService.getAllMembersList("js");
		for (MemberGroupCount member : memberslist) {
			System.out.println(member.toString());
		}
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
		
		if(zipCode.length()>0){
			
			System.out.println("우편번호1:"+zipCode.substring(0, 3));
			System.out.println("우편번호2:"+zipCode.substring(3));
			
			member.setZipCode1(zipCode.substring(0, 3));
			member.setZipCode2(zipCode.substring(3));
		}
		if(member.getAddressJibeon().length()<0){
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
		System.out.println("지번:"+member.getAddressJibeon());
		
		
		memberService.adminSavesMember(member);
		return "admin/member/form";
	}
	
	@RequestMapping(value={"/update"})
	public String memberUpdate(Member member){
		
		return "admin/member/form";
	}
	
	
	
	
	
	
	
	@RequestMapping(value={"/pointlist"})
	public String pointList(Model model){
		model.addAttribute("test","포인트리스트");
		return "admin/member/point_list";
	}
	

}
