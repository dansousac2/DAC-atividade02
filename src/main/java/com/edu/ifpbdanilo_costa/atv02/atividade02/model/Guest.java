package com.edu.ifpbdanilo_costa.atv02.atividade02.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Guest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;
	@Id
	@Column(nullable = false)
	private Long cpf;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_event")
	private Event event;
	
	public Guest() {
	}
	
	public Guest(String name, Long cpf, Event event) {
		this.name = name;
		this.cpf = cpf;
		this.event = event;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Guest other = (Guest) obj;
		return Objects.equals(cpf, other.cpf);
	}

	@Override
	public String toString() {
		return "Guest [nome = " + name + ", cpf = " + cpf + ", evento = " + event.getEventName() 
		+ "]";
	}

}
