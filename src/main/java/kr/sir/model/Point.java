package kr.sir.model;

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
public class Point {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="po_id")
	private int Id;
	
	@Column(name="mb_id")
	private String memberId;
	
	@Column(name="po_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;
	
	@Column(name="po_content")
	private String content;
	
	@Column(name="po_point")
	private int point;
	
	@Column(name="po_use_point")
	private int usePoint;
	
	@Column(name="po_expired")
	private int expired;
	
	@Column(name="po_expire_date")
	@Temporal(TemporalType.DATE)
	private Date expireDate;
	
	@Column(name="po_mb_point")
	private int mbPoint;
	
	@Column(name="po_rel_table")
	private String relTable;
	
	@Column(name="po_rel_id")
	private String relId;
	
	@Column(name="po_rel_action")
	private String relAction;
	
	
	
}
