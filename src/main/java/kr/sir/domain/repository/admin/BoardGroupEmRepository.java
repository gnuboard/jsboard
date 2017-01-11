package kr.sir.domain.repository.admin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import kr.sir.domain.BoardGroupList;

@Repository
public class BoardGroupEmRepository {

	@PersistenceContext
	EntityManager em;
	
	public List<BoardGroupList> getAllBoardGroupsList(String prefix){
		String query="SELECT g.*,COUNT(gm.gm_id)as countAccessibleMembers ,COUNT(b.bo_table) as countIncludeBoards "+ //bo_table을 bo_id로 바꿔야함 
					 "FROM "+prefix+"group g LEFT JOIN "+ prefix+"group_member gm "+
				     "ON g.gr_id=gm.gr_id "+
					 "LEFT JOIN "+prefix+"board b "+
					 "ON g.gr_id=b.gr_id "+
					 "GROUP BY g.gr_id";
		
		return em.createNativeQuery(query, BoardGroupList.class).getResultList();
	}
	
	@Transactional
	public int deleteGroups(String ids,String prefix){
		String query="delete from "+prefix+"group where gr_id in ("+ids+")";
		
		return em.createNativeQuery(query).executeUpdate();
	}
	
	
	
	
	
	
	
}
