package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Repository.EventDAO;

@Service
public class EventService {

	@Autowired
	private EventDAO eventDAO;

	public void save(Event event) {
		eventDAO.save(event);
	}

	public void update(Integer id, String name, String date, String adress) {

		if (isOnDB(id)) {
			Event eventFinded = eventDAO.findById(id).get();
			eventFinded.setAdress(adress);
			eventFinded.setDate(date);
			eventFinded.setEventName(name);
			save(eventFinded);
		}
	}

	public void delete(Integer id) {

		if (isOnDB(id)) {
			Event evetFinded = eventDAO.findById(id).get();
			eventDAO.delete(evetFinded);
		}
	}

	public void showAll() {
		List<Event> lista = (List<Event>) eventDAO.findAll();
		lista.forEach(item -> System.out.println(item));
	}

	private boolean isOnDB(Integer id) {
		Optional<Event> opGuest = eventDAO.findById(id);

		if (opGuest.isPresent()) {
			return true;
		} else {
			System.out.println("Evento não encontrado no banco de dados!");
			return false;
		}
	}
}
