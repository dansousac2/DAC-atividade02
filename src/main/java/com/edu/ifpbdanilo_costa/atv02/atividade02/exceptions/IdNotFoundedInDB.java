package com.edu.ifpbdanilo_costa.atv02.atividade02.exceptions;

public class IdNotFoundedInDB extends Exception {
	
	private static final long serialVersionUID = 1L;

	public IdNotFoundedInDB(String msg) {
		super(msg);
	}

}
