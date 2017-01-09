package kr.sir.domain;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class PointJoinMember extends PointBaseEntity{

	
	private String name;
	private String nick;
	private String email;
	private String homepage;

}
