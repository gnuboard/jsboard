package kr.sir.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sir.common.CommonUtil;
import kr.sir.domain.Board;
import kr.sir.domain.BoardGroup;
import kr.sir.domain.BoardGroupList;
import kr.sir.domain.repository.admin.BoardEmRepository;
import kr.sir.domain.repository.admin.BoardGroupEmRepository;
import kr.sir.domain.repository.admin.BoardGroupRepository;
import kr.sir.domain.repository.admin.BoardRepository;
import kr.sir.domain.repository.admin.BoardGroupMemberRepository;
import kr.sir.service.admin.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardGroupRepository boardGroupRepository;
	private BoardGroupMemberRepository boardGroupMemberRepository;
	private BoardGroupEmRepository boardGroupEmRepository;
	private BoardRepository boardRepository;
	private BoardEmRepository boardEmRepository;
	
	
	
	@Autowired
	public void setBoardGroupRepository(BoardGroupRepository boardGroupRepository){
		this.boardGroupRepository=boardGroupRepository;
	}
	@Autowired
	public void setGroupMemberRepository(BoardGroupMemberRepository boardGroupMemberRepository){
		this.boardGroupMemberRepository=boardGroupMemberRepository;
	}
	@Autowired
	public void setBoardGroupEmRepository(BoardGroupEmRepository boardGroupEmRepository){
		this.boardGroupEmRepository=boardGroupEmRepository;
	}
	@Autowired
	public void setBoardRepository(BoardRepository boardRepository){
		this.boardRepository=boardRepository;
	}
	@Autowired
	public void setBoardEmRepository(BoardEmRepository boardEmRepository) {
		this.boardEmRepository = boardEmRepository;
	}
	
	//보드그룹리스트 전체 가져오기
	@Override
	public List<BoardGroupList> getAllBoardGroupsList() {
		
		/*return boardGroupRepository.findAll();*/
		return boardGroupEmRepository.getAllBoardGroupsList(CommonUtil.getTablePrefix());
	}
	
	//보드 그룹 총 갯수
	@Override
	public long getCountBoardGroups() {		
		return boardGroupRepository.count();
	}
	
	//보드 그룹 추가
	@Override
	public void addBoardGroup(BoardGroup group) {
		boardGroupRepository.save(group);
		
	}
	
	//보드그룹 한개 가져오기          
	public BoardGroup getOneBoardGroup(String id){
		return boardGroupRepository.findById(id);
	}
	
	//그룹에 접근 가능한 회원수
	@Override
	public int getCountAccessibleMembers(String id) {
		/*boardGroupMemberRepository.getCountAccessibleMembers(id);*/
		return 0; // 바꿔야함
	}
	
	//그룹리스트에서 그룹삭제
	@Override
	public int deleteGroups(String ids) {
		
		//ids가 문자열이고 where gr_id in() 명령으로 삭제하기 때문에 문자열마다 홑따옴표로 감싸줘야함.
		ids="'"+ids+"'";		
		if(ids.length()>1){			
			ids=ids.replace("," , "','");		
		}		
		return boardGroupEmRepository.deleteGroups(ids,CommonUtil.getTablePrefix());
		
	}
	
	@Override
	public List<Board> getAllBoardsList() {
		//is_admin이 super면 모든테이블. 아니면 자기가 관리자인 그룹의 게시판 목록만.
		String is_admin="super";
		String prefix=CommonUtil.getTablePrefix();
		
		String sqlCommon="from "+prefix+"board b ";
		String sqlSearch=" where (1) ";
		
		
		if(!is_admin.equals("super")){
			sqlCommon+=","+prefix+"group g ";
			sqlSearch="and (b.gr_id=g.gr_id and g.gr_admin='"+"로그인한 아이디"+"'";
			
		}
		String query="select * "+sqlCommon+sqlSearch;
		
		
		
		return boardEmRepository.getAllBoardsList(query);
	}
	
	//게시판생성에서 시용할 변수들 생성
	@Override
	public List<String> makeValue(String type){
		List<String> values = new ArrayList<String>();
		String submitForm="<div class='btn_confirm01 btn_confirm'>"
				         +"<input type='submit' value='확인' class='btn_submit' accesskey='s'>"
				         +"<a href='./board_list.php?'>목록</a>";
		
		if(type.equals("update")){
			submitForm+="<a href='./board_copy.php?bo_table='.$bo_table.' id='board_copy' target='win_board_copy'>게시판복사</a>"
		               +"<a href='/board.php?bo_table='.$board['bo_table']. class='btn_frmline'>게시판 바로가기</a>"
		               +"<a href='./board_thumbnail_delete.php?bo_table='.$board['bo_table'].'&amp;'.$qstr.' onclick='return delete_confirm2(\'게시판 썸네일 파일을 삭제하시겠습니까?\');'>게시판 썸네일 삭제</a>";
		    
		    submitForm+="</div>";
		}
		
		
		
		return values;
		
	}
	
	
	//게시판 생성,수정시 그룹목록 출력과 생성시에는 해당하는 그룹 선택까지 한 selectBOX 태그
	public String getSelectedGroup(String id,String groupId,String event){
		String selectBoxTag="";
		
		
		List<BoardGroup> groupList=boardGroupEmRepository.getBoardGroupListByAdmin();
		
		for(int i=0; i<groupList.size();i++){
			if(i==0){
				selectBoxTag="<option id='"+id+"' value=''"+event+">선택</option>";
			}
			selectBoxTag+=selectOption(groupList.get(i).getId(),groupId,groupList.get(i).getSubject());
		}		
		return selectBoxTag;
	}
	
	public String selectOption(String value,String groupId,String text){
		
		if(value.equals(groupId)){
			return "<option value='"+value+"' selected='selected'>"+text+"</option>\n";
		}else{
			return "<option value='"+value+"'>"+text+"</option>\n";
		}
		
	}
	
	
	

}




























