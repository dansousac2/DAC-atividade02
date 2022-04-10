package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Controller.DTO;

import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Guest;

public class GuestDto {
	
	private Long cpf;
	private String name;
	private Event event;
	
	public GuestDto(Guest guest) {
		this.cpf = guest.getCpf();
		this.name = guest.getName();
		this.event = guest.getEvent();
	}
	
	public GuestDto() {
	}

	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
}
