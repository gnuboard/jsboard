package kr.sir.service.admin;

import java.util.List;

import kr.sir.domain.Board;
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
	
	//게시판 목록
	public List<Board> getAllBoardsList();

	//회원가입폼에서 사용할 변수 생성
	public List<String> makeValue(String type);
	
	//게시판 생성,수정시 그룹목록 출력과 생성시에는 해당하는 그룹 선택까지 한 selectBOX 태그
	public String getSelectedGroup(String name,String groupId,String event);
}
