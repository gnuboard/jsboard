package kr.sir.controller.test;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.sir.domain.Member;
import kr.sir.domain.MemberSearch;
import kr.sir.service.admin.CommonService;
import kr.sir.service.test.MemberService;

@Controller
@RequestMapping("/test/member")
public class TestMemberController {
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private MemberService memberService;
	
	@Value("${prefix}") 
	private String prefix;

	@RequestMapping(value="/", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String hello(Model model) {
		model.addAttribute("prefix", prefix);
		return "hello";
	}
	
	// test - member
	@RequestMapping(value="/view/MemberById", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String findMemberByIdUseJPQL(Model model) {
		String memberId = memberService.findMemberById("admin");
		model.addAttribute("id", memberId);
		return "test";
	}
	
	// 검색 테스트 페이지로 이동
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String searchForm() {
		return "/admin/test/search";
	}
	
	// 검색 결과 페이지로 이동
	@RequestMapping(value="/search", method = RequestMethod.POST)
	public String search(@Valid MemberSearch member, Model model) {
		String searchWord = member.getSearchWord();		// 검색어
		String keyword = member.getKeyword();			// 검색 키워드
		
		@SuppressWarnings("unchecked")
		List<Member> searchMember = (List<Member>) commonService.search(keyword, searchWord, new Member());	// 검색 실행
		
		model.addAttribute("searchMember", searchMember);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("keyword", keyword);
		return "/admin/test/result";
	}
	
}
