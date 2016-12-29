package kr.sir.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@Table(indexes = {
		@Index(name = "mb_id", columnList = "mb_id")
})
public class BoardNew {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO) 
	@Column(name="bn_id", columnDefinition = "int(11) NOT NULL")
	private int id;
	
	@Column(name="bo_table", columnDefinition = "varchar(20) NOT NULL default ''")
	private String table;
	
	@Column(name="wr_id", columnDefinition = "int(11) NOT NULL default '0'")
	private int writeId;
	
	@Column(name="wr_parent", columnDefinition = "int(11) NOT NULL default '0'")
	private int parent;
	
	@Column(name="bn_datetime", columnDefinition = "datetime NOT NULL default '0000-00-00 00:00:00'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;

	@Column(name="mb_id", columnDefinition = "varchar(20) NOT NULL default ''")
	private String memberId;
}
