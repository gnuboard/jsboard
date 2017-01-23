package kr.sir.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.sir.common.exception.FindException;
import kr.sir.domain.repository.board.BbsEmRepository;

@Controller
@RequestMapping("/test/exception")
public class TestExcpetionController {
	
	private BbsEmRepository bbsEmRepository;
	
	@Autowired
	public void setBbsEmRepository(BbsEmRepository bbsEmRepository) {
		this.bbsEmRepository = bbsEmRepository;
	}

	@RequestMapping("/find/minNum")
	public String testFindMinNum(Model model) throws FindException {
		int minNum = bbsEmRepository.findMinNum();
//		int minNum = 0;
		if(minNum == 0) {
			throw new FindException("wr_num을 가져오는데 실패했습니다.");
		} else {
			model.addAttribute("minNum", minNum);
		}
		return "testException";
	}
}
