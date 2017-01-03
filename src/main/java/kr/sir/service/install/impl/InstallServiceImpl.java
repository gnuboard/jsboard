package kr.sir.service.install.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import kr.sir.domain.Config;
import kr.sir.domain.module.ConfigForm;
import kr.sir.domain.repository.admin.ConfigRepository;
import kr.sir.domain.repository.install.InstallEmRepository;
import kr.sir.service.install.InstallService;

@Service
public class InstallServiceImpl implements InstallService{
	
	private InstallEmRepository installEmRepository;
	
	private ConfigRepository configRepository;

	@Autowired
	public void setInstallEmRepository(InstallEmRepository installEmRepository) {
		this.installEmRepository = installEmRepository;
	}
	
	@Autowired
	public void setConfigRepository(ConfigRepository configRepository) {
		this.configRepository = configRepository;
	}

	@Override
	public int writeConfigInfo(String prefix, ConfigForm configForm) {
		return installEmRepository.writeConfigInfo(prefix, configForm);
	}

	@Override
	public Config findConfigById(int id) {
		return configRepository.findById(id);
	}

	@Override
	public void createTable(ClassPathResource classPathResource, String prefix) {
		installEmRepository.createTable(classPathResource, prefix);
	}

	@Override
	public int existConfigTable(String prefix) {
		System.out.println("prefix" + prefix);
		return installEmRepository.existConfigTable(prefix);
	}
	
}
