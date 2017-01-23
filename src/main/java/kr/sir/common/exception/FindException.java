package kr.sir.common.exception;

import java.sql.SQLException;

public class FindException extends SQLException{

	private static final long serialVersionUID = 1L;

	public FindException(String message) {
		super(message);
	}

}
