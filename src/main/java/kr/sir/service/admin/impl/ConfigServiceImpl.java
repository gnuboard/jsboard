package kr.sir.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sir.domain.Config;
import kr.sir.domain.repository.admin.ConfigRepository;
import kr.sir.service.admin.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService {

	private ConfigRepository configRepository;

	@Autowired
	public void setConfigRepository(ConfigRepository configRepository) {
		this.configRepository = configRepository;
	}

	@Override
	public Config getConfig() {
		return configRepository.findById(1);
	}

}
