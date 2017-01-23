package kr.sir.domain.repository.admin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import kr.sir.common.util.CommonUtil;
import kr.sir.domain.Board;

@Repository
public class BoardEmRepository {
	
	@PersistenceContext
	EntityManager em;
	
	//게시판 전체 목록
	public List<Board> getAllBoardsList(String prefix){
		//is_admin이 super면 모든테이블. 아니면 자기가 관리자인 그룹의 게시판 목록만.
		String is_admin="super";	
		
		String sqlCommon="FROM "+prefix+"board b ";
		String sqlSearch=" WHERE (1) ";
		
		
		if(is_admin.equals("group")){
			sqlCommon+=","+prefix+"group g ";
			sqlSearch+="AND (b.gr_id=g.gr_id AND g.gr_admin='"+"로그인한 아이디"+"')";
			
		}
		String query="SELECT * "+sqlCommon+sqlSearch;
		
		System.out.println("쿼리문: "+ query);
		return em.createNativeQuery(query,Board.class).getResultList();
	}

	//리스트에서 게시판 삭제
	@Transactional
	public void deleteBoards(String ids,String prefix) {
		String query="DELETE FROM "+prefix+"board "
				   +"WHERE bo_id IN ("+ids+")";
		em.createNativeQuery(query).executeUpdate();
		
	}
	
	//리스트에서 게시판 수정
	@Transactional
	public void updateBoard(Board board){
		String query="UPDATE Board "
				   +"SET skin=:skin,"
				   +"mobileSkin=:mobileSkin,"
				   +"Subject=:subject,"
				   +"readPoint=:readPoint,"
				   +"writePoint=:writePoint,"
				   +"commentPoint=:commentPoint,"
				   +"downloadPoint=:downloadPoint,"
				   +"useSns=:useSns,"
				   +"useSearch=:useSearch,"
				   +"order=:order,"
				   +"device=:device "
		           +" WHERE id=:id";
		
		em.createQuery(query)
		.setParameter("skin",board.getSkin())
		.setParameter("mobileSkin", board.getMobileSkin())
		.setParameter("subject", board.getSubject())
		.setParameter("readPoint", board.getSubject())
		.setParameter("writePoint", board.getWritePoint())
		.setParameter("commentPoint", board.getCommentPoint())
		.setParameter("downloadPoint", board.getDownloadPoint())
		.setParameter("useSns", board.getUseSns())
		.setParameter("useSearch", board.getUseSearch())
		.setParameter("order", board.getOrder())
		.setParameter("device", board.getDevice())
		.setParameter("id", board.getId())
		.executeUpdate();
	}
}









