package kr.sir.domain.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.sir.domain.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Integer>{
	public Config findById(int id);
}
