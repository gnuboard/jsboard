package kr.sir.model;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;


@Entity
@Data
public class Member{
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mb_no")
	private int id;
	
	@Column(name = "mb_id")
	private String memberId;
	
	@Column(name = "mb_password")
	private String memberPassword;
	
	@Column(name = "mb_name")
	private String memberName;
	
	@Column(name = "mb_nick")
	private String memberNickName;
	
	@Column(name = "mb_nick_date")
	@Temporal(TemporalType.DATE)
	private Date memberRickDate;
	
	@Column(name = "mb_email")
	private String memberEmail;
	
	@Column(name = "mb_homepage")
	private String memberHomePage;
	
	@Column(name = "mb_level")
	private int memberLevel;
	
	@Column(name = "mb_sex")
	private char memberSex;
	
	@Column(name = "mb_birth")
	private String memberBirth;

	@Column(name = "mb_tel")
	private String memberTel;

	@Column(name = "mb_hp")
	private String memberHpNo;
	
	@Column(name = "mb_certify")
	private String memberCertify;
	
	@Column(name = "mb_adult")
	private int memberAdult;
	
	@Column(name = "mb_dupinfo")
	private String memberDupInfo;
	
	@Column(name = "mb_zip1")
	private char memberZipCode1;
	
	@Column(name = "mb_zip2")
	private char memberZipCode2;
	
	@Column(name = "mb_addr1")
	private String memberAddress1;

	@Column(name = "mb_addr2")
	private String memberAddress2;
	
	@Column(name = "mb_addr3")
	private String memberAddress3;
	
	@Column(name = "mb_addr_jibeon")
	private String memberAddressJibeon;
	
	@Column(name = "mb_signature")
	private String memberSignature;
	
	@Column(name = "mb_recommend")
	private String memberRecommend;
	
	@Column(name = "mb_point")
	private int memberPoint; 
	
	@Column(name = "mb_today_login")
	@Temporal(TemporalType.TIMESTAMP)
	private Date memberTodayLogin;
	
	@Column(name = "mb_login_ip")
	private String memberLoginIp;
	
	@Column(name = "mb_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date memberDatetime;
	
	@Column(name = "mb_ip")
	private String memberIp;

	@Column(name = "mb_leave_date") 
	private String memberLeaveDate;
	
	@Column(name = "mb_intercept_date")
	private String memberInterceptDate;
	
	@Column(name = "mb_email_certify")
	@Temporal(TemporalType.TIMESTAMP)
	private Date memberEmailCertify;
	
	@Column(name = "mb_email_certify2")
	private String memberEmailCertify2;
	
	@Column(name = "mb_memo")
	private String memberMemo;
	
	@Column(name = "mb_lost_certify")
	private String memberLostCertify;
	
	@Column(name = "mb_mailing")
	private int memberMailing;
	
	@Column(name = "mb_sms")
	private int memberSms;
	
	@Column(name = "mb_Open")
	private int memberOpen;
	
	@Column(name = "mb_open_date")
	@Temporal(TemporalType.DATE)
	private Date memberOpenDate;
	
	@Column(name = "mb_profile")
	private String memberProfile;
	
	@Column(name = "mb_memo_call")
	private String memberMemoCall;
	
	@Column(name = "mb_1")
	private String memberExtra1;
	
	@Column(name = "mb_2")
	private String memberExtra2;
	
	@Column(name = "mb_3")
	private String memberExtra3;
	
	@Column(name = "mb_4")
	private String memberExtra4;
	
	@Column(name = "mb_5")
	private String memberExtra5;
	
	@Column(name = "mb_6")
	private String memberExtra6;
	
	@Column(name = "mb_7")
	private String memberExtra7;
	
	@Column(name = "mb_8")
	private String memberExtra8;
	
	@Column(name = "mb_9")
	private String memberExtra9;
	
	@Column(name = "mb_10")
	private String memberExtra10;
	
}