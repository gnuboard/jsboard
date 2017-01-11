package kr.sir.domain.module;

import lombok.Data;

@Data
public class BoardForm{
	
	private String id;
	private int totalCount;
	private int pagePerPosts;
	private int currentPage;
	private String currentCategory;
	
	public int redirectPageNumber(int deletedArticleCount, int pageNumber) {
		totalCount = totalCount - deletedArticleCount;
		if(totalCount % pagePerPosts > 0) {
			return pageNumber;
		} else {
			return totalCount / pagePerPosts;
		}
	}
	
}
