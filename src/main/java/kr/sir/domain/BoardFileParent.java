package kr.sir.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class BoardFileParent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int boardId;
	private int writeId;
	private int no;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardFileParent other = (BoardFileParent) obj;
		if (boardId != other.boardId)
			return false;
		if (no != other.no)
			return false;
		if (writeId != other.writeId)
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + boardId;
		result = prime * result + no;
		result = prime * result + writeId;
		return result;
	}

}
