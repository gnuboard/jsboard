package kr.sir.service.admin;

import java.util.List;

public interface CommonService {
	
	// 검색 기능
	public List<?> search(String keyword, String searchWord, Object entity, String orderBy);

}
