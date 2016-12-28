package kr.sir.config;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.MySQLMyISAMDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HttpPutFormContentFilter;

@Configuration
public class WebConfig{
	
	// Http method 'PUT' Usable config
	@Bean
	public HttpPutFormContentFilter httpPutFormContentFilter() {
		return new HttpPutFormContentFilter();
	}
	
	@Bean
	public MySQLDialect mySQLDialect() {
		return new MySQLMyISAMDialect();
	}
	

	
}
