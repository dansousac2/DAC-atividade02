package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ifpbdanilo_costa.atv02.atividade02.exceptions.CpfAlreadyExistsInDB;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Guest;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Repository.EventRepository;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Repository.GuestRepository;

@Service
public class GuestService {
	
	@Autowired
	private GuestRepository guestRepository;
	@Autowired
	private EventRepository eventRepository;
	
	public Guest save(Guest guest) throws CpfAlreadyExistsInDB {
		if(!isOnDB(guest.getCpf())) {
			Optional<Event> opc = eventRepository.findById(guest.getEvent().getId()); // the id's event already is checked by GuestController
			Event event = opc.get();
			guest.setEvent(event);
			
			return guestRepository.save(guest);
		}
		throw new CpfAlreadyExistsInDB("Already a Guest on data base whit CPF: " + guest.getCpf());
	}
	
	public void update(String name, Long cpf, Event event) {
		
		if(isOnDB(cpf)) {
			Guest guestFinded = guestRepository.findByCpf(cpf).get();
			guestFinded.setName(name);
			guestFinded.setEvent(event);
			guestRepository.save(guestFinded);
		}
	}
	
	public void delete(Long cpf) {

		if(isOnDB(cpf)) {
			Guest guestFinded = guestRepository.findByCpf(cpf).get();
			guestRepository.delete(guestFinded);
		}
	}

	public void showAll() {
		List<Guest> lista = (List<Guest>) guestRepository.findAll();
		lista.forEach(item -> System.out.println(item));
	}
	
	private boolean isOnDB(Long cpf) {
		Optional<Guest> opGuest = guestRepository.findByCpf(cpf);
		
		if(opGuest.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Event getEvent(Integer id) {
		return eventRepository.findById(id).get();
	}
}
