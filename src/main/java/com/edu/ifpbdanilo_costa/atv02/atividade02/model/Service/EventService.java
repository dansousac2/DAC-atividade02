package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ifpbdanilo_costa.atv02.atividade02.exceptions.IdNotFoundedInDB;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public Event save(Event event) {
		return eventRepository.save(event);
	}

	public Event update(Event event) throws IdNotFoundedInDB {

		if (isOnDB(event.getId())) {
			Event eventFinded = eventRepository.findById(event.getId()).get();
			eventFinded.setAdress(event.getAdress());
			eventFinded.setDate(event.getDate());
			eventFinded.setEventName(event.getEventName());
			return save(eventFinded);
		}
		throw new IdNotFoundedInDB("Do not found the event: " + event);
	}

	public void delete(Integer id) {

		if (isOnDB(id)) {
			Event evetFinded = eventRepository.findById(id).get();
			eventRepository.delete(evetFinded);
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
			System.out.println("Evento não encontrado no banco de dados!");
			return false;
		}
	}
}
