package kr.sir.domain.repository.admin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import kr.sir.domain.Board;

@Repository
public class BoardEmRepository {
	
	@PersistenceContext
	EntityManager em;
	
	public List<Board> getAllBoardsList(String query){
		return em.createNativeQuery(query,Board.class).getResultList();
	}
}
