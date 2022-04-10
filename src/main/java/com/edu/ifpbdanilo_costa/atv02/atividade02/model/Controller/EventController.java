package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@PostMapping("/save")
	public ResponseEntity save(@RequestBody EventDto dto) {
		try {
			validation.isValidDate(dto.getDate());
			Event event = converterService.dtoToEvent(dto); // whitout "id"
			event = eventService.save(event); // whit "id"
			dto = converterService.eventToDto(event);

			return new ResponseEntity(dto, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity update(@PathVariable Integer id, @RequestBody EventDto dto) {
		try {
			validation.isValidDate(dto.getDate());
			validation.isValidEvent(id);
			Event event = converterService.dtoToEvent(dto); // whitout id
			event.setId(id);
			event = eventService.update(event);
			dto = converterService.eventToDto(event);
			
			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			return  ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable Integer id) {
		try {
			eventService.delete(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
//
//	public void listAll() {
//		eventService.showAll();
//	}

}
