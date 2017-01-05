package kr.sir.domain.repository.admin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import kr.sir.domain.PointJoinMember;

@Repository
public class PointEmRepository{

	@PersistenceContext
	EntityManager em;
	
	
	//모든 회원 포인트 합계
	public int getTotalPoint(String prefix) {
		String query = "select sum(po_point) as totalPoint from "+prefix+"point";
					
		return  (int) em.createNativeQuery(query, Integer.class).getSingleResult();
	}
	
	//포인트 처리 내용 전부 + 일부 회원 정보
	public List<PointJoinMember> getAllPointContent(String prefix){
		String query="select m.mb_id,m.mb_name,m.mb_nick,m.mb_homepage,p.* "
				     +"from "+prefix +"point p "
				     +"join "+prefix +"member m";
		return em.createNativeQuery(query,PointJoinMember.class).getResultList();
	}
	
	
}
