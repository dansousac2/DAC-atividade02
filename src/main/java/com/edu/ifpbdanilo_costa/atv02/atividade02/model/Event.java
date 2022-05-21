package com.edu.ifpbdanilo_costa.atv02.atividade02.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Event implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String eventName;
	private String date;
	private String adress;
	private BigDecimal budget;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_event")
	private List<Guest> listGuest;
	
	public Event() {
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
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
	public List<Guest> getListGuest() {
		return listGuest;
	}
	public void setListGuest(List<Guest> listGuest) {
		this.listGuest = listGuest;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		int count = 1;
		String guestNames = "\n";
		for(Guest gest : listGuest) {
			guestNames += gest.getName();
			
			if(count < listGuest.size()) {
				guestNames += "\n";
			}
			
			count++;
		}
		
		return "\nEvent \n[id = " + id + ", título do evento = " + eventName + ", data = " + date 
				+ ", endereço = " + adress + "\nConvidados: " + guestNames + "]";
	}
	
}
