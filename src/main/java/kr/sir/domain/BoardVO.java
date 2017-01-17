package kr.sir.domain;

import java.util.List;

import lombok.Data;

//게시판리스트에서 게시판을 다중 수정하기 위해 만들어둔 클래스.
@Data
public class BoardVO extends Board{

	private List<BoardVO> list;

	
}
