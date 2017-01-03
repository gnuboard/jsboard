package kr.sir.domain.module;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class ConfigForm {	
	@NotEmpty
	private String table_prefix;
	@NotEmpty
	private String admin_id;
	@NotEmpty
	private String admin_pass;
	@NotEmpty
	private String admin_name;
	@NotEmpty
	private String admin_email;
	
}
