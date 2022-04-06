package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service.EventService;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service.ValidationService;

@Controller
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private ValidationService validation;
	
	public void save(String name, String date, String adress) {
		if(validation.validateDate(date)) {
			eventService.save(new Event(name, date, adress));
		}
	}
	
	public void update(Integer id, String name, String date, String adress) {
		if(validation.validateDate(date)) {
			//informa se o id não for encontrado
			eventService.update(id, name, date, adress);
		}
	}
	
	public void delete(Integer id) {
		//informa se o id não for encontrado
		eventService.delete(id);
	}
	
	public void listAll() {
		eventService.showAll();
	}

}
