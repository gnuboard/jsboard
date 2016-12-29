package kr.sir.domain.repository.admin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
