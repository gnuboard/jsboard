package kr.sir.domain;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class GroupList extends GroupBaseEntity{

	private int countIncludeBoards;
	private int countAccessibleMembers;
}
