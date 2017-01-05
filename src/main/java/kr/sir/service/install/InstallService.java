package kr.sir.service.install;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;

import kr.sir.domain.module.ConfigForm;

public interface InstallService {
	public int writeConfigInfo(String prefix, ConfigForm configForm);
	public void createTable(ClassPathResource classPathResource, String prefix);
	public int existConfigTable(String prefix);
	public void writeConfigToYaml(String prefix) throws FileNotFoundException, IOException; 
}
