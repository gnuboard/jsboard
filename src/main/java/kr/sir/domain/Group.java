package kr.sir.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Group {

	@Id 
	@Column(name="gr_id")
	private String id;
	
	@Column(name="gr_subject")
	private String subject;
	
	@Column(name="gr_device")
	private String device;
	
	@Column(name="gr_admin")
	private String admin;
	
	@Column(name="gr_use_access")
	private int useAccess;
	
	@Column(name="gr_order")
	private int order;
	
	@Column(name="gr_1_subj")
	private String _1Subj;
	
	@Column(name="gr_2_subj")
	private String _2Subj;
	
	@Column(name="gr_3_subj")
	private String _3Subj;
	
	@Column(name="gr_4_subj")
	private String _4Subj;
	
	@Column(name="gr_5_subj")
	private String _5Subj;
	
	@Column(name="gr_6_subj")
	private String _6Subj;
	
	@Column(name="gr_7_subj")
	private String _7Subj;
	
	@Column(name="gr_8_subj")
	private String _8Subj;
	
	@Column(name="gr_9_subj")
	private String _9Subj;
	
	@Column(name="gr_10_subj")
	private String _10Subj;
	
	@Column(name="gr_1")
	private String _1;
	
	@Column(name="gr_2")
	private String _2;
	
	@Column(name="gr_3")
	private String _3;
	
	@Column(name="gr_4")
	private String _4;
	
	@Column(name="gr_5")
	private String _5;
	
	@Column(name="gr_6")
	private String _6;
	
	@Column(name="gr_7")
	private String _7;
	
	@Column(name="gr_8")
	private String _8;
	
	@Column(name="gr_9")
	private String _9;
	
	@Column(name="gr_10")
	private String _10;
}
