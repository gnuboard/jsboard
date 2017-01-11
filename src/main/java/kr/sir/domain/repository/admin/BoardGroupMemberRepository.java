package kr.sir.domain.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.sir.domain.BoardGroupMember;

@Repository
public interface BoardGroupMemberRepository extends JpaRepository<BoardGroupMember, Integer> {

	/*@Modifying
	@Query("select count(gm.id) from BoardGroupMember gm where gm.id=?1") 
	public int getCountAccessibleMembers(String id);*/
}
