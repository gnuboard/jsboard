package kr.sir.domain;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class MemberGroupCount extends MemberBaseEntity {

	private String countGroupMember;


	
}
