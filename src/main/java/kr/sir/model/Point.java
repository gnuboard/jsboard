package kr.sir.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="point")
@NoArgsConstructor
@Getter
@Setter
public class Point {

	@Id
	@Column(name="po_id")
	private int pointId;
	
	@Column(name="mb_id")
	private String memberId;
	
	@Column(name="po_datetime")
	private Date pointDatetime;
	
	@Column(name="po_content")
	private String pointContent;
	
	@Column(name="po_point")
	private int pointPoint;
	
	@Column(name="po_use_point")
	private int pointUsePoint;
	
	@Column(name="po_expired")
	private int pointExpired;
	
	@Column(name="po_expire_date")
	private Date pointExpireDate;
	
	@Column(name="po_mb_point")
	private int pointMbPoint;
	
	@Column(name="po_rel_table")
	private String pointRelTable;
	
	@Column(name="po_rel_id")
	private String pointRelId;
	
	@Column(name="po_rel_action")
	private String pointRelAction;
	
	
	
}
