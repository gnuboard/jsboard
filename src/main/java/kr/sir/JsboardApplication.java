package kr.sir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import kr.sir.domain.Prefix;

@SpringBootApplication
public class JsboardApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(JsboardApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(JsboardApplication.class, args);
	}
}
