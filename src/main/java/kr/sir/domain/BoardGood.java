package kr.sir.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(
		name = "fkey1", columnNames = {"bo_table", "wr_id", "mb_id"})
})
public class BoardGood {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="bg_id", columnDefinition = "int(11) NOT NULL")
	private int id;
	
	@Column(name="bo_table", columnDefinition = "varchar(20) NOT NULL default ''")
	private String boardTable;
	
	@Column(name="wr_id", columnDefinition = "int(11) NOT NULL default '0'")
	private int writeId;
	
	@Column(name="mb_id", columnDefinition = "varchar(20) NOT NULL default ''")
	private String memberId;
	
	@Column(name="bg_flag", columnDefinition = "varchar(255) NOT NULL default ''")
	private String flag;
	
	@Column(name="bg_datetime", columnDefinition = "datetime NOT NULL default '0000-00-00 00:00:00'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;

}
