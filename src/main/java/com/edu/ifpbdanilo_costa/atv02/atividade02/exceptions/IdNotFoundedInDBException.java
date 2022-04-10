package com.edu.ifpbdanilo_costa.atv02.atividade02.exceptions;

public class IdNotFoundedInDBException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public IdNotFoundedInDBException(String msg) {
		super(msg);
	}

}
