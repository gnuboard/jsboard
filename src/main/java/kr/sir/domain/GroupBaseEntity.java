package kr.sir.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;


@Data
@MappedSuperclass
public abstract class GroupBaseEntity {

	@Id 
	@Column(name="gr_id", columnDefinition = "varchar(10) NOT NULL default ''")
	private String id;
	
	@Column(name="gr_subject", columnDefinition = "varchar(255) NOT NULL default ''")
	private String subject="";
	
	@Column(name="gr_device", columnDefinition = "ENUM('both','pc','mobile') NOT NULL DEFAULT 'both'")
	private String device="both";
	
	@Column(name="gr_admin", columnDefinition = "varchar(255) NOT NULL default ''")
	private String admin="";
	
	@Column(name="gr_use_access", columnDefinition = "tinyint(4) NOT NULL default '0'")
	private int useAccess=0;
	
	@Column(name="gr_order", columnDefinition = "int(11) NOT NULL default '0'")
	private int order=0;
	
	@Column(name="gr_1_subj", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra1Key="";
	
	@Column(name="gr_2_subj", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra2Key="";
	
	@Column(name="gr_3_subj", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra3Key="";
	
	@Column(name="gr_4_subj", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra4Key="";
	
	@Column(name="gr_5_subj", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra5Key="";
	
	@Column(name="gr_6_subj", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra6Key="";
	
	@Column(name="gr_7_subj", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra7Key="";
	
	@Column(name="gr_8_subj", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra8Key="";
	
	@Column(name="gr_9_subj", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra9Key="";
	
	@Column(name="gr_10_subj", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra10Key="";
	
	@Column(name="gr_1", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra1Value="";
	
	@Column(name="gr_2", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra2Value="";
	
	@Column(name="gr_3", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra3Value="";
	
	@Column(name="gr_4", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra4Value="";
	
	@Column(name="gr_5", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra5Value="";
	
	@Column(name="gr_6", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra6Value="";
	
	@Column(name="gr_7", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra7Value="";
	
	@Column(name="gr_8", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra8Value="";
	
	@Column(name="gr_9", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra9Value="";
	
	@Column(name="gr_10", columnDefinition = "varchar(255) NOT NULL default ''")
	private String extra10Value="";
}
