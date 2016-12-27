package kr.sir.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@Table(name="group_member")
public class GroupMember {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO) 
	@Column(name="gm_id")
	private int id;
	
	@Column(name="gr_id")
	private String groupId;
	
	@Column(name="mb_id")
	private String memberId;
	
	@Column(name="gm_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;

}
