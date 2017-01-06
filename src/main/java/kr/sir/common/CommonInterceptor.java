package kr.sir.common;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 공통 인터셉터
public class CommonInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(isError(request, "/error"))	// 에러가 발생했을 때 
			return true;
		
		// demo 후 session 검사 후 정해둔 값이 없다면 table 조회로 바꾸자.(table 조회 결과를 session에 저장.)
		if(!existConfigTable()) {	// 설치 정보가 없으면
			if(isInstallPage(request.getServletPath()))	{	// 설치 step 페이지이면
				return true;
			}
			else {		// DB에 설치 정보가 없으면서, 설치 관련 URL 요청이 아닐 때
				response.sendRedirect("/install/step/1");
				return false;
			}
		}
		else {		// 설치 정보가 있으면
			if(isInstallPage(request.getServletPath()))	{	// 설치 step 페이지이면
				response.sendRedirect("/index"); 	// 메인 페이지로 보낸다.
				return false;
			}
			return true;
		}
		
	}

	private boolean existConfigTable() {
		String table_prefix = CommonUtil.getTablePrefix();
		if(table_prefix != "")
			return true;
		return false;
	}

	private boolean isError(HttpServletRequest request, String string) {
		return request.getServletPath().equals(string);
	}

	private boolean isInstallPage(String servletPath) {
		return servletPath.length() >= 8 && servletPath.substring(0, 8).equals("/install");
	}
	
}
