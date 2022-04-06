package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Guest;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service.GuestService;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service.ValidationService;

@Controller
public class GuestController {

	@Autowired
	private GuestService guestService;

	@Autowired
	private ValidationService validation;

	public void save(String name, Long cpf, Integer eventId) {
		if (validation.isValidCpf(cpf) && validation.isValidEvent(eventId)) {
			Event event = guestService.getEvent(eventId);
			guestService.save(new Guest(name, cpf, event));
		}
	}

	public void update(String name, Long cpf, Integer eventId) {
		if (validation.isValidCpf(cpf) && validation.isValidEvent(eventId)) {
			Event event = guestService.getEvent(eventId);
			//informa se o CPF não for encontrado
			guestService.update(name, cpf, event);
		}
	}

	public void delete(Long cpf) {
		if (validation.isValidCpf(cpf)) {
			guestService.delete(cpf);
		}
	}

	public void listAll() {
		guestService.showAll();
	}
}
