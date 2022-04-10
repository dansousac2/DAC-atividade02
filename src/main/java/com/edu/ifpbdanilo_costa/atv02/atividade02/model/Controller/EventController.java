package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Controller.DTO.EventDto;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service.ConverterService;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service.EventService;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service.ValidationService;

@RestController
@RequestMapping("/api/event")
public class EventController {

	@Autowired
	private EventService eventService;
	@Autowired
	private ValidationService validation;
	@Autowired
	private ConverterService converterService;

	@PostMapping
	@RequestMapping("/save")
	public ResponseEntity save(@RequestBody EventDto dto) {
		try {
			validation.validateDate(dto.getDate());
			Event event = converterService.dtoToEvent(dto); // whitout "id"
			event = eventService.save(event); // whit "id"
			dto = converterService.eventToDto(event);
			
			return new ResponseEntity(dto,HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

//	public void update(Integer id, String name, String date, String adress) {
//		if (validation.validateDate(date)) {
//			// informa se o id não for encontrado
//			eventService.update(id, name, date, adress);
//		}
//	}
//
//	public void delete(Integer id) {
//		// informa se o id não for encontrado
//		eventService.delete(id);
//	}
//
//	public void listAll() {
//		eventService.showAll();
//	}

}
