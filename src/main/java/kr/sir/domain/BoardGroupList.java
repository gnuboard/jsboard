package kr.sir.domain;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class BoardGroupList extends BoardGroupBaseEntity{

	private int countIncludedBoards;
	private int countAccessibleMembers;
}
