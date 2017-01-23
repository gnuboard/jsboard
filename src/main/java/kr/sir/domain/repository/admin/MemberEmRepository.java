package kr.sir.domain.repository.admin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import kr.sir.domain.Member;
import kr.sir.domain.MemberGroupCount;

@Repository
public class MemberEmRepository {
	
	@PersistenceContext
	EntityManager em;
	
	
	//예시용 메서드 사용하지않음.
	public List<Member> findBySome1OrderSome2(String where, String orderBy) {
		String query = "select m from Member m where m." + where + "= :name order by " + orderBy + " desc";
		return em.createQuery(query, Member.class)
				.setParameter("name", "admin")
				.getResultList();
	}
	
	//회원 전체 검색(어드민 회원관리에서 접근그룹 수를 표현해 주기 위해 findAll()대신 네이티브 쿼리 사용,접근 그룹이 없는 회원도 출력해야 하므로 left join 사용)
	public List<MemberGroupCount> getAllMembersList(String prefix) {
		String query = "select count(gm.gm_id) as countGroupMember, m.*"
				+ " from " + prefix + "member m"
				+ " left join " + prefix + "group_member gm"
				+ " on m.mb_id = gm.mb_id"
				+ " group by m.mb_id";
		
		
		return em.createNativeQuery(query, MemberGroupCount.class).getResultList();
	}
	
	//회원 리스트에서 회원 삭제
	public void deleteMembers(String ids,String prefix){
		//그누보드5에서 삭제가 안되서 일부러 냅둠ㅈㅈ
	}
	
	
	
	
	//모든 회원 포인트 합계
	public int getTotalPoint(String prefix) {
		String query = "select sum(mb_point) as totalPoint from "+prefix+"member";
		Object object=em.createNativeQuery(query).getSingleResult();
		
		if(object != null) {
			return Integer.parseInt(object.toString()); 
		} else {
			return 0;
		}	
	}
	
	
	
	

	public List<Member> findMembers(String sfl,String stx,String sst,String sod){
		String query="select m from Member m where m.";
		return null;//작업중
	}

}
