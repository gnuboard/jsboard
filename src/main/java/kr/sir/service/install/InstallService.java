package kr.sir.service.install;

import kr.sir.domain.module.ConfigForm;

public interface InstallService {
	int writeConfigInfo(String prefix, ConfigForm configForm);
}
