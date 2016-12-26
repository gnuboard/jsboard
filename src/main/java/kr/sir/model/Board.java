package kr.sir.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Board {

	@Id 
	@Column(name = "bo_table")
	private String boardTable;
	
	public String getBoardTable() {
		return boardTable;
	}

	public void setBoardTable(String boardTable) {
		this.boardTable = boardTable;
	}

	@Column(name="gr_id")
	private String groupId;
	
	@Column(name="bo_subject")
	private String boardSubject;
	
	@Column(name="bo_mobile_subject")
	private String boardMobileSubject;
	
	@Column(name="bo_device")
	private String boardDevice;
	
	@Column(name="bo_admin")
	private String boardAdmin;
	
	@Column(name="bo_list_level")
	private String boardListLevel;
	
	@Column(name="bo_read_level")
	private int boardReadLevel;	
	
	@Column(name="bo_write_level")
	private int boardWriteLevel;
	
	@Column(name="bo_reply_level")
	private int boardReplyLevel;
	
	@Column(name="bo_comment_level")
	private int boardCommentLevel;
	
	@Column(name="bo_upload_level")
	private int boardUploadLevel;
	
	@Column(name="bo_download_level")
	private int boardDownloadLevel;
	
	@Column(name="bo_html_level")
	private int boardHtmlLevel;
	
	@Column(name="bo_link_level")
	private int boardLinkLevle;
	
	@Column(name="bo_count_delete")
	private int boardCountDelete;
	
	@Column(name="bo_count_modify")
	private int boardCountModify;
	
	@Column(name="bo_read_point")
	private int boardReadPoint;
	
	@Column(name="bo_write_point")
	private int boardWritePoint;
	
	@Column(name="bo_comment_point")
	private int boardCommentPoint;
	
	@Column(name="bo_download_point")
	private int boardDownloadPoint;
	
	@Column(name="bo_use_category")
	private int boardUseCategory;
	
	@Column(name="bo_category_list")
	private String boardCategoryList;
	
	@Column(name="bo_use_sideview")
	private int boardUseSideview;
	
	@Column(name="bo_use_file_content")
	private int boardUseFileContent;
	
	@Column(name="bo_use_secret")
	private int boardUseSecret;
	
	@Column(name="bo_use_dhtml_editor")
	private int boardUseDhtmlEditor;
	
	@Column(name="bo_use_rss_view")
	private int boardUseRssView;
	
	@Column(name="bo_use_good")
	private int boardUseGood;
	
	@Column(name="bo_use_nogood")
	private int boardUseNogood;
	
	@Column(name="bo_use_name")
	private int boardUseName;
	
	@Column(name="bo_use_signature")
	private int boardUseSignature;
	
	@Column(name="bo_use_ip_view")
	private int boardUseIpView;
	
	@Column(name="bo_use_list_view")
	private int boardUseListView;
	
	@Column(name="bo_use_list_file")
	private int boardUseListFile;
	
	@Column(name="bo_use_list_content")
	private int boardUseListContent;
	
	@Column(name="bo_table_width")
	private int boardTableWidth;
	
	@Column(name="bo_subject_len")
	private int boardSubjectLen;
	
	@Column(name="bo_mobile_subject_len")
	private int boardMobileSubjectLen;
	
	@Column(name="bo_page_rows")
	private int boardPageRows;
	
	@Column(name="bo_mobile_page_rows")
	private int boardMobilePageRows;
	
	@Column(name="bo_new")
	private int boardNew;
	
	@Column(name="bo_hot")
	private int boardHot;
	
	@Column(name="bo_image_width")
	private int boardImageWidth;
	
	@Column(name="bo_skin")
	private String boardSkin;
	
	@Column(name="bo_mobile_skin")
	private String boardMobileSkin;
	
	@Column(name="bo_include_head")
	private String boardIncludeHead;
	
	@Column(name="bo_include_tail")
	private String boardincludeTail;
	
	@Column(name="bo_content_head")
	private String boardContentHead;
	
	@Column(name="bo_mobile_content_head")
	private String boardMobileContentHead;
	
	@Column(name="bo_content_tail")
	private String boardContentTail;
	
	@Column(name="bo_mobile_content_tail")
	private String boardMobileContentTail;
	
	@Column(name="bo_insert_content")
	private String boardInsertContent;
	
	@Column(name="bo_gallery_cols")
	private int boardGalleryCols;
	
	@Column(name="bo_gallery_width")
	private int boardGalleryWidth;
	
	@Column(name="bo_gallery_height")
	private int boardGalleryHeight;
	
	@Column(name="bo_mobile_gallery_width")
	private int boardMobileGalleryWidth;
	
	@Column(name="bo_mobile_gallery_height")
	private int boardMobileGalleryHeight;
	
	@Column(name="bo_upload_size")
	private int boardUploadSize;
	
	@Column(name="bo_reply_order")
	private int boardReplyOrder;
	
	@Column(name="bo_use_search")
	private int boardUseSearch;
	
	@Column(name="bo_order")
	private int boardOrder;
	
	@Column(name="bo_count_write")
	private int boardCountWrite;
	
	@Column(name="bo_count_comment")
	private int boardCountComment;
	
	@Column(name="bo_write_min")
	private int boardWriteMin;
	
	@Column(name="bo_write_max")
	private int boardWriteMax;
	
	@Column(name="bo_comment_min")
	private int boardCommentMin;
	
	@Column(name="bo_comment_max")
	private int boardCommentMax;
	
	@Column(name="bo_notice")
	private String boardNotice;
	
	@Column(name="bo_upload_count")
	private int boardUploadCount;
	
	@Column(name="bo_use_email")
	private int boardUseEmail;
	
	@Column(name="bo_use_cert")
	private String boardUseCert;
	
	@Column(name="bo_use_sns")
	private int boardUseSns;
	
	@Column(name="bo_sort_field")
	private String boardSortField;
	
	@Column(name="bo_1_subj")
	private String board1Subj;

	@Column(name="bo_2_subj")
	private String board2Subj;
	
	@Column(name="bo_3_subj")
	private String board3Subj;
	
	@Column(name="bo_4_subj")
	private String board4Subj;
	
	@Column(name="bo_5_subj")
	private String board5Subj;
	
	@Column(name="bo_6_subj")
	private String board6Subj;
	
	@Column(name="bo_7_subj")
	private String board7Subj;
	
	@Column(name="bo_8_subj")
	private String board8Subj;
	
	@Column(name="bo_9_subj")
	private String board9Subj;
	
	@Column(name="bo_10_subj")
	private String board10Subj;
	
	@Column(name="bo_1")
	private String board1;
	
	@Column(name="bo_2")
	private String board2;
	
	@Column(name="bo_3")
	private String board3;
	
	@Column(name="bo_4")
	private String board4;
	
	@Column(name="bo_5")
	private String board5;
	
	@Column(name="bo_6")
	private String board6;
	
	@Column(name="bo_7")
	private String board7;
	
	@Column(name="bo_8")
	private String board8;
	
	@Column(name="bo_9")
	private String board9;
	
	@Column(name="bo_10")
	private String board10;
}





























