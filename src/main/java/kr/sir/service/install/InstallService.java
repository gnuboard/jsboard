package kr.sir.service.install;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import org.springframework.core.io.ClassPathResource;

import kr.sir.domain.InstallAdmin;

public interface InstallService {
	public int writeConfigInfo(String prefix, InstallAdmin configForm);
	public int createTable(ClassPathResource classPathResource, String prefix) throws SQLException, IOException;
	public void writeConfigToYaml(String prefix) throws FileNotFoundException, IOException;
	public int writeAdminInfo(String prefix, InstallAdmin adminForm) throws UnknownHostException;
	public int restartService() throws Exception; 
}
