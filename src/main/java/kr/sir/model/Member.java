package kr.sir.model;

import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="member")
@NoArgsConstructor
@Getter
@Setter
public class Member {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mb_no", length = 11, nullable = false)
	private int id;
	
	@Column(name = "mb_id", length = 20, unique = true)
	private String memberId;
	
	@Column(name = "mb_password", length = 255)
	private String memberPassword;
	
	@Column(name = "mb_name", length = 255)
	private String memberName;
	
	@Column(name = "mb_rick", length = 255)
	private String memberRick;
	
	@Column(name = "mb_rick_date", length = 255)
	@Temporal(TemporalType.DATE)
	private Date memberRickDate;
	
	@Column(name = "mb_email", length = 255)
	private String memberEmail;
	
	@Column(name = "mb_homepage", length = 255)
	private String memberHomePage;
	
	@Column(name = "mb_level", columnDefinition = "TINYINT(4)")
	private int memberLevel;		// tinyint
	
	@Column(name = "mb_sex", length = 1)
	private char memberSex;
	
	@Column(name = "mb_birth", length = 255)
	private String memberBirth;

	@Column(name = "mb_tel", length = 255)
	private String memberTel;

	@Column(name = "mb_hp", length = 255)
	private String memberHpNo;
	
	@Column(name = "mb_certify", length = 20)
	private String memberCertify;
	
	@Column(name = "mb_adult", columnDefinition = "TINYINT(4)")
	private int memberAdult;		// tinyint
	
	@Column(name = "mb_dupinfo", length = 255)
	private String memberDupInfo;
	
	@Column(name = "mb_zip1", length = 3)
	private char memberZipCode1;
	
	@Column(name = "mb_zip2", length = 3)
	private char memberZipCode2;
	
	@Column(name = "mb_addr1", length = 255)
	private String memberAddress1;

	@Column(name = "mb_addr2", length = 255)
	private String memberAddress2;
	
	@Column(name = "mb_addr3", length = 255)
	private String memberAddress3;
	
	@Column(name = "mb_addr_jibeon", length = 255)
	private String memberAddressJibeon;
	
	@Column(name = "mb_signature", columnDefinition = "TEXT")
	private String memberSignature;		// text
	
	@Column(name = "mb_recommend", length = 255)
	private String memberRecommend;
	
	@Column(name = "mb_point", length = 11, nullable = false)
	private int memberPoint;
	
	@Column(name = "mb_today_login")
	@Temporal(TemporalType.TIMESTAMP)
	private Date memberTodayLogin;		// MUL
	
	@Column(name = "mb_login_ip", length = 255)
	private String memberLoginIp;
	
	@Column(name = "mb_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date memberDatetime;		// MUL
	
	@Column(name = "mb_ip", length = 255)
	private String memberIp;

	@Column(name = "mb_leave_date", length = 8)
	private String memberLeaveDate;
	
	@Column(name = "mb_intercept_date", length = 8)
	private String memberInterceptDate;
	
	@Column(name = "mb_email_certify")
	@Temporal(TemporalType.TIMESTAMP)
	private Date memberEmailCertify;
	
	@Column(name = "mb_email_certify2", length = 255)
	private String memberEmailCertify2;
	
	@Column(name = "mb_memo", columnDefinition = "TEXT")
	private String memberMemo;		// text
	
	@Column(name = "mb_lost_certify", length = 255)
	private String memberLostCertify;
	
	@Column(name = "mb_mailing", columnDefinition = "TINYINT(4)")
	private int memberMailing;		// tiny int
	
	@Column(name = "mb_sms", columnDefinition = "TINYINT(4)")
	private int memberSms;		// tiny int
	
	@Column(name = "mb_Open", columnDefinition = "TINYINT(4)")
	private int memberOpen;		// tiny int
	
	@Column(name = "mb_open_date")
	@Temporal(TemporalType.DATE)
	private Date memberOpenDate;
	
	@Column(name = "mb_profile", columnDefinition = "TEXT")
	private String memberProfile;		// text
	
	@Column(name = "mb_memo_call", length = 255)
	private String memberMemoCall;
	
	@Column(name = "mb_1", length = 255)
	private String memberExtra1;
	
	@Column(name = "mb_2", length = 255)
	private String memberExtra2;
	
	@Column(name = "mb_3", length = 255)
	private String memberExtra3;
	
	@Column(name = "mb_4", length = 255)
	private String memberExtra4;
	
	@Column(name = "mb_5", length = 255)
	private String memberExtra5;
	
	@Column(name = "mb_6", length = 255)
	private String memberExtra6;
	
	@Column(name = "mb_7", length = 255)
	private String memberExtra7;
	
	@Column(name = "mb_8", length = 255)
	private String memberExtra8;
	
	@Column(name = "mb_9", length = 255)
	private String memberExtra9;
	
	@Column(name = "mb_10", length = 255)
	private String memberExtra10;
	
}