package kr.sir.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "table")
public class Prefix {
	
	private String prefix;
	
}
