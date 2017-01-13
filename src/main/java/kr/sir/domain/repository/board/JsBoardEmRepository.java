package kr.sir.domain.repository.board;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import kr.sir.common.CommonUtil;
import kr.sir.domain.Write;

@Repository
public class JsBoardEmRepository {

	@PersistenceContext
	EntityManager em;

	
	// 선택 삭제
	@Transactional
	public int delete(String ids) {
		String query = "delete from " + CommonUtil.getTablePrefix()
					+ "write where wr_id in (" + ids + ")";
		return em.createNativeQuery(query, Write.class).executeUpdate();
	}

	// 이전 or 다음 글 찾기
	public int findPrevOrNextArticle(int articleNumber, String prevOrNext) {
		String query = querySelect(prevOrNext);
		return findArticleNumber(query, articleNumber);
	}

	// 이전 or 다음 글 찾기 : 쿼리 선택
	private String querySelect(String prevOrNext) {
		String query = "";
		switch (prevOrNext) {
		case "prev":
			query = "SELECT MIN(w.id) FROM Write w WHERE w.id > :articleNumber";
			break;
		case "next":
			query = "SELECT MAX(w.id) FROM Write w WHERE w.id < :articleNumber";
			break;
		}
		return query;
	}

	// 이전 or 다음 글 찾기 : 쿼리 수행
	private int findArticleNumber(String query, int articleNumber) {
		Object obj = em.createQuery(query)
				.setParameter("articleNumber", articleNumber)
				.getSingleResult();
		
		return CommonUtil.convertObjectToInteger(obj);
	}
	
	// 카테고리 리스트 가져오기
	@SuppressWarnings("unchecked")
	public List<String> findCategoryNames() {
		String query = "SELECT DISTINCT(w.categoryName) as categoryName FROM Write w";
		return em.createQuery(query).getResultList();
	}
	
	// 게시판에서 가장 작은 wr_num 가져오기
	public int findMinNum() {
		String query = "SELECT MIN(w.num) FROM Write w";
		Object obj = em.createQuery(query).getSingleResult();
		return CommonUtil.convertObjectToInteger(obj);
	}
	
	// 게시판에서 가장 큰 wr_id 가져오기
	public int findMaxId() {
		String query = "SELECT MAX(w.id) FROM Write w";
		Object obj = em.createQuery(query).getSingleResult();
		return CommonUtil.convertObjectToInteger(obj);
	}

	// 해당 게시글에서 가장 큰 댓글 그룹 번호 가져오기
	public int findMaxCommentById(int articleNumber) {
		String query = "SELECT MAX(w.comment) FROM Write w WHERE w.parent=:articleNumber and w.isComment = 1";
		Object obj = em.createQuery(query)
				.setParameter("articleNumber", articleNumber)
				.getSingleResult();
		return CommonUtil.convertObjectToInteger(obj);
	}
	
	// 댓글이 들어갈 장소(commentReply)를 구한다.
	public String findMaxCommentReplyByBaseComment(int articleNumber, int comment, String commentReply, int isComment) {
		String query = "SELECT MAX(w.commentReply) FROM Write w"
				+ " WHERE w.parent=:articleNumber"
				+ " and w.isComment=:isComment"
				+ " and w.comment=:comment";
		if( !("").equals(commentReply) ) {
			query += " and w.commentReply LIKE CONCAT(:commentReply,'%')";
		}
		
		Query q;
		if( !("").equals(commentReply) ) {
			q = em.createQuery(query)
					.setParameter("articleNumber", articleNumber)
					.setParameter("comment", comment)
					.setParameter("isComment", isComment)
					.setParameter("commentReply", commentReply);
		} else {
			q = em.createQuery(query)
					.setParameter("articleNumber", articleNumber)
					.setParameter("comment", comment)
					.setParameter("isComment", isComment);
		}
		Object obj = q.getSingleResult();
		
		return CommonUtil.convertObjectToString(obj);
	}
	
}
