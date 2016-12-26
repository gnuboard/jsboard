package kr.sir.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class BoardGood {
	
	@Column(name="bg_id")
	private int id;
	
	@Column(name="bo_table")
	private String table;
	
	@Column(name="wr_id")
	private int writeId;
	
	@Column(name="mb_id")
	private String memberId;
	
	@Column(name="bg_flag")
	private String flag;
	
	@Column(name="bg_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;

}
