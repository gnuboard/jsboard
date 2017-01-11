package kr.sir.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sir.common.CommonUtil;
import kr.sir.domain.BoardGroup;
import kr.sir.domain.BoardGroupList;
import kr.sir.domain.repository.admin.BoardGroupEmRepository;
import kr.sir.domain.repository.admin.BoardGroupRepository;
import kr.sir.domain.repository.admin.BoardGroupMemberRepository;
import kr.sir.service.admin.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardGroupRepository boardGroupRepository;
	private BoardGroupMemberRepository groupMemberRepository;
	private BoardGroupEmRepository boardGroupEmRepository;
	
	
	@Autowired
	public void setBoardGroupRepository(BoardGroupRepository boardGroupRepository){
		this.boardGroupRepository=boardGroupRepository;
	}
	@Autowired
	public void setGroupMemberRepository(BoardGroupMemberRepository boardGroupMemberRepository){
		this.groupMemberRepository=boardGroupMemberRepository;
	}
	@Autowired
	public void setBoardGroupEmRepository(BoardGroupEmRepository boardGroupEmRepository){
		this.boardGroupEmRepository=boardGroupEmRepository;
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
		/*return groupMemberRepository.getCountAccessibleMembers(id);*/
		return 0; // 바꿔야함
	}
	
	@Override
	public int deleteGroups(String ids) {
		ids="'"+ids+"'";
		
		if(ids.length()>1){			
			ids=ids.replace("," , "','");		
		}
		
		return boardGroupEmRepository.deleteGroups(ids,CommonUtil.getTablePrefix());
		
	}
	

}
