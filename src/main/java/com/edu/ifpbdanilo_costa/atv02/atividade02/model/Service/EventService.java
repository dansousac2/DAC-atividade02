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
			eventFinded.setBudget(event.getBudget());
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

	public List<Event> showAll() {
		List<Event> list = eventRepository.findAll();
		return list;
	}
	
	public Event findById(Integer id) throws IdNotFoundedInDBException {
		Event eventFinded = eventRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundedInDBException("Not founded the id's event: " + id));
		return eventFinded;
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
