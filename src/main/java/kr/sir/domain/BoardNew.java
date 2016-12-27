package kr.sir.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class BoardNew {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO) 
	@Column(name="bn_id")
	private int id;
	
	@Column(name="bo_table")
	private String table;
	
	@Column(name="wr_id")
	private int writeId;
	
	@Column(name="wr_parent")
	private int parent;
	
	@Column(name="bn_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;

	@Column(name="mb_id")
	private String memberId;
}
