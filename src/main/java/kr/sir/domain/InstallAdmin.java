package kr.sir.domain;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class InstallAdmin extends MemberBaseEntity{	

	private String table_prefix;
}
