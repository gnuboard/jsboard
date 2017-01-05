package kr.sir.domain.repository.admin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class PointEmRepository{

	@PersistenceContext
	EntityManager em;
	
	
	/*//모든 회원 포인트 합계
	public int getTotalPoint(String prefix) {
		String query = "select sum(po_point) as totalPoint from "+prefix+"point";
					
		return (int) em.createNativeQuery(query, Integer.class).getSingleResult();
	}*/
	
	
}
