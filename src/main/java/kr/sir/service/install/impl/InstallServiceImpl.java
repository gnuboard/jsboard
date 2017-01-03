package kr.sir.service.install.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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

	// 설치할 때 table에 설정 정보 insert
	@Override
	public int writeConfigInfo(String prefix, ConfigForm configForm) {
		return installEmRepository.writeConfigInfo(prefix, configForm);
	}

	// 설치할때 전체 table 생성
	@Override
	public void createTable(ClassPathResource classPathResource, String prefix) {
		installEmRepository.createTable(classPathResource, prefix);
	}

	// config table 존재 여부 확인하기
	@Override
	public int existConfigTable(String prefix) {
		System.out.println("prefix" + prefix);
		return installEmRepository.existConfigTable(prefix);
	}
	
}
