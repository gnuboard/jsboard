package kr.sir.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.sir.common.exception.ConfigException;
import kr.sir.common.exception.DeleteException;
import kr.sir.common.exception.FindException;

@RequestMapping("/error")
@Controller
public class ExceptionHandlingController {
	
	// DB에서 Data를 가져올 때 발생하는 예외 처리 
	@ExceptionHandler(FindException.class)
	public ModelAndView sqlError(HttpServletRequest request, FindException exception) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", request.getRequestURL());
		mav.setViewName("error/error");
		return mav;
	}
	
	// 설정값과 비교할 때 발생하는 예외 처리
	@ExceptionHandler(ConfigException.class)
	public ModelAndView configError(HttpServletRequest request, ConfigException exception) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", request.getRequestURL());
		mav.setViewName("error/error");
		return mav;
	}
	
	// 삭제할때 발생하는 예외 처리
	@ExceptionHandler(DeleteException.class)
	public ModelAndView configError(HttpServletRequest request, DeleteException exception) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", request.getRequestURL());
		mav.setViewName("error/error");
		return mav;
	}
	
	// 404 에러 처리
	@RequestMapping("/404")
	public String error404(Model model) {
		return "error/404";
	}
	
	// 500 에러 처리
	@RequestMapping("/500")
	public String error500(Model model) {
		return "error/500";
	}
}
