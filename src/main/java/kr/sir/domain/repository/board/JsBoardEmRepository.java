package kr.sir.domain.repository.board;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import kr.sir.common.CommonUtil;
import kr.sir.domain.Write;

@Repository
public class JsBoardEmRepository {

	@PersistenceContext
	EntityManager em;

	
	// 선택 삭제
	@Transactional
	public int delete(String ids) {
		String query = "delete from "
					+ CommonUtil.getTablePrefix() + "write"
					+" where wr_id in (" + ids + ")";
		return em.createNativeQuery(query, Write.class).executeUpdate();
	}
	
}
