package kr.sir.domain.module;

import lombok.Data;

@Data
public class BoardForm{
	
	private String id;
	private int totalCount;
	private int PagePerPosts;
}
