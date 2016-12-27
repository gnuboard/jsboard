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
	@Column(name="cf_id")
	private int id;
	
	@Column(name="cf_title")
	private String title;
	
	@Column(name="cf_theme")
	private String theme;
	
	@Column(name="cf_admin")
	private String admin;
	
	@Column(name="cf_admin_email")
	private String adminEmail;
	
	@Column(name="cf_admin_email_name")
	private String adminEmailName;
	
	@Column(name="cf_add_script")
	private String addScript;
	
	@Column(name="cf_use_point")
	private int usePoint;
	
	@Column(name="cf_point_term")
	private int pointTerm;
	
	@Column(name="cf_use_copy_log")
	private int useCopyLog;
	
	@Column(name="cf_use_email_certify")
	private int useEmailCertify;
	
	@Column(name="cf_login_point")
	private int loginPoint;
	
	@Column(name="cf_cut_name")
	private int cutName;
	
	@Column(name="cf_nick_modify")
	private int nickModify;
	
	@Column(name="cf_new_skin")
	private String newSkin;
	
	@Column(name="cf_new_rows")
	private int newRows;
	
	@Column(name="cf_search_skin")
	private String searchSkin;
	
	@Column(name="cf_connect_skin")
	private String connectSkin;
	
	@Column(name="cf_faq_skin")
	private String faqSkin;
	
	@Column(name="cf_read_point")
	private int readPoint;
	
	@Column(name="cf_write_point")
	private int writePoint;
	
	@Column(name="cf_comment_point")
	private int commentPoint;
	
	@Column(name="cf_download_point")
	private int downloadPoint;
	
	@Column(name="cf_write_pages")
	private int writePages;
	
	@Column(name="cf_mobile_pages")
	private int mobilePages;
	
	@Column(name="cf_link_target")
	private String linkTarget;
	
	@Column(name="cf_delay_sec")
	private int delaySec;
	
	@Column(name="cf_filter")
	private String filter;
	
	@Column(name="cf_possible_ip")
	private String possibleIp;
	
	@Column(name="cf_intercept_ip")
	private String interceptIp;
	
	@Column(name="cf_analyrics")
	private String analyrics;
	
	@Column(name="cf_add_meta")
	private String addMeta;
	
	@Column(name="cf_syndi_token")
	private String syndiToken;
	
	@Column(name="cf_syndi_except")
	private String syndiExcept;
	
	@Column(name="cf_member_skin")
	private String memberSkin;
	
	@Column(name="cf_use_homepage")
	private int useHomepage;
	
	@Column(name="cf_req_homepage")
	private int reqHomepage;
	
	@Column(name="cf_use_tel")
	private int useTel;
	
	@Column(name="cf_req_tel")
	private int reqTel;
	
	@Column(name="cf_use_hp")
	private int useHp;
	
	@Column(name="cf_req_hp")
	private int reqHp;
	
	@Column(name="cf_use_addr")
	private int useAddr;
	
	@Column(name="cf_req_addr")
	private int reqAddr;
	
	@Column(name="cf_use_signature")
	private int useSignature;
	
	@Column(name="cf_req_signature")
	private int reqSignature;
	
	@Column(name="cf_use_profile")
	private int useProfile;
	
	@Column(name="cf_req_profile")
	private int reqProfile;
	
	@Column(name="cf_register_level")
	private int registerLevel;
	
	@Column(name="cf_register_point")
	private int registerPoint;
	
	@Column(name="cf_icon_level")
	private int iconlevel;
	
	@Column(name="cf_use_recommend")
	private int useRecommend;
	
	@Column(name="cf_recommend_point")
	private int recommendPoint;
	
	@Column(name="cf_leave_day")
	private int leaveDay;
	
	@Column(name="cf_search_part")
	private int searchPart;
	
	@Column(name="cf_email_use")
	private int emailUse;
	
	@Column(name="cf_email_wr_super_admin")
	private int emailWriteSuperAdmin;
	
	@Column(name="cf_emaile_wr_group_admin")
	private int emailWriteGroupAdmin;
	
	@Column(name="cf_email_wr_board_admin")
	private int emailWriteBoardAdmin;
	
	@Column(name="cf_email_wr_write")
	private int emailWriteWriter;
	
	@Column(name="cf_email_wr_comment_all")
	private int emailWriteCommentAll;
	
	@Column(name="cf_email_mb_super_admin")
	private int emailJoinSuperAdmin;
	
	@Column(name="cf_email_mb_member")
	private int emailMemberMember;
	
	@Column(name="cf_email_po_super_admin")
	private int emailPointSuperAdmin;
	
	@Column(name="cf_prohibit_id")
	private String prohibitId;
	
	@Column(name="cf_prohibit_email")
	private String prohibitEmail;
	
	@Column(name="cf_new_del")
	private int newDel;
	
	@Column(name="cf_memo_del")
	private int memoDel;
	
	@Column(name="cf_visit_del")
	private int visitDel;
	
	@Column(name="cf_popular_del")
	private int popularDel;
	
	@Column(name="cf_optimize_date")
	@Temporal(TemporalType.DATE)
	private Date optimizeDate;
	
	@Column(name="cf_use_member_icon")
	private int useMemberIcon;
	
	@Column(name="cf_member_icon_size")
	private int memberIconSize;
	
	@Column(name="cf_member_icon_width")
	private int memberIconWidth;
	
	@Column(name="cf_member_icon_height")
	private int memberIconHeight;
	
	@Column(name="cf_login_minutes")
	private int loginMinutes;
	
	@Column(name="cf_image_extension")
	private String imageExtension;
	
	@Column(name="cf_flash_extension")
	private String flashExtension;
	
	@Column(name="cf_movie_extension")
	private String movieExtension;
	
	@Column(name="cf_formmail_is_member")
	private int formmailIsMember;
	
	@Column(name="cf_page_rows")
	private int pageRows;
	
	@Column(name="cf_mobile_page_rows")
	private int mobilePageRows;

	@Column(name="cf_visit")
	private String visit;
	
	@Column(name="cf_max_po_id")
	private int maxPointId;
	
	@Column(name="cf_stipulation")
	private String stipulation;
	
	@Column(name="cf_privacy")
	private String privacy;
	
	@Column(name="cf_open_modify")
	private int openModify;
	
	@Column(name="cf_memo_send_point")
	private int memoSendPoint;
	
	@Column(name="cf_mobile_new_skin")
	private String mobileNewSkin;
	
	@Column(name="cf_mobile_search_skin")
	private String mobileSearchSkin;
	
	@Column(name="cf_mobile_connect_skin")
	private String mobileConnectSkin;
	
	@Column(name="cf_mobile_faq_skin")
	private String mobileFaqSkin;
	
	@Column(name="cf_mobile_member_skin")
	private String mobileMemberSkin;
	
	@Column(name="cf_captcha_mp3")
	private String captchaMp3;
	
	@Column(name="cf_editor")
	private String editor;
	
	@Column(name="cf_cert_use")
	private int certUse;
	
	@Column(name="cf_cert_ipin")
	private String certIpin; 
	
	@Column(name="cf_cert_hp")
	private String certHp;
	
	@Column(name="cf_cert_kcb_cd")
	private String certKcbCd;
	
	@Column(name="cf_lg_mid")
	private String lgMid;
	
	@Column(name="cf_lg_mert_key")
	private String lgMertKey; 
	
	@Column(name="cf_cert_Limit")
	private int certLimit;
	
	@Column(name="cf_cert_req")
	private int certReq;
	
	@Column(name="cf_sms_use")
	private String smsUse;
	
	@Column(name="cf_sms_type")
	private String smsType;
	
	@Column(name="cf_icode_id")
	private String icodeId;
	
	@Column(name="cf_icode_pw")
	private String icodePW;
	
	@Column(name="cf_icode_server_ip")
	private String icodeServerIp;
	
	@Column(name="cf_icode_server_port")
	private String icodeServerPort;
	
	@Column(name="cf_googl_Shorturl_apikey")
	private String googlShorturlApikey;
	
	@Column(name="cf_facebook_appid")
	private String facebookAppid;
	
	@Column(name="cf_facebook_secret")
	private String facebookSecret;
	
	@Column(name="cf_twitter_key")
	private String twitterKey;
	
	@Column(name="cf_twitter_secret")
	private String twitterSecret;
	
	@Column(name="cf_kakao_js_apikey")
	private String kakaoJsApikey;
	
	@Column(name="cf_1_sub")
	private String _1Subj;
	
	@Column(name="cf_2_sub")
	private String _2Subj;
	
	@Column(name="cf_3_sub")
	private String _3Subj;
	
	@Column(name="cf_4_sub")
	private String _4Subj;
	
	@Column(name="cf_5_sub")
	private String _5Subj;
	
	@Column(name="cf_6_sub")
	private String _6Subj;
	
	@Column(name="cf_7_sub")
	private String _7Subj;
	
	@Column(name="cf_8_sub")
	private String _8Subj;
	
	@Column(name="cf_9_sub")
	private String _9Subj;
	
	@Column(name="cf_10_sub")
	private String _10Subj;
	
	@Column(name="cf_1")
	private String _1;
	
	@Column(name="cf_2")
	private String _2;
	
	@Column(name="cf_3")
	private String _3;
	
	@Column(name="cf_4")
	private String _4;
	
	@Column(name="cf_5")
	private String _5;
	
	@Column(name="cf_6")
	private String _6;
	
	@Column(name="cf_7")
	private String _7;
	
	@Column(name="cf_8")
	private String _8;
	
	@Column(name="cf_9")
	private String _9;
	
	@Column(name="cf_10")
	private String _10;
	
	
}





















