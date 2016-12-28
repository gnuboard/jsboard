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
	private String extra1Key;
	
	@Column(name="gr_2_subj")
	private String extra2Key;
	
	@Column(name="gr_3_subj")
	private String extra3Key;
	
	@Column(name="gr_4_subj")
	private String extra4Key;
	
	@Column(name="gr_5_subj")
	private String extra5Key;
	
	@Column(name="gr_6_subj")
	private String extra6Key;
	
	@Column(name="gr_7_subj")
	private String extra7Key;
	
	@Column(name="gr_8_subj")
	private String extra8Key;
	
	@Column(name="gr_9_subj")
	private String extra9Key;
	
	@Column(name="gr_10_subj")
	private String extra10Key;
	
	@Column(name="gr_1")
	private String extra1Value;
	
	@Column(name="gr_2")
	private String extra2Value;
	
	@Column(name="gr_3")
	private String extra3Value;
	
	@Column(name="gr_4")
	private String extra4Value;
	
	@Column(name="gr_5")
	private String extra5Value;
	
	@Column(name="gr_6")
	private String extra6Value;
	
	@Column(name="gr_7")
	private String extra7Value;
	
	@Column(name="gr_8")
	private String extra8Value;
	
	@Column(name="gr_9")
	private String extra9Value;
	
	@Column(name="gr_10")
	private String extra10Value;
}
