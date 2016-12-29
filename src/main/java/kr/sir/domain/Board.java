package kr.sir.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Board {

	@Id @Column(name = "bo_table", columnDefinition = "varchar(20) NOT NULL DEFAULT ''")
	private String table;
	
	@Column(name="gr_id", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String groupId;
	
	@Column(name="bo_subject", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String subject;
	
	@Column(name="bo_mobile_subject", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String mobileSubject;
	
	@Column(name="bo_device", columnDefinition = "enum('both','pc','mobile') NOT NULL DEFAULT 'both'")
	private String device;
	
	@Column(name="bo_admin", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String admin;
	
	@Column(name="bo_list_level", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private String listLevel;
	
	@Column(name="bo_read_level", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int readLevel;	
	
	@Column(name="bo_write_level", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int writeLevel;
	
	@Column(name="bo_reply_level", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int replyLevel;
	
	@Column(name="bo_comment_level", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int commentLevel;
	
	@Column(name="bo_upload_level", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int uploadLevel;
	
	@Column(name="bo_download_level", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int downloadLevel;
	
	@Column(name="bo_html_level", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int htmlLevel;
	
	@Column(name="bo_link_level", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int linkLevle;
	
	@Column(name="bo_count_delete", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int countDelete;
	
	@Column(name="bo_count_modify", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int countModify;
	
	@Column(name="bo_read_point", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int readPoint;
	
	@Column(name="bo_write_point", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int writePoint;
	
	@Column(name="bo_comment_point", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int commentPoint;
	
	@Column(name="bo_download_point", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int downloadPoint;
	
	@Column(name="bo_use_category", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int useCategory;
	
	@Column(name="bo_category_list", columnDefinition = "text NOT NULL")
	private String categoryList;
	
	@Column(name="bo_use_sideview", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int useSideview;
	
	@Column(name="bo_use_file_content", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int useFileContent;
	
	@Column(name="bo_use_secret", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int useSecret;
	
	@Column(name="bo_use_dhtml_editor", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int useDhtmlEditor;
	
	@Column(name="bo_use_rss_view", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int useRssView;
	
	@Column(name="bo_use_good", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int useGood;
	
	@Column(name="bo_use_nogood", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int useNogood;
	
	@Column(name="bo_use_name", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int useName;
	
	@Column(name="bo_use_signature", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int useSignature;
	
	@Column(name="bo_use_ip_view", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int useIpView;
	
	@Column(name="bo_use_list_view", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int useListView;
	
	@Column(name="bo_use_list_file", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int useListFile;
	
	@Column(name="bo_use_list_content", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int useListContent;
	
	@Column(name="bo_table_width", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int tableWidth;
	
	@Column(name="bo_subject_len", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int subjectLen;
	
	@Column(name="bo_mobile_subject_len", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int mobileSubjectLen;
	
	@Column(name="bo_page_rows", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int pageRows;
	
	@Column(name="bo_mobile_page_rows", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int mobilePageRows;
	
	@Column(name="bo_new", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int newIcon;
	
	@Column(name="bo_hot", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int hotIcon;
	
	@Column(name="bo_image_width", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int imageWidth;
	
	@Column(name="bo_skin", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String skin;
	
	@Column(name="bo_mobile_skin", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String mobileSkin;
	
	@Column(name="bo_include_head", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String includeHead;
	
	@Column(name="bo_include_tail", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String includeTail;
	
	@Column(name="bo_content_head", columnDefinition = "text NOT NULL")
	private String contentHead;
	
	@Column(name="bo_mobile_content_head", columnDefinition = "text NOT NULL")
	private String mobileContentHead;
	
	@Column(name="bo_content_tail", columnDefinition = "text NOT NULL")
	private String contentTail;
	
	@Column(name="bo_mobile_content_tail", columnDefinition = "text NOT NULL")
	private String mobileContentTail;
	
	@Column(name="bo_insert_content", columnDefinition = "text NOT NULL")
	private String insertContent;
	
	@Column(name="bo_gallery_cols", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int galleryCols;
	
	@Column(name="bo_gallery_width", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int galleryWidth;
	
	@Column(name="bo_gallery_height", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int galleryHeight;
	
	@Column(name="bo_mobile_gallery_width", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int mobileGalleryWidth;
	
	@Column(name="bo_mobile_gallery_height", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int mobileGalleryHeight;
	
	@Column(name="bo_upload_size", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int uploadSize;
	
	@Column(name="bo_reply_order", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int replyOrder;
	
	@Column(name="bo_use_search", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int useSearch;
	
	@Column(name="bo_order", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int order;
	
	@Column(name="bo_count_write", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int countWrite;
	
	@Column(name="bo_count_comment", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int countComment;
	
	@Column(name="bo_write_min", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int writeMin;
	
	@Column(name="bo_write_max", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int writeMax;
	
	@Column(name="bo_comment_min", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int commentMin;
	
	@Column(name="bo_comment_max", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
	private int commentMax;
	
	@Column(name="bo_notice", columnDefinition = "text NOT NULL")
	private String notice;
	
	@Column(name="bo_upload_count", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int uploadCount;
	
	@Column(name="bo_use_email", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int useEmail;
	
	@Column(name="bo_use_cert", columnDefinition = "enum('','cert','adult','hp-cert','hp-adult') NOT NULL DEFAULT ''")
	private String useCert;
	
	@Column(name="bo_use_sns", columnDefinition = "tinyint(4) NOT NULL DEFAULT '0'")
	private int useSns;
	
	@Column(name="bo_sort_field", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String sortField;
	
	@Column(name="bo_1_subj", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra1Key;

	@Column(name="bo_2_subj", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra2Key;
	
	@Column(name="bo_3_subj", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra3Key;
	
	@Column(name="bo_4_subj", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra4Key;
	
	@Column(name="bo_5_subj", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra5Key;
	
	@Column(name="bo_6_subj", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra6Key;
	
	@Column(name="bo_7_subj", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra7Key;
	
	@Column(name="bo_8_subj", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra8Key;
	
	@Column(name="bo_9_subj", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra9Key;
	
	@Column(name="bo_10_subj", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra10Key;
	
	@Column(name="bo_1", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra1Value;
	
	@Column(name="bo_2", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra2Value;
	
	@Column(name="bo_3", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra3Value;
	
	@Column(name="bo_4", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra4Value;
	
	@Column(name="bo_5", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra5Value;
	
	@Column(name="bo_6", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra6Value;
	
	@Column(name="bo_7", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra7Value;
	
	@Column(name="bo_8", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra8Value;
	
	@Column(name="bo_9", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra9Value;
	
	@Column(name="bo_10", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
	private String extra10Value;
}





























