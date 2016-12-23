package kr.sir.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class BoardNew {
	
	@Id @Column(name="bn_id")
	private int boardNewId;
	
	@Column(name="bo_table")
	private String boardTable;
	
	@Column(name="wr_id")
	private int writeId;
	
	@Column(name="wr_parent")
	private int writeParent;
	
	@Column(name="bn_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date boardNewDatetime;

	@Column(name="mb_id")
	private String memberId;
}
