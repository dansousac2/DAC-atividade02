package com.edu.ifpbdanilo_costa.atv02.atividade02.exceptions;

public class EventNotFoundedInDBException extends Exception {

	private static final long serialVersionUID = 1L;

	public EventNotFoundedInDBException(String msg) {
		super(msg);
	}

}
