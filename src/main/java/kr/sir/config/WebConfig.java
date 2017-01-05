package kr.sir.config;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.MySQLMyISAMDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import kr.sir.common.CommonInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	// Http method 'PUT' Usable config
	@Bean
	public HttpPutFormContentFilter httpPutFormContentFilter() {
		return new HttpPutFormContentFilter();
	}
	
	@Bean
	public MySQLDialect mySQLDialect() {
		return new MySQLMyISAMDialect();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) { 
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(commonInterceptor()).addPathPatterns("/**");
	}

	@Bean
	public CommonInterceptor commonInterceptor() {
		return new CommonInterceptor(); 
	}
	
}

