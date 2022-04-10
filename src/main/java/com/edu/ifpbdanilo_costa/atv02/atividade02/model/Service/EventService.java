package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ifpbdanilo_costa.atv02.atividade02.exceptions.IdNotFoundedInDBException;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public Event save(Event event) {
		return eventRepository.save(event);
	}

	public Event update(Event event) throws IdNotFoundedInDBException {

		if (isOnDB(event.getId())) {
			Event eventFinded = eventRepository.findById(event.getId()).get();
			eventFinded.setAdress(event.getAdress());
			eventFinded.setDate(event.getDate());
			eventFinded.setEventName(event.getEventName());
			return save(eventFinded);
		}
		throw new IdNotFoundedInDBException("Not founded the id's event: " + event.getId());
	}

	public void delete(Integer id) throws IdNotFoundedInDBException {

		if (isOnDB(id)) {
			Event evetFinded = eventRepository.findById(id).get();
			eventRepository.delete(evetFinded);
		} else {
			throw new IdNotFoundedInDBException("Not founded the id's event: " + id);
		}
	}

	public void showAll() {
		List<Event> lista = (List<Event>) eventRepository.findAll();
		lista.forEach(item -> System.out.println(item));
	}

	private boolean isOnDB(Integer id) {
		Optional<Event> opGuest = eventRepository.findById(id);

		if (opGuest.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
}
