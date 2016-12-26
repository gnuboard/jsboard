package kr.sir.service.admin;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sir.model.Member;
import kr.sir.repository.admin.TestRepository;

@Service
@Transactional
public class TestService {
	
	private TestRepository testRepository;

	@Autowired
	public void setTestRepository(TestRepository testRepository) {
		this.testRepository = testRepository;
	}
	
	public Member findAdmin() {
		return testRepository.findByMemberId("admin");
	}
}
