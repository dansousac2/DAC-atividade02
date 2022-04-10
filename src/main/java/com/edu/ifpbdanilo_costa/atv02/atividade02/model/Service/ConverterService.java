package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Guest;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Controller.DTO.EventDto;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Controller.DTO.GuestDto;

@Service
public class ConverterService {

	public Event dtoToEvent(EventDto dto) { // whitout "id"
		Event event = new Event();
		event.setEventName(dto.getEventName());
		event.setAdress(dto.getAdress());
		event.setDate(dto.getDate());
		event.setBudget(dto.getBudget());
		event.setListGuest(dtoToListGuest(dto.getListGuest()));

		return event;
	}

	private List<Guest> dtoToListGuest(List<GuestDto> listGuest) {
		if (!(listGuest == null)) {
			List<Guest> guests = new ArrayList<>();
			listGuest.forEach(e -> guests.add(dtoToGuest(e)));
			return guests;
		}
		return new ArrayList<>();

	}

	public Guest dtoToGuest(GuestDto dto) {
		Guest entity = new Guest();
		entity.setCpf(dto.getCpf());
		entity.setName(dto.getName());
		entity.setEvent(dto.getEvent());

		return entity;
	}

	public EventDto eventToDto(Event entity) { // not return the budget's event
		EventDto dto = new EventDto();
		dto.setId(entity.getId());
		dto.setEventName(entity.getEventName());
		dto.setDate(entity.getDate());
		dto.setAdress(entity.getAdress());
		dto.setListGuest(guestToListDto(entity.getListGuest()));

		return dto;
	}

	private List<GuestDto> guestToListDto(List<Guest> listGuest) {
		if (!(listGuest == null)) {
			List<GuestDto> dto = new ArrayList<>();
			listGuest.forEach(e -> dto.add(guestNamesOnly(e)));
			return dto;
		}
		return new ArrayList<>();
	}

	private GuestDto guestNamesOnly(Guest entity) {
		GuestDto dto = new GuestDto();
		dto.setName(entity.getName());
		return dto;
	}

	public GuestDto guestToDto(Guest entity) { // not return the cpf's guest and event's guest
		GuestDto dto = new GuestDto();
		dto.setName(entity.getName());
		dto.setEvent(entity.getEvent());

		return dto;
	}

}
