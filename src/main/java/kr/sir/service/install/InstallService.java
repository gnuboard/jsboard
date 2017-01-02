package kr.sir.service.install;

import kr.sir.domain.module.ConfigForm;

public interface InstallService {

	void writeConfigInfo(String prefix, ConfigForm configForm);

}
