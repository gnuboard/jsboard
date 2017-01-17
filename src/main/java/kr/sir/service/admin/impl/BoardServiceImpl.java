package kr.sir.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sir.common.CommonUtil;
import kr.sir.domain.Board;
import kr.sir.domain.BoardGroup;
import kr.sir.domain.BoardGroupList;
import kr.sir.domain.BoardVO;
import kr.sir.domain.Config;
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
	
	
	//게시판 목록 
	@Override
	public List<Board> getAllBoardsList() {		
		return boardEmRepository.getAllBoardsList(CommonUtil.getTablePrefix());
	}
	
	
	//게시판 생성,수정시 그룹목록 출력과 생성시에는 해당하는 그룹 선택까지 한 selectBOX 태그
	public String getSelectedGroup(String selectId,String groupId,String event){
		String selectBoxTag="<select id='"+selectId+"' name='"+selectId+"' "+event+">\n";
		
		
		List<BoardGroup> groupList=boardGroupEmRepository.getBoardGroupListByAdmin();
		
		for(int i=0; i<groupList.size();i++){
			if(i==0){
				selectBoxTag+="<option id='"+selectId+"' value='"+event+"'>선택</option>\n";
			}
			selectBoxTag+=selectOption(groupList.get(i).getId(),groupId,groupList.get(i).getSubject());
		}
		
		selectBoxTag+="</select>";
		
		System.out.println("selectTag : " + selectBoxTag);
		return selectBoxTag;
	}
	
	public String selectOption(String value,String groupId,String text){
		
		if(value.equals(groupId)){
			return "<option value='"+value+"' selected='selected'>"+text+"</option>\n";
		}else{
			return "<option value='"+value+"'>"+text+"</option>\n";
		}
		
	}
	
	//생성된 게시판 갯수
	@Override
	public long getCountBoards() {		
		return boardRepository.count();
	}
	
	//게시판 추기
	@Override
	public void addBoard(Board board) {	
		boardRepository.save(board);	
	}
	
	// 리스트에서 게시판 수정
	@Override
	public void updateBoards(String[] chk,BoardVO boardVO){
		List<BoardVO> board= boardVO.getList();		
		for(String k:chk){
			Board tempBoard = board.get(Integer.parseInt(k));
			Board boardToUpdate = new Board();
			boardToUpdate.setId(tempBoard.getId());
			boardToUpdate.setSkin(tempBoard.getSkin());
			boardToUpdate.setMobileSkin(tempBoard.getMobileSkin());
			boardToUpdate.setSubject(tempBoard.getSubject());
			boardToUpdate.setReadPoint(tempBoard.getReadPoint());
			boardToUpdate.setWritePoint(tempBoard.getWritePoint());
			boardToUpdate.setCommentPoint(tempBoard.getCommentPoint());
			boardToUpdate.setDownloadPoint(tempBoard.getDownloadPoint());
			boardToUpdate.setUseSns(tempBoard.getUseSns());
			boardToUpdate.setUseSearch(tempBoard.getUseSearch());
			boardToUpdate.setOrder(tempBoard.getOrder());
			boardToUpdate.setDevice(tempBoard.getDevice());
			//여기부턴 cannot be null 에러 떄문에 억지로 매핑중
			boardToUpdate.setAdmin(tempBoard.getAdmin());
			boardToUpdate.setCategoryList(tempBoard.getCategoryList());
		
			boardEmRepository.updateBoard(boardToUpdate);			
			/*boardRepository.save(boardToSave);*/
		}
	}
	
	//게시판 삭제
	public void deleteBoards(String ids){
		//게시판에서 먼저 삭제
		boardEmRepository.deleteBoards(ids,CommonUtil.getTablePrefix());
		
		
		//최신글삭제 (boardNew)
		
		//스크랩삭제 (아직없음)
		
		//파일삭제(boardFile)
		
		//delete_cache_latest(테이블Id) 뭔지모름
		
		//게시판 폴더 전체 삭제 rm_rf(G5_DATA_PATH/file/테이블Id)
	}

	//게시판 생성시 기본 값을 가진 board
	@Override
	public Board getInitializedBoard(Config config) {
		Board board =new Board();
		board.setCountDelete(1);
		board.setCountModify(1);
		board.setReadPoint(config.getReadPoint());
		board.setWritePoint(config.getCommentPoint());
		board.setCommentPoint(config.getCommentPoint());
		board.setDownloadPoint(config.getDownloadPoint());
		board.setGalleryCols(4);
		board.setGalleryWidth(174);
		board.setGalleryHeight(124);
		board.setMobileGalleryWidth(125);
		board.setMobileGalleryHeight(100);
		board.setTableWidth(100);
		board.setPageRows(config.getPageRows());
		board.setMobilePageRows(config.getPageRows());
		board.setSubjectLen(60);
		board.setMobileSubjectLen(30);
		board.setNewIcon(24);
		board.setHotIcon(100);
		board.setImageWidth(600);
		board.setUploadCount(2); 
		board.setUploadSize(1048576);
		board.setReplyOrder(1);
		board.setUseSearch(1);
		board.setSkin("basic");
		board.setMobileSkin("basic");
		board.setUseSecret(0);
		board.setIncludeHead("head.jsp");
		board.setIncludeTail("tail.jsp");
		//여기부턴 널이 안되서 강제로 값 넣은거
		board.setMobileSubject("자유게시판");
		board.setNotice("");
		board.setSubject("");
		return board;
	}
	
	
}




























