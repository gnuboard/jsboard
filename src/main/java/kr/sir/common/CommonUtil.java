package kr.sir.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import kr.sir.domain.Write;

public class CommonUtil {

	public static String getTablePrefix(){
		YamlMapFactoryBean yaml = new YamlMapFactoryBean();
		yaml.setResources(new ClassPathResource("config.yml"));
		
		Map<String, Object> map = yaml.getObject();

		Object prefixObj = map.get("prefix");
		if(prefixObj != null)
			return prefixObj.toString();
		
		return "";
	}
	
	public static String getToday(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		return formatter.format(date);
	}
	
	public static String getIpAddress() throws UnknownHostException {
		InetAddress local = InetAddress.getLocalHost();
		return local.getHostAddress();
	}
	
	// Paging 처리를 위한 정보 가공.
	public static Model pagingInfo(Page<Write> result, Model model) {
		
		int currentPage = result.getNumber();					// 현재 페이지
		int totalPages = result.getTotalPages();				// 전체 페이지 수
		long totalCount = result.getTotalElements();			// 전체 게시물 수
		List<Write> writeList = result.getContent();			// 조회된 데이터
		boolean hasContent = result.hasContent();				// 조회된 데이터 존재 여부
		Sort sortInfo = result.getSort();						// 정렬 정보
		
		int PageGroupPerSize = 10;								// 한 페이지그룹 당 페이지 수
		int currentPageGroup = currentPage / PageGroupPerSize;	// 현재 페이지가 속한 페이지 그룹
		int currentPageGroupFirstPage							// 현재 페이지가 속한 페이지 그룹의 첫 페이지
							= currentPageGroup * PageGroupPerSize + 1;
		int currentPageGroupLastPage = 							// 현재 페이지가 속한 페이지 그룹의 마지막 페이지 
								currentPage >= totalPages - (totalPages-PageGroupPerSize)	// 현재 페이지가 속한 페이지 그룹이 마지막 그룹인지 여부 
								? totalPages
									: currentPageGroupFirstPage + PageGroupPerSize - 1;
		int nextPageGroupFirstPage = currentPageGroupFirstPage + PageGroupPerSize;	 // 다음 페이지 그룹의 첫번째 페이지          
		int prevPageGroupLastPage = 							// 이전 페이지 그룹의 마지막 페이지
								currentPageGroupLastPage == totalPages
								? totalPages - (totalPages-PageGroupPerSize)
									: currentPageGroupLastPage - PageGroupPerSize;          
		
		model.addAttribute("currentPage", currentPage + 1);		// 0부터 시작.
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("writeList", writeList);
		model.addAttribute("hasContent", hasContent);
		model.addAttribute("sortInfo", sortInfo); 
		model.addAttribute("PageGroupPerSize", PageGroupPerSize);
		model.addAttribute("currentPageGroup", currentPageGroup);
		model.addAttribute("currentPageGroupFirstPage", currentPageGroupFirstPage);
		model.addAttribute("currentPageGroupLastPage", currentPageGroupLastPage);
		model.addAttribute("nextPageGroupFirstPage", nextPageGroupFirstPage);
		model.addAttribute("prevPageGroupLastPage", prevPageGroupLastPage);
		
		return model;
	}
	
}
