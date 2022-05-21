package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Guest;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Controller.DTO.GuestDto;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service.ConverterService;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service.GuestService;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service.ValidationService;

@RestController
@RequestMapping("/api/guest")
public class GuestController {

	@Autowired
	private GuestService guestService;
	@Autowired
	private ValidationService validation;
	@Autowired
	private ConverterService converterService;

	@PostMapping("/save")
	public ResponseEntity save(@RequestBody GuestDto dto) {
		try {
			validation.isValidCpf(dto.getCpf());
			validation.isValidEvent(dto.getEvent().getId());

			Guest guest = converterService.dtoToGuest(dto); // have only the "id" in your Event's atribute
			guest = guestService.save(guest); // Have a full association with the Event in the DB
			dto = converterService.guestToDto(guest);

			return new ResponseEntity(dto, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PutMapping("/update")
	public ResponseEntity update(@RequestBody GuestDto dto) { // The Event's Guest have only the "id" in this point
		try {
			validation.isValidCpf(dto.getCpf());
			validation.isValidEvent(dto.getEvent().getId());

			Guest guest = converterService.dtoToGuest(dto);
			Event event = guestService.getEvent(dto.getEvent().getId());
			guest.setEvent(event); // the Event's Guest have full association with the Event in the DB

			guest = guestService.update(guest);
			dto = converterService.guestToDto(guest);

			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@DeleteMapping("/delete")
	public ResponseEntity delete(@RequestBody GuestDto dto) {
		try {
			validation.isValidCpf(dto.getCpf());

			guestService.delete(dto.getCpf());

			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/listAll")
	public ResponseEntity listAll() {
		try {
			List<Guest> list = guestService.showAll();
			List<GuestDto> listDto = new ArrayList<>();
			
			list.forEach(g -> listDto.add(converterService.guestToDto(g)));
			
			return ResponseEntity.ok(listDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
