package kr.sir.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class BoardFile implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bo_id", columnDefinition = "shortint(6) NOT NULL")
	private int boardId;
	
//	@Id
//	@Column(name="bo_table", columnDefinition = "varchar(20) NOT NULL default ''")
//	private String table;
	
	@Id @Column(name="wr_id", columnDefinition = "int(11) NOT NULL default '0'")
	private int writeId;
	
	@Id @Column(name="bf_no", columnDefinition = "int(11) NOT NULL default '0'")
	private int no;
	
	@Column(name="bf_source", columnDefinition = "varchar(255) NOT NULL default ''")
	private String source;
	
	@Column(name="bf_file", columnDefinition = "varchar(255) NOT NULL default ''")
	private String file;
	
	@Column(name="bf_download", columnDefinition = "int(11) NOT NULL")
	private int download;
	
	@Column(name="bf_content", columnDefinition = "text NOT NULL")
	private String content;
	
	@Column(name="bf_filesize", columnDefinition = "int(11) NOT NULL default '0'")
	private int filesize;
	
	@Column(name="bf_width", columnDefinition = "int(11) NOT NULL default '0'")
	private int fileWidth;
	
	@Column(name="bf_height", columnDefinition = "smallint(6) NOT NULL default '0'")
	private int fileHeight;
	
	@Column(name="bf_type", columnDefinition = "tinyint(4) NOT NULL default '0'")
	private int fileType;
	
	@Column(name="bf_datetime", columnDefinition = "datetime NOT NULL default '0000-00-00 00:00:00'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fileDatetime;
}

















