package kr.sir.controller.board;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.sir.domain.Write;
import kr.sir.service.board.JsBoardService;

@Controller
@RequestMapping(value="/board")
public class JsboardController {
	
	private JsBoardService jsBoardService;
	
	@Autowired
	public void setBoardService(JsBoardService jsBoardService) {
		this.jsBoardService = jsBoardService;
	}

	@RequestMapping(value="/list/{pageNumber}")
	public String index(Model model, @PathVariable int pageNumber) {
		
		int paramCurrentPage = pageNumber - 1;					// 현재 몇 페이지 인지
		int paramPagePerPosts = 10;								// 한 페이지 당 게시물 수 
		int paramBoardId = 1;									// 어떤 게시판인지
		
		PageRequest pageRequest = new PageRequest(paramCurrentPage, paramPagePerPosts, new Sort(Direction.ASC, "id"));
		
		Page<Write> result = jsBoardService.findByBoardId(paramBoardId, pageRequest);	// Paging 한 게시판 가져오기
		
		int currentPage = result.getNumber();					// 현재 페이지
		int pageSize = result.getSize();						// 페이지 당 데이터 수 (numberOfElements와 차이가?)
		int totalPages = result.getTotalPages();				// 전체 페이지 수
		int numberOfElements = result.getNumberOfElements();	// 현재 페이지에 나올 데이터 수
		long totalCount = result.getTotalElements();			// 전체 게시물 수
		boolean hasPrev = result.hasPrevious();					// 이전 페이지 여부
		boolean isFirst = result.isFirst();						// 현재 페이지가 첫 페이지 인지 여부
		boolean hasNext = result.hasNext();						// 다음 페이지 여부
		boolean isLast = result.isLast();						// 현재 페이지가 마지막 페이지 인지 여부
		Pageable nextPageable = result.nextPageable();			// 다음 페이지 객체, 다음 페이지가 없으면 null
		Pageable prevPageable = result.previousPageable();		// 이전 페이지 객체, 이전 페이지가 없으면 null
		List<Write> writeList = result.getContent();			// 조회된 데이터
		boolean hasContent = result.hasContent();				// 조회된 데이터 존재 여부
		Sort sortInfo = result.getSort();						// 정렬 정보
		
		int onePageGroupSize = 10;								// 한 페이지그룹 당 페이지 수 
		
		model.addAttribute("currentPage", currentPage + 1);		// 0부터 시작.
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("numberOfElements", numberOfElements);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("hasPrev", hasPrev);
		model.addAttribute("isFirst", isFirst);
		model.addAttribute("hasNext", hasNext);
		model.addAttribute("isLast", isLast);
		model.addAttribute("nextPageable", nextPageable);
		model.addAttribute("prevPageable", prevPageable);
		model.addAttribute("writeList", writeList);
		model.addAttribute("hasContent", hasContent);
		model.addAttribute("sortInfo", sortInfo); 
		model.addAttribute("onePageGroupSize", onePageGroupSize); 
		
		
		System.out.println("★currentPage : " + currentPage
				+ " ★pageSize : " + pageSize
				+ " ★totalPages : " + totalPages
				+ " ★numberOfElements : " + numberOfElements
				+ " ★totalCount : " + totalCount
				+ " ★hasPrev : " + hasPrev
				+ " ★isFirst : " + isFirst
				+ " ★hasNext : " + hasNext
				+ " ★isLast : " + isLast
				+ " ★nextPageable : " + nextPageable
				+ " ★prevPageable : " + prevPageable
//				+ " writeList : " + writeList.toString()
				+ " ★hasContent : " + hasContent
				+ " ★sortInfo : " + sortInfo.toString()
				);
		
		return "/board/list";
		
	}
}
