package kr.sir.domain;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class MemberSearch extends MemberBaseEntity {
	
	private String searchWord;
	private String keyword;
	private String orderBy;
}