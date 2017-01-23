package kr.sir.domain.repository.admin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import kr.sir.common.util.CommonUtil;


@Repository
public class CommonEmRepository {
	
	@PersistenceContext
	EntityManager em;
	
	// 검색
	public List<?> search(String keyword, String searchWord, Object entity, String orderBy) {
		String query = "select * from "
					+ CommonUtil.getTablePrefix() + entity.getClass().getSimpleName().toLowerCase()
					+" where " + keyword + " = '" + searchWord + "'"
					+" order by " + keyword + " " + orderBy;
		return em.createNativeQuery(query, entity.getClass()).getResultList();
	}
	
}