package kr.sir.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Board {

	@Id @Column(name = "bo_table")
	private String table;
	
	@Column(name="gr_id")
	private String groupId;
	
	@Column(name="bo_subject")
	private String subject;
	
	@Column(name="bo_mobile_subject")
	private String mobileSubject;
	
	@Column(name="bo_device")
	private String device;
	
	@Column(name="bo_admin")
	private String admin;
	
	@Column(name="bo_list_level")
	private String listLevel;
	
	@Column(name="bo_read_level")
	private int readLevel;	
	
	@Column(name="bo_write_level")
	private int writeLevel;
	
	@Column(name="bo_reply_level")
	private int replyLevel;
	
	@Column(name="bo_comment_level")
	private int commentLevel;
	
	@Column(name="bo_upload_level")
	private int uploadLevel;
	
	@Column(name="bo_download_level")
	private int downloadLevel;
	
	@Column(name="bo_html_level")
	private int htmlLevel;
	
	@Column(name="bo_link_level")
	private int linkLevle;
	
	@Column(name="bo_count_delete")
	private int countDelete;
	
	@Column(name="bo_count_modify")
	private int countModify;
	
	@Column(name="bo_read_point")
	private int readPoint;
	
	@Column(name="bo_write_point")
	private int writePoint;
	
	@Column(name="bo_comment_point")
	private int commentPoint;
	
	@Column(name="bo_download_point")
	private int downloadPoint;
	
	@Column(name="bo_use_category")
	private int useCategory;
	
	@Column(name="bo_category_list")
	private String categoryList;
	
	@Column(name="bo_use_sideview")
	private int useSideview;
	
	@Column(name="bo_use_file_content")
	private int useFileContent;
	
	@Column(name="bo_use_secret")
	private int useSecret;
	
	@Column(name="bo_use_dhtml_editor")
	private int useDhtmlEditor;
	
	@Column(name="bo_use_rss_view")
	private int useRssView;
	
	@Column(name="bo_use_good")
	private int useGood;
	
	@Column(name="bo_use_nogood")
	private int useNogood;
	
	@Column(name="bo_use_name")
	private int useName;
	
	@Column(name="bo_use_signature")
	private int useSignature;
	
	@Column(name="bo_use_ip_view")
	private int useIpView;
	
	@Column(name="bo_use_list_view")
	private int useListView;
	
	@Column(name="bo_use_list_file")
	private int useListFile;
	
	@Column(name="bo_use_list_content")
	private int useListContent;
	
	@Column(name="bo_table_width")
	private int tableWidth;
	
	@Column(name="bo_subject_len")
	private int subjectLen;
	
	@Column(name="bo_mobile_subject_len")
	private int mobileSubjectLen;
	
	@Column(name="bo_page_rows")
	private int pageRows;
	
	@Column(name="bo_mobile_page_rows")
	private int mobilePageRows;
	
	@Column(name="bo_new")
	private int newIcon;
	
	@Column(name="bo_hot")
	private int hotIcon;
	
	@Column(name="bo_image_width")
	private int imageWidth;
	
	@Column(name="bo_skin")
	private String skin;
	
	@Column(name="bo_mobile_skin")
	private String mobileSkin;
	
	@Column(name="bo_include_head")
	private String includeHead;
	
	@Column(name="bo_include_tail")
	private String includeTail;
	
	@Column(name="bo_content_head")
	private String contentHead;
	
	@Column(name="bo_mobile_content_head")
	private String mobileContentHead;
	
	@Column(name="bo_content_tail")
	private String contentTail;
	
	@Column(name="bo_mobile_content_tail")
	private String mobileContentTail;
	
	@Column(name="bo_insert_content")
	private String insertContent;
	
	@Column(name="bo_gallery_cols")
	private int galleryCols;
	
	@Column(name="bo_gallery_width")
	private int galleryWidth;
	
	@Column(name="bo_gallery_height")
	private int galleryHeight;
	
	@Column(name="bo_mobile_gallery_width")
	private int mobileGalleryWidth;
	
	@Column(name="bo_mobile_gallery_height")
	private int mobileGalleryHeight;
	
	@Column(name="bo_upload_size")
	private int uploadSize;
	
	@Column(name="bo_reply_order")
	private int replyOrder;
	
	@Column(name="bo_use_search")
	private int useSearch;
	
	@Column(name="bo_order")
	private int order;
	
	@Column(name="bo_count_write")
	private int countWrite;
	
	@Column(name="bo_count_comment")
	private int countComment;
	
	@Column(name="bo_write_min")
	private int writeMin;
	
	@Column(name="bo_write_max")
	private int writeMax;
	
	@Column(name="bo_comment_min")
	private int commentMin;
	
	@Column(name="bo_comment_max")
	private int commentMax;
	
	@Column(name="bo_notice")
	private String notice;
	
	@Column(name="bo_upload_count")
	private int uploadCount;
	
	@Column(name="bo_use_email")
	private int useEmail;
	
	@Column(name="bo_use_cert")
	private String useCert;
	
	@Column(name="bo_use_sns")
	private int useSns;
	
	@Column(name="bo_sort_field")
	private String sortField;
	
	@Column(name="bo_1_subj")
	private String extra1Key;

	@Column(name="bo_2_subj")
	private String extra2Key;
	
	@Column(name="bo_3_subj")
	private String extra3Key;
	
	@Column(name="bo_4_subj")
	private String extra4Key;
	
	@Column(name="bo_5_subj")
	private String extra5Key;
	
	@Column(name="bo_6_subj")
	private String extra6Key;
	
	@Column(name="bo_7_subj")
	private String extra7Key;
	
	@Column(name="bo_8_subj")
	private String extra8Key;
	
	@Column(name="bo_9_subj")
	private String extra9Key;
	
	@Column(name="bo_10_subj")
	private String extra10Key;
	
	@Column(name="bo_1")
	private String extra1Value;
	
	@Column(name="bo_2")
	private String extra2Value;
	
	@Column(name="bo_3")
	private String extra3Value;
	
	@Column(name="bo_4")
	private String extra4Value;
	
	@Column(name="bo_5")
	private String extra5Value;
	
	@Column(name="bo_6")
	private String extra6Value;
	
	@Column(name="bo_7")
	private String extra7Value;
	
	@Column(name="bo_8")
	private String extra8Value;
	
	@Column(name="bo_9")
	private String extra9Value;
	
	@Column(name="bo_10")
	private String extra10Value;
}





























