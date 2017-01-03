package kr.sir.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Config {
	
	@Id
	@Column(name="cf_id", columnDefinition = "tinyint(1) NOT NULL DEFAULT '1'")
	private int id;
	
	@Column(name="cf_title",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String title;
	
	@Column(name="cf_theme",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String theme;
	
	@Column(name="cf_admin",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String admin;
	
	@Column(name="cf_admin_email",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String adminEmail;
	
	@Column(name="cf_admin_email_name",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String adminEmailName;
	
	@Column(name="cf_add_script",columnDefinition="TEXT NOT NULL")
	private String addScript;
	
	@Column(name="cf_use_point",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int usePoint;
	
	@Column(name="cf_point_term",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int pointTerm;
	
	@Column(name="cf_use_copy_log",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int useCopyLog;
	
	@Column(name="cf_use_email_certify",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int useEmailCertify;
	
	@Column(name="cf_login_point",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int loginPoint;
	
	@Column(name="cf_cut_name",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int cutName;
	
	@Column(name="cf_nick_modify",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int nickModify;
	
	@Column(name="cf_new_skin",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String newSkin;
	
	@Column(name="cf_new_rows",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int newRows;
	
	@Column(name="cf_search_skin",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String searchSkin;
	
	@Column(name="cf_connect_skin",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String connectSkin;
	
	@Column(name="cf_faq_skin",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String faqSkin;
	
	@Column(name="cf_read_point",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int readPoint;
	
	@Column(name="cf_write_point",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int writePoint;
	
	@Column(name="cf_comment_point",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int commentPoint;
	
	@Column(name="cf_download_point",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int downloadPoint;
	
	@Column(name="cf_write_pages",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int writePages;
	
	@Column(name="cf_mobile_pages",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int mobilePages;
	
	@Column(name="cf_link_target",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String linkTarget;
	
	@Column(name="cf_delay_sec",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int delaySec;
	
	@Column(name="cf_filter",columnDefinition="TEXT NOT NULL")
	private String filter;
	
	@Column(name="cf_possible_ip",columnDefinition="TEXT NOT NULL")
	private String possibleIp;
	
	@Column(name="cf_intercept_ip",columnDefinition="TEXT NOT NULL")
	private String interceptIp;
	
	@Column(name="cf_analytics",columnDefinition="TEXT NOT NULL")
	private String analytics;
	
	@Column(name="cf_add_meta",columnDefinition="TEXT NOT NULL")
	private String addMeta;
	
	@Column(name="cf_syndi_token",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String syndiToken;
	
	@Column(name="cf_syndi_except",columnDefinition="TEXT NOT NULL")
	private String syndiExcept;
	
	@Column(name="cf_member_skin",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String memberSkin;
	
	@Column(name="cf_use_homepage",columnDefinition="int(4) NOT NULL DEFAULT '0'")
	private int useHomepage;
	
	@Column(name="cf_req_homepage",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int reqHomepage;
	
	@Column(name="cf_use_tel",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int useTel;
	
	@Column(name="cf_req_tel",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int reqTel;
	
	@Column(name="cf_use_hp",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int useHp;
	
	@Column(name="cf_req_hp",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int reqHp;
	
	@Column(name="cf_use_addr",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int useAddr;
	
	@Column(name="cf_req_addr",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int reqAddr;
	
	@Column(name="cf_use_signature",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int useSignature;
	
	@Column(name="cf_req_signature",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int reqSignature;
	
	@Column(name="cf_use_profile",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int useProfile;
	
	@Column(name="cf_req_profile",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int reqProfile;
	
	@Column(name="cf_register_level",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int registerLevel;
	
	@Column(name="cf_register_point",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int registerPoint;
	
	@Column(name="cf_icon_level",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int iconlevel;
	
	@Column(name="cf_use_recommend",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int useRecommend;
	
	@Column(name="cf_recommend_point",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int recommendPoint;
	
	@Column(name="cf_leave_day",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int leaveDay;
	
	@Column(name="cf_search_part",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int searchPart;
	
	@Column(name="cf_email_use",columnDefinition="tinyint(11) NOT NULL DEFAULT '0'")
	private int emailUse;
	
	@Column(name="cf_email_wr_super_admin",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int emailWriteSuperAdmin;
	
	@Column(name="cf_emaile_wr_group_admin",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int emailWriteGroupAdmin;
	
	@Column(name="cf_email_wr_board_admin",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int emailWriteBoardAdmin;
	
	@Column(name="cf_email_wr_write",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int emailWriteWriter;
	
	@Column(name="cf_email_wr_comment_all",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int emailWriteCommentAll;
	
	@Column(name="cf_email_mb_super_admin",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int emailJoinSuperAdmin;
	
	@Column(name="cf_email_mb_member",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int emailMemberMember;
	
	@Column(name="cf_email_po_super_admin",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int emailPointSuperAdmin;
	
	@Column(name="cf_prohibit_id",columnDefinition="TEXT NOT NULL")
	private String prohibitId;
	
	@Column(name="cf_prohibit_email",columnDefinition="TEXT NOT NULL")
	private String prohibitEmail;
	
	@Column(name="cf_new_del",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int newDel;
	
	@Column(name="cf_memo_del",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int memoDel;
	
	@Column(name="cf_visit_del",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int visitDel;
	
	@Column(name="cf_popular_del",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int popularDel;
	
	@Column(name="cf_optimize_date",columnDefinition="date NOT NULL DEFAULT '0000-00-00'")
	@Temporal(TemporalType.DATE)
	private Date optimizeDate;
	
	@Column(name="cf_use_member_icon",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int useMemberIcon;
	
	@Column(name="cf_member_icon_size",columnDefinition="int(4) NOT NULL DEFAULT '0'")
	private int memberIconSize;
	
	@Column(name="cf_member_icon_width",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int memberIconWidth;
	
	@Column(name="cf_member_icon_height",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int memberIconHeight;
	
	@Column(name="cf_login_minutes",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int loginMinutes;
	
	@Column(name="cf_image_extension",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String imageExtension;
	
	@Column(name="cf_flash_extension",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String flashExtension;
	
	@Column(name="cf_movie_extension",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String movieExtension;
	
	@Column(name="cf_formmail_is_member",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int formmailIsMember;
	
	@Column(name="cf_page_rows",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int pageRows;
	
	@Column(name="cf_mobile_page_rows",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int mobilePageRows;

	@Column(name="cf_visit",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String visit;
	
	@Column(name="cf_max_po_id",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int maxPointId;
	
	@Column(name="cf_stipulation",columnDefinition="TEXT NOT NULL")
	private String stipulation;
	
	@Column(name="cf_privacy",columnDefinition="TEXT NOT NULL")
	private String privacy;
	
	@Column(name="cf_open_modify",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int openModify;
	
	@Column(name="cf_memo_send_point",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int memoSendPoint;
	
	@Column(name="cf_mobile_new_skin",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String mobileNewSkin;
	
	@Column(name="cf_mobile_search_skin",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String mobileSearchSkin;
	
	@Column(name="cf_mobile_connect_skin",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String mobileConnectSkin;
	
	@Column(name="cf_mobile_faq_skin",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String mobileFaqSkin;
	
	@Column(name="cf_mobile_member_skin",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String mobileMemberSkin;
	
	@Column(name="cf_captcha_mp3",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String captchaMp3;
	
	@Column(name="cf_editor",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String editor;
	
	@Column(name="cf_cert_use",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int certUse;
	
	@Column(name="cf_cert_ipin",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String certIpin; 
	
	@Column(name="cf_cert_hp",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String certHp;
	
	@Column(name="cf_cert_kcb_cd",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String certKcbCd;
	
	@Column(name="cf_lg_mid",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String lgMid;
	
	@Column(name="cf_lg_mert_key",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String lgMertKey; 
	
	@Column(name="cf_cert_Limit",columnDefinition="int(11) NOT NULL DEFAULT '0'")
	private int certLimit;
	
	@Column(name="cf_cert_req",columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	private int certReq;
	
	@Column(name="cf_sms_use",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String smsUse;
	
	@Column(name="cf_sms_type",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String smsType;
	
	@Column(name="cf_icode_id",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String icodeId;
	
	@Column(name="cf_icode_pw",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String icodePW;
	
	@Column(name="cf_icode_server_ip",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String icodeServerIp;
	
	@Column(name="cf_icode_server_port",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String icodeServerPort;
	
	@Column(name="cf_googl_Shorturl_apikey",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String googlShorturlApikey;
	
	@Column(name="cf_facebook_appid",columnDefinition="varchar(255) NOT NULL")
	private String facebookAppid;
	
	@Column(name="cf_facebook_secret",columnDefinition="varchar(255) NOT NULL")
	private String facebookSecret;
	
	@Column(name="cf_twitter_key",columnDefinition="varchar(255) NOT NULL")
	private String twitterKey;
	
	@Column(name="cf_twitter_secret",columnDefinition="varchar(255) NOT NULL")
	private String twitterSecret;
	
	@Column(name="cf_kakao_js_apikey",columnDefinition="varchar(255) NOT NULL")
	private String kakaoJsApikey;
	
	@Column(name="cf_1_sub",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra1Key;
	
	@Column(name="cf_2_sub",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra2Key;
	
	@Column(name="cf_3_sub",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra3Key;
	
	@Column(name="cf_4_sub",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra4Key;
	
	@Column(name="cf_5_sub",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra5Key;
	
	@Column(name="cf_6_sub",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra6Key;
	
	@Column(name="cf_7_sub",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra7Key;
	
	@Column(name="cf_8_sub",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra8Key;
	
	@Column(name="cf_9_sub",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra9Key;
	
	@Column(name="cf_10_sub",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra10Key;
	
	@Column(name="cf_1",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra1Value;
	
	@Column(name="cf_2",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra2Value;
	
	@Column(name="cf_3",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra3Value;
	
	@Column(name="cf_4",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra4Value;
	
	@Column(name="cf_5",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra5Value;
	
	@Column(name="cf_6",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra6Value;
	
	@Column(name="cf_7",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra7Value;
	
	@Column(name="cf_8",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra8Value;
	
	@Column(name="cf_9",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra9Value;
	
	@Column(name="cf_10",columnDefinition="varchar(255) NOT NULL DEFAULT ''")
	private String extra10Value;
	
	
}





















