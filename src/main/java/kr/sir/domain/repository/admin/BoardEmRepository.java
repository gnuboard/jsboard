package kr.sir.domain.repository.admin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import kr.sir.common.CommonUtil;
import kr.sir.domain.Board;

@Repository
public class BoardEmRepository {
	
	@PersistenceContext
	EntityManager em;
	
	//게시판 전체 목록
	public List<Board> getAllBoardsList(String prefix){
		//is_admin이 super면 모든테이블. 아니면 자기가 관리자인 그룹의 게시판 목록만.
		String is_admin="super";	
		
		String sqlCommon="from "+prefix+"board b ";
		String sqlSearch=" where (1) ";
		
		
		if(is_admin.equals("group")){
			sqlCommon+=","+prefix+"group g ";
			sqlSearch+="and (b.gr_id=g.gr_id and g.gr_admin='"+"로그인한 아이디"+"')";
			
		}
		String query="select * "+sqlCommon+sqlSearch;
		
		System.out.println("쿼리문: "+ query);
		return em.createNativeQuery(query,Board.class).getResultList();
	}

	//리스트에서 게시판 삭제
	public void deleteBoards(String ids,String prefix) {
		String query="DELETE FROM "+prefix+"board "
				   +"WHERE bo_id IN ("+ids+")";
		em.createNativeQuery(query).executeUpdate();
		
	}
}
