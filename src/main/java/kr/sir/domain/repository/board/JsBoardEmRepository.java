package kr.sir.domain.repository.board;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

	private int findArticleNumber(String query, int articleNumber) {
		Object obj = em.createQuery(query)
				.setParameter("articleNumber", articleNumber)
				.getSingleResult();
		
		if(obj != null) {
			return Integer.parseInt(obj.toString()); 
		} else {
			return 0;
		}
	}
	
	// 카테고리 리스트 가져오기
	@SuppressWarnings("unchecked")
	public List<String> findCategoryNames() {
		String query = "select distinct(w.categoryName) as categoryName from Write w";
		return em.createQuery(query).getResultList();
	}
}
