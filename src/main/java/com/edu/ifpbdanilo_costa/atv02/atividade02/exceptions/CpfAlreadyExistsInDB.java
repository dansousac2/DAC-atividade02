package com.edu.ifpbdanilo_costa.atv02.atividade02.exceptions;

public class CpfAlreadyExistsInDB extends Exception {

	private static final long serialVersionUID = 1L;

	public CpfAlreadyExistsInDB(String message) {
		super(message);
	}
	
}
