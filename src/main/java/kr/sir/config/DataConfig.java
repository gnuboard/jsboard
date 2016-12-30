package kr.sir.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import kr.sir.domain.Config;
import kr.sir.domain.repository.admin.ConfigRepository;

@Configuration
public class DataConfig {
	
	private final int CONFIG_ID = 1;
	
	private ConfigRepository configRepository;
	
	@Autowired
	public void setConfigRepository(ConfigRepository configRepository) {
		this.configRepository = configRepository;
	}

	@Value("${table.prefix}")
	private String prefix;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
		yaml.setResources(new ClassPathResource("application.yml"));
		propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
		return propertySourcesPlaceholderConfigurer;
	}
	
	@Bean
	public String getPrefix() {
		return this.prefix;
	}
	
	@Bean
	public Config getConfig() {
		return configRepository.findById(CONFIG_ID);
	}
	
}
