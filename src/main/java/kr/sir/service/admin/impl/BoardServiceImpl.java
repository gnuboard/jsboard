package kr.sir.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.sir.common.CommonUtil;
import kr.sir.domain.Group;
import kr.sir.domain.GroupList;
import kr.sir.domain.repository.admin.BoardGroupEmRepository;
import kr.sir.domain.repository.admin.BoardGroupRepository;
import kr.sir.domain.repository.admin.GroupMemberRepository;
import kr.sir.service.admin.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardGroupRepository boardGroupRepository;
	private GroupMemberRepository groupMemberRepository;
	private BoardGroupEmRepository boardGroupEmRepository;
	
	
	@Autowired
	public void setBoardGroupRepository(BoardGroupRepository boardRepository){
		this.boardGroupRepository=boardRepository;
	}
	@Autowired
	public void setGroupMemberRepository(GroupMemberRepository groupMemberRepository){
		this.groupMemberRepository=groupMemberRepository;
	}
	@Autowired
	public void setBoardGroupEmRepository(BoardGroupEmRepository boardEmRepository){
		this.boardGroupEmRepository=boardEmRepository;
	}
	
	
	//보드그룹리스트 전체 가져오기
	@Override
	public List<GroupList> getAllBoardGroupsList() {
		
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
	public void addBoardGroup(Group group) {
		boardGroupRepository.save(group);
		
	}
	
	//보드그룹 한개 가져오기
	public Group getOneBoardGroup(String id){
		return boardGroupRepository.findById(id);
	}
	
	//그룹에 접근 가능한 회원수
	@Override
	public int getCountAccessMembers(String id) {
		return groupMemberRepository.getCountAccessibleMembers(id);
	}
	

}
