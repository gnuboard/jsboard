package kr.sir.service.install.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sir.domain.module.ConfigForm;
import kr.sir.domain.repository.install.InstallEmRepository;
import kr.sir.service.install.InstallService;

@Service
public class InstallServiceImpl implements InstallService{
	
	private InstallEmRepository installEmRepository;

	@Autowired
	public void setInstallEmRepository(InstallEmRepository installEmRepository) {
		this.installEmRepository = installEmRepository;
	}
	
	@Override
	public int writeConfigInfo(String prefix, ConfigForm configForm) {
		return installEmRepository.writeConfigInfo(prefix, configForm);
	}
	
}
