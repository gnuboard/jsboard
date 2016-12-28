package kr.sir.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data


public class BoardFile implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id @Column(name="bo_table")
	private String table;
	
	@Id @Column(name="wr_id")
	private int writeId;
	
	@Id @Column(name="bf_no")
	private int no;
	
	@Column(name="bf_source")
	private String source;
	
	@Column(name="bf_download")
	private int download;
	
	@Column(name="bf_content")
	private String content;
	
	@Column(name="bf_filesize")
	private int filesize;
	
	@Column(name="bf_width")
	private int fileWidth;
	
	@Column(name="bf_height")
	private int fileHeight;
	
	@Column(name="bf_type")
	private int fileType;
	
	@Column(name="bf_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fileDatetime;
}

















