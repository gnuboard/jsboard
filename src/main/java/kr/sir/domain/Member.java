package kr.sir.domain;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;


@Entity
@Data
@Table(name="member")
public class Member{
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mb_no")
	private int id;
	
	@Column(name = "mb_id", length = 20)
	private String memberId;
	
	@Column(name = "mb_password")
	private String password;
	
	@Column(name = "mb_name")
	private String name;
	
	@Column(name = "mb_nick")
	private String nick;
	
	@Column(name = "mb_nick_date")
	@Temporal(TemporalType.DATE)
	private Date nickDate;
	
	@Column(name = "mb_email")
	private String email;
	
	@Column(name = "mb_homepage")
	private String homePage;
	
	@Column(name = "mb_level", columnDefinition="tinyint(4) NOT NULL default '0'")
	private int level;
	
	@Column(name = "mb_sex", columnDefinition="char(1) NOT NULL default ''")
	private String sex;
	
	@Column(name = "mb_birth")
	private String birth;

	@Column(name = "mb_tel")
	private String tel;

	@Column(name = "mb_hp")
	private String hpNo;
	
	@Column(name = "mb_certify", length = 20)
	private String certify;
	
	@Column(name = "mb_adult", columnDefinition="tinyint(4) NOT NULL default '0'")
	private int adult;
	
	@Column(name = "mb_dupinfo")
	private String dupInfo;
	
	@Column(name = "mb_zip1", columnDefinition="char(3) NOT NULL default ''")
	private String zipCode1;
	
	@Column(name = "mb_zip2", columnDefinition="char(3) NOT NULL default ''")
	private String zipCode2;
	
	@Column(name = "mb_addr1")
	private String address1;

	@Column(name = "mb_addr2")
	private String address2;
	
	@Column(name = "mb_addr3")
	private String address3;
	
	@Column(name = "mb_addr_jibeon")
	private String addressJibeon;
	
	@Column(name = "mb_signature", columnDefinition="text NOT NULL")
	private String signature;
	
	@Column(name = "mb_recommend")
	private String recommend;
	
	@Column(name = "mb_point")
	private int point; 
	
	@Column(name = "mb_today_login")
	@Temporal(TemporalType.TIMESTAMP)
	private Date todayLogin;
	
	@Column(name = "mb_login_ip")
	private String loginIp;
	
	@Column(name = "mb_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;

	@Column(name = "mb_ip")
	private String ip;

	@Column(name = "mb_leave_date", length = 8) 
	private String leaveDate;
	
	@Column(name = "mb_intercept_date", length = 8)
	private String interceptDate;
	
	@Column(name = "mb_email_certify")
	@Temporal(TemporalType.TIMESTAMP)
	private Date emailCertify;
	
	@Column(name = "mb_email_certify2")
	private String emailCertify2;
	
	@Column(name = "mb_memo", columnDefinition="text NOT NULL")
	private String memo;
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
	@Column(name = "mb_lost_certify")
	private String lostCertify;
	
	@Column(name = "mb_mailing", columnDefinition="tinyint(4) NOT NULL default '0'")
	private int mailing;
	
	@Column(name = "mb_sms", columnDefinition="tinyint(4) NOT NULL default '0'")
	private int sms;
	
	@Column(name = "mb_open", columnDefinition="tinyint(4) NOT NULL default '0'")
	private int open;
	
	@Column(name = "mb_open_date")
	@Temporal(TemporalType.DATE)
	private Date openDate;
	
	@Column(name = "mb_profile", columnDefinition="text NOT NULL")
	private String profile;
	
	@Column(name = "mb_memo_call")
	private String memoCall;
	
	@Column(name = "mb_1")
	private String extra1;
	
	@Column(name = "mb_2")
	private String extra2;
	
	@Column(name = "mb_3")
	private String extra3;
	
	@Column(name = "mb_4")
	private String extra4;
	
	@Column(name = "mb_5")
	private String extra5;
	
	@Column(name = "mb_6")
	private String extra6;
	
	@Column(name = "mb_7")
	private String extra7;
	
	@Column(name = "mb_8")
	private String extra8;
	
	@Column(name = "mb_9")
	private String extra9;
	
	@Column(name = "mb_10")
	private String extra10;
	
}