package kr.sir.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;


@Data
@Table(indexes = {
		@Index(name = "index1", columnList = "mb_id, po_rel_table, po_rel_id, po_rel_action"),
		@Index(name = "index2", columnList = "po_expire_date")
})
@MappedSuperclass
public abstract class PointBaseEntity {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="po_id", columnDefinition = "int(11) NOT NULL")
	private int id;
	
	@Column(name="mb_id", columnDefinition = "varchar(20) NOT NULL default ''")
	private String memberId="";
	
	@Column(name="po_datetime", columnDefinition = "datetime NOT NULL default '0000-00-00 00:00:00'")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime=new Date();
	
	@Column(name="po_content", columnDefinition = "varchar(255) NOT NULL default ''")
	private String content="";
	            
	@Column(name="po_point", columnDefinition = "int(11) NOT NULL default '0'")
	private int point=0;
	
	@Column(name="po_use_point", columnDefinition = "int(11) NOT NULL default '0'")
	private int usePoint=0;
	
	@Column(name="po_expired", columnDefinition="tinyint(4) NOT NULL default '0'")
	private int expired=0;
	
	@Column(name="po_expire_date", columnDefinition = "date NOT NULL default '0000-00-00'")
	@Temporal(TemporalType.DATE)
	private Date expireDate=defaultTimestamp();
	
	@Column(name="po_mb_point", columnDefinition = "int(11) NOT NULL default '0'")
	private int memberPoint=0;
	
	@Column(name="po_rel_table", columnDefinition = "varchar(20) NOT NULL default ''")
	private String relTable="";
	
	@Column(name="po_rel_id", columnDefinition = "varchar(20) NOT NULL default ''")
	private String relId="";
	
	@Column(name="po_rel_action", columnDefinition = "varchar(255) NOT NULL default ''")
	private String relAction="";
	
	private Date defaultTimestamp() {
		Calendar c = Calendar.getInstance();
		c.set(9999, 12-1, 31, 23, 59, 59);//달부분은 가독성 때문에 -1 을 적어둠
		/*c.set(Calendar.YEAR, 9999);
		c.set(Calendar.MONTH, 12-1);
		c.set(Calendar.);*/
		return c.getTime();
	}
	
}
