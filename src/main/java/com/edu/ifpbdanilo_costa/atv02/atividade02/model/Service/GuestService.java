package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ifpbdanilo_costa.atv02.atividade02.exceptions.CpfAlreadyExistsInDB;
import com.edu.ifpbdanilo_costa.atv02.atividade02.exceptions.CpfNotFaundedInDBException;
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
	
	public Guest update(Guest guest) throws CpfNotFaundedInDBException {
		
		if(isOnDB(guest.getCpf())) {
			Guest guestFinded = guestRepository.findByCpf(guest.getCpf()).get();
			guestFinded.setName(guest.getName());
			guestFinded.setEvent(guest.getEvent());
			return guestRepository.save(guestFinded);
		}
		throw new CpfNotFaundedInDBException("CPF not founded to: " + guest.getCpf());
	}
	
	public void delete(Long cpf) {

		if(isOnDB(cpf)) {
			Guest guestFinded = guestRepository.findByCpf(cpf).get();
			guestRepository.delete(guestFinded);
		}
	}

	public List<Guest> showAll() {
		List<Guest> list = (List<Guest>) guestRepository.findAll();
		
		return list;
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
