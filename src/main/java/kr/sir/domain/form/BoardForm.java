package kr.sir.domain.form;

import lombok.Data;

@Data
public class BoardForm{
	
	private String selectedId;
	private int totalCount;
	private int pagePerPosts;
	private int currentPage;
	private String currentCategory;
	private int isReply;
	private int commentDepth;
	private int baseCommentId;
	private String boardName;
	
	public int redirectPageNumber(int deletedArticleCount, int pageNumber) {
		totalCount = totalCount - deletedArticleCount;
		if(totalCount % pagePerPosts > 0) {
			return pageNumber;
		} else {
			return totalCount / pagePerPosts;
		}
	}
	
}
