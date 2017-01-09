package kr.sir.domain.repository.admin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import kr.sir.domain.Point;
import kr.sir.domain.PointJoinMember;

@Repository
public class PointEmRepository{

	@PersistenceContext
	EntityManager em;
	
	
	//모든 회원 포인트 합계
	public Point getTotalPoint(String prefix) {
		String query = "select sum(po_point) as totalPoint from "+prefix+"point";
		System.out.println("query:"+query);
		return  (Point) em.createNativeQuery(query, Point.class).getSingleResult();
	}
	
	//포인트 처리 내용 전부 + 회원 정보중 일부
	public List<PointJoinMember> getAllPointContent(String prefix){
		String query="select m.mb_name as name,m.mb_nick as nick,m.mb_homepage as homepage,m.mb_email as email, p.* "
				     +"from "+prefix +"point p "
				     +"join "+prefix +"member m "
				     +"on m.mb_id=p.mb_id "
				     +"order by p.po_datetime desc";
		return em.createNativeQuery(query,PointJoinMember.class).getResultList();
	}
	
	
	
	public Point getMemberpointByMemberId(String memberId,String prefix){
		String query="select p.po_mb_point as memberPoint,p.mb_id as memberId"
					 +" from "+prefix+"point p"
				     +" join "+prefix+"member m"
				     +" on m.mb_id=p.mb_id"
				     +" and m.mb_id="+"\'"+memberId+"\'"
				     +" order by po_datetime desc"
				     +" limit 1";
		  
		return  (Point) em.createNativeQuery(query,Point.class).getSingleResult();
		
	}
	
	
}
