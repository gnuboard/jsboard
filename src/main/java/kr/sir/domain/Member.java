package kr.sir.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;


@Entity
@Data
@Table(indexes = {
		@Index(name = "mb_today_login", columnList = "mb_today_login"),
		@Index(name = "mb_datetime", columnList = "mb_datetime")
},uniqueConstraints={@UniqueConstraint(name="mb_id",columnNames={"mb_id"})})
public class Member{
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mb_no",columnDefinition="int(11) NOT NULL")	
	private int id;
	
	@Column(name = "mb_id",columnDefinition="varchar(20) NOT NULL DEFAULT ''")
	private String memberId;
	
	@Column(name = "mb_password",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String password;
	
	@Column(name = "mb_name",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String name;
	
	@Column(name = "mb_nick",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String nick;
	
	@Column(name = "mb_nick_date",columnDefinition="date NOT NULL DEFAULT '0000-00-00'")
	@Temporal(TemporalType.DATE)
	private Date nickDate;
	
	@Column(name = "mb_email",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String email;
	
	@Column(name = "mb_homepage",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String homePage;
	
	@Column(name = "mb_level", columnDefinition="tinyint(4) NOT NULL default '0'")
	private int level;
	
	@Column(name = "mb_sex", columnDefinition="char(1) NOT NULL default ''")
	private String sex;
	
	@Column(name = "mb_birth",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String birth;

	@Column(name = "mb_tel",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String tel;

	@Column(name = "mb_hp",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String hpNo;
	
	@Column(name = "mb_certify",columnDefinition="varchar(20) NOT NULL DEFAULT ''")
	private String certify;
	
	@Column(name = "mb_adult", columnDefinition="tinyint(4) NOT NULL default '0'")
	private int adult;
	
	@Column(name = "mb_dupinfo",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String dupInfo;
	
	@Column(name = "mb_zip1", columnDefinition="char(3) NOT NULL default ''")
	private String zipCode1;
	
	@Column(name = "mb_zip2", columnDefinition="char(3) NOT NULL default ''")
	private String zipCode2;
	
	@Column(name = "mb_addr1",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String address1;

	@Column(name = "mb_addr2",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String address2;
	
	@Column(name = "mb_addr3",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String address3;
	
	@Column(name = "mb_addr_jibeon",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String addressJibeon;
	
	@Column(name = "mb_signature", columnDefinition="text NOT NULL")
	private String signature;
	
	@Column(name = "mb_recommend",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String recommend;
	
	@Column(name = "mb_point",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int point; 
	
	@Column(name = "mb_today_login",columnDefinition="datetime NOT NULL DEFAULT '0000-00-00 00:00:00'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date todayLogin;
	
	@Column(name = "mb_login_ip",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String loginIp;
	
	@Column(name = "mb_datetime",columnDefinition="datetime NOT NULL DEFAULT '0000-00-00 00:00:00'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;

	@Column(name = "mb_ip",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String ip;

	@Column(name = "mb_leave_date",columnDefinition="varchar(8) NOT NULL DEFAULT ''") 
	private String leaveDate;
	
	@Column(name = "mb_intercept_date",columnDefinition="varchar(8) NOT NULL DEFAULT ''")
	private String interceptDate;
	
	@Column(name = "mb_email_certify",columnDefinition="datetime NOT NULL DEFAULT '0000-00-00 00:00:00'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date emailCertify;
	
	@Column(name = "mb_email_certify2",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String emailCertify2;
	
	@Column(name = "mb_memo", columnDefinition="TEXT NOT NULL")
	private String memo;
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
	@Column(name = "mb_lost_certify",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String lostCertify;
	
	@Column(name = "mb_mailling", columnDefinition="tinyint(4) NOT NULL default '0'")
	private int mailling;
	
	@Column(name = "mb_sms", columnDefinition="tinyint(4) NOT NULL default '0'")
	private int sms;
	
	@Column(name = "mb_open", columnDefinition="tinyint(4) NOT NULL default '0'")
	private int open;
	
	@Column(name = "mb_open_date",columnDefinition="date NOT NULL DEFAULT '0000-00-00'")
	@Temporal(TemporalType.DATE)
	private Date openDate;
	
	@Column(name = "mb_profile", columnDefinition="TEXT NOT NULL")
	private String profile;
	
	@Column(name = "mb_memo_call",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String memoCall;
	
	@Column(name = "mb_1",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra1;
	
	@Column(name = "mb_2",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra2;
	
	@Column(name = "mb_3",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra3;
	
	@Column(name = "mb_4",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra4;
	
	@Column(name = "mb_5",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra5;
	
	@Column(name = "mb_6",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra6;
	
	@Column(name = "mb_7",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra7;
	
	@Column(name = "mb_8",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra8;
	
	@Column(name = "mb_9",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra9;
	
	@Column(name = "mb_10",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra10;

	
	@Transient
	private int countGroupMember;
	
}