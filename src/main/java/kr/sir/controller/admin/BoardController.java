package kr.sir.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.sir.domain.BoardGroup;
import kr.sir.service.admin.BoardService;

@Controller
@RequestMapping("/adm/board")
public class BoardController {
	
	
	private BoardService boardService;
	
	@Autowired
	public void setBoardService(BoardService boardService){
		this.boardService=boardService;
	}

	@RequestMapping(value={"/list"})
	public String boardList(Model model){
		model.addAttribute("", "보드리스트");
		return "admin/board/list";
	}
	
	//보드 그룹 목록 출력
	@RequestMapping(value={"/boardgroupslist"})
	public String boardgroupList(Model model){
	
		model.addAttribute("countBoardGroupsList",boardService.getCountBoardGroups());
		model.addAttribute("allBoardGroupsList", boardService.getAllBoardGroupsList());
		return "admin/board/group_list";
	}
	
	
	//그룹추가폼	
	@RequestMapping(value={"/form/addgroup"})
	public String ShowaddGroupForm(Model model){
		
		model.addAttribute("type","add");
		return "admin/board/groupform";
		
	}	
	
	//그룹수정폼
	@RequestMapping(value={"/form/updategroup/{groupId}"})
	public String showUpdateGroupForm(Model model,@PathVariable("groupId") String groupId){	
		model.addAttribute("type","update");		
		BoardGroup boardGroup=boardService.getOneBoardGroup(groupId);
		model.addAttribute("boardGroup",boardGroup);	
		model.addAttribute("countAccessibleMembers",boardService.getCountAccessibleMembers(boardGroup.getId()));
		
		return "admin/board/groupform";
	}
	
	//그룹추가
	@RequestMapping(value={"/addgroup"},method=RequestMethod.POST)
	public String addGroup(Model model,BoardGroup group,HttpServletRequest request ){
		boardService.addBoardGroup(group);
		System.out.println(request.getMethod());
		System.out.println("그룹추가메서드");
		return "redirect:./boardgroupslist";
	}
	
	
	//그룹수정
	@RequestMapping(value={"/updategroup"},method=RequestMethod.PUT)
	public String updateGroup(Model model,BoardGroup group){
		boardService.addBoardGroup(group);
		return "redirect:./list";
	}
	
	
	//그룹리스트에서 그룹삭제
	@RequestMapping(value={"/delete/group"},method=RequestMethod.DELETE)
	public String deleteGroup(Model model, @RequestParam("chk[]") String chk){			
					
		
		boardService.deleteGroups(chk);
			
		
		return "redirect:../list";
	}
	
	
	
	
	
	
	
	
	
}



















