package kr.sir.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.sir.config.DataConfig;

// 공통 인터셉터
public class CommonInterceptor extends HandlerInterceptorAdapter {
	
	private DataConfig dataConfig;
	
	@Autowired
	public void setDataConfig(DataConfig dataConfig) {
		this.dataConfig = dataConfig;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(isError(request, "/error"))	// 에러가 발생했을 때 
			return true;
		
		if(configIsNull()) {	// 설치 정보가 없으면
			System.out.println("config is null!");
			if(isInstallPage(request.getServletPath()))	{	// 설치 step 페이지이면
				System.out.println("install step!");
				return true;
			}
			else {		// DB에 설치 정보가 없으면서, 설치 관련 URL 요청이 아닐 때
				response.sendRedirect("/install/step/1");
				return false;
			}
		}
		else {		// 설치 정보가 있으면
			System.out.println("config is 'not' null!");
			if(isInstallPage(request.getServletPath()))	{	// 설치 step 페이지이면
				response.sendRedirect("/index"); 	// 메인 페이지로 보낸다.
				return false;
			}
			System.out.println("pass!");
			return true;
		}
		
	}

	private boolean isError(HttpServletRequest request, String string) {
		return request.getServletPath().equals(string);
	}

	private boolean configIsNull() {
		return dataConfig.getConfig() == null;
	}
	
	private boolean isInstallPage(String servletPath) {
		return servletPath.length() >= 8 && servletPath.substring(0, 8).equals("/install");
	}
}
