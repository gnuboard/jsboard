package kr.sir.common.exception;

import java.sql.SQLException;

public class DeleteException extends SQLException{

	private static final long serialVersionUID = 1L;

	public DeleteException(String message) {
		super(message);
	}

}
