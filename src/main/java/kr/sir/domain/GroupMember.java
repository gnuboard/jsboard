package kr.sir.domain;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(indexes = {
		@Index(name = "gr_id", columnList = "gr_id"),
		@Index(name = "mb_id", columnList = "mb_id")
})
public class GroupMember{
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="gm_id", columnDefinition = "int(11) NOT NULL")
	private int id;
	
	@Column(name="gr_id", columnDefinition = "varchar(255) NOT NULL default ''")
	private String groupId;
	
	@Column(name="mb_id", columnDefinition = "varchar(20) NOT NULL default ''")
	private String memberId;
	
	@Column(name="gm_datetime", columnDefinition = "datetime NOT NULL default '0000-00-00 00:00:00' ")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	
}
