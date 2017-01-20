package kr.sir.service.install;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

import org.springframework.core.io.ClassPathResource;

import kr.sir.domain.InstallAdmin;

public interface InstallService {
	public int writeConfigInfo(String prefix, InstallAdmin configForm);
	public void createTable(ClassPathResource classPathResource, String prefix);
	public void writeConfigToYaml(String prefix) throws FileNotFoundException, IOException;
	public int writeAdminInfo(String prefix, InstallAdmin adminForm) throws UnknownHostException;
	public int restartService() throws Exception; 
}
