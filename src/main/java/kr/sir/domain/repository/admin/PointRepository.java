package kr.sir.domain.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.sir.domain.Point;
@Repository
public interface PointRepository extends JpaRepository<Point, Integer> {
		
		//모든 회원 포인트 합계
		@Query(value="select count(p.id) from Point p")
		public int getTotalPoint();
}
