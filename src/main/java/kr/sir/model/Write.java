package kr.sir.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class Write {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="wr_id")
	private int id;
	
	@Column(name="wr_num")
	private int num;
	
	@Column(name="wr_reply")
	private String reply;
	
	@Column(name="wr_parent")
	private int parent;
	
	@Column(name="wr_is_comment")
	private int isComment;
	
	@Column(name="wr_comment")
	private int comment;
	
	@Column(name="wr_comment_reply")
	private String commentReply;
	
	@Column(name="ca_name")
	private String categoryName;
	
	@Column(name="wr_option")
	private String option;
	
	@Column(name="wr_subject")
	private String subject;
	
	@Column(name="wr_content")
	private String content;
	
	@Column(name="wr_link1")
	private String link11;
	
	@Column(name="wr_link2")
	private String link12;
	
	@Column(name="wr_link1_hit")
	private int link1Hit;
	
	@Column(name="wr_link2_hit")
	private int link2Hit;
	
	@Column(name="wr_hit")
	private int hit;
	
	@Column(name="wr_good")
	private int good;
	
	@Column(name="wr_nogood")
	private int nogood;
	
	@Column(name="mb_id")
	private String memberId;
	
	@Column(name="wr_password")
	private String password;
	
	@Column(name="wr_name")
	private String name;
	
	@Column(name="wr_email")
	private String email;
	
	@Column(name="wr_homepage")
	private String homepage;
	
	@Column(name="wr_datetime")
	private Date datetime;
	
	@Column(name="wr_file")
	private int file;
	
	@Column(name="wr_last")
	private String last;
	
	@Column(name="wr_ip")
	private String ip;
	
	@Column(name="wr_facebook_user")
	private String facebookUser;
	
	@Column(name="wr_twitter_user")
	private String twitterUser;
	
	@Column(name="wr_1")
	private String _1;
	
	@Column(name="wr_2")
	private String _2;
	
	@Column(name="wr_3")
	private String _3;
	
	@Column(name="wr_4")
	private String _4;
	
	@Column(name="wr_5")
	private String _5;
	
	@Column(name="wr_6")
	private String _6;
	
	@Column(name="wr_7")
	private String _7;
	
	@Column(name="wr_8")
	private String _8;
	
	@Column(name="wr_9")
	private String _9;
	
	@Column(name="wr_10")
	private String _10;
	
	
	
	
}


















