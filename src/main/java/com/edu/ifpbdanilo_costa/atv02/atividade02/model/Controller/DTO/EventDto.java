package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Controller.DTO;

import java.math.BigDecimal;
import java.util.List;

public class EventDto {
	
	private Integer id;
	private String eventName;
	private String date;
	private String adress;
	private BigDecimal budget;
	private List<GuestDto> listGuest;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public List<GuestDto> getListGuest() {
		return listGuest;
	}

	public void setListGuest(List<GuestDto> listGuest) {
		this.listGuest = listGuest;
	}
	
}
