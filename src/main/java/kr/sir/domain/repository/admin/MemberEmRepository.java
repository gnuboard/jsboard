package kr.sir.domain.repository.admin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.sir.domain.Member;

@Repository
public class MemberEmRepository {
	
	@PersistenceContext
	EntityManager em;
	
	public List<Member> findBySome1OrderSome2(String where, String orderBy) {
		String query = "select m from Member m where m." + where + "= :name order by " + orderBy + " desc";
		return em.createQuery(query, Member.class)
				.setParameter("name", "admin")
				.getResultList();
	}
	
	public List<Member> getAllMemberList(String prefix, String memberId) {
		String query = "select m.mb_id, m.mb_name, count(gm.gm_id) "
				+ "from js_member m "
				+ "join js_group_member gm "
				+ "on m.mb_id = gm.mb_id "
				+ "where m.mb_id = " + memberId;
		return em.createNativeQuery(query, Member.class).getResultList();
	}

}
