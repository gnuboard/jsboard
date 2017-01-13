package kr.sir.service.admin;

import java.util.List;

import kr.sir.domain.BoardGroup;
import kr.sir.domain.BoardGroupList;

public interface BoardService {

	
	//보드 그룹 리스트 가져오기
	public List<BoardGroupList> getAllBoardGroupsList();
	
	
	//보드 그룹 전체 개수
	public long getCountBoardGroups();
	
	//보드그룹 추가
	public void addBoardGroup(BoardGroup group);
	
	
	//보드그룹 한개 가져오기
	public BoardGroup getOneBoardGroup(String id);
	
	//그룹에 접근 가능한 회원수
	public int getCountAccessibleMembers(String id);
	
	//그룹삭제
	public int deleteGroups(String ids);
	
}
