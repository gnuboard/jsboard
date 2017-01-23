package kr.sir.service.install.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import ch.qos.logback.classic.Logger;
import kr.sir.domain.InstallAdmin;
import kr.sir.domain.Member;
import kr.sir.domain.repository.install.InstallEmRepository;
import kr.sir.service.install.InstallService;

@Service
public class InstallServiceImpl implements InstallService {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(InstallServiceImpl.class);

	private InstallEmRepository installEmRepository;

	@Autowired
	public void setInstallEmRepository(InstallEmRepository installEmRepository) {
		this.installEmRepository = installEmRepository;
	}

	// table에 설정 정보 insert
	@Override
	public int writeConfigInfo(String prefix, InstallAdmin configForm) {
		return installEmRepository.writeConfigInfo(prefix, configForm);
	}

	// 전체 table 생성
	@Override
	public int createTable(ClassPathResource classPathResource, String prefix) throws SQLException, IOException {
		return installEmRepository.createTable(classPathResource, prefix);
	}
	
	@Override
	public int writeAdminInfo(String prefix, InstallAdmin adminForm) throws UnknownHostException {
		
		return installEmRepository.writeAdminInfo(prefix, adminInfoSave(adminForm));
	}
	
	private Member adminInfoSave(InstallAdmin adminForm) {
		Member member = new Member();
		
		member.setMemberId(adminForm.getMemberId());
		member.setPassword(adminForm.getPassword());
		member.setName(adminForm.getName());
		member.setEmail(adminForm.getEmail());
		
		return member;
	}

	// config.yml에 table prefix 정보 저장하기
	@Override
	public void writeConfigToYaml(String prefix) throws FileNotFoundException, IOException {
		Yaml yaml = new Yaml();
		// 경로를 application.yml의 profile에 따라 가져오도록 변경해야함.
//		String path = "src/main/resources/config.yml";
		String path = "../webapps/ROOT/WEB-INF/classes/config.yml";
		
		logger.debug("path : " + path);
		logger.debug("prefix : " + prefix);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("prefix", prefix);
		File file = new File(path);	// 얻은 경로에 파일 가져오기
		String output = dumpYamlData(yaml, data);
		
		logger.debug("yaml output : " + output);
		
		writeToFileByBufferedWriter(output, file);
		
		logger.debug("write into config.yml success!");
	}

	private String dumpYamlData(Yaml yaml, Map<String, Object> data) {
		return yaml.dump(data);	// yaml 형식으로 data 저장
	}

	private void writeToFileByBufferedWriter(String output, File file) throws IOException {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			// write into file
			bw.write(output);
			bw.flush();
		} finally {
			closeBufferedWriter(bw);
		}
	}

	private void closeBufferedWriter(BufferedWriter bw) throws IOException {
		if(bw!=null) {
			bw.close();
		}
	}

	@Override
	public int restartService() throws Exception {
		String[] command = { "cd ~/tomcat/bin/", "./catalina.sh stop", "./catalina.sh start" };
		
		Runtime runtime = Runtime.getRuntime();
		Process process = null;
		for(String com : command) {
			process = runtime.exec(com);
			InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while((line = br.readLine()) != null) {
            	logger.debug(line);
            	System.out.flush();
            }
		}

		return process.waitFor();
	}

}
