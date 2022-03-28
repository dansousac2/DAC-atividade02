package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Guest;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.DAO.EventDAO;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.DAO.GuestDAO;

@Service
public class GuestService {
	
	@Autowired
	private GuestDAO guestDAO;
	@Autowired
	private EventDAO eventDAO;
	
	public void save(Guest guest) {
		if(!isOnDB(guest.getCpf())) {
			guestDAO.save(guest);
			System.out.println("Salvo com sucesso!");
		} else {
			System.out.println("Já existe um convidado com o CPF informado no banco de dados!");
		}
	}
	
	public void update(String name, Long cpf, Event event) {
		
		if(isOnDB(cpf)) {
			Guest guestFinded = guestDAO.findByCpf(cpf).get();
			guestFinded.setName(name);
			guestFinded.setEvent(event);
			guestDAO.save(guestFinded);
		}
	}
	
	public void delete(Long cpf) {

		if(isOnDB(cpf)) {
			Guest guestFinded = guestDAO.findByCpf(cpf).get();
			guestDAO.delete(guestFinded);
		}
	}

	public void showAll() {
		List<Guest> lista = (List<Guest>) guestDAO.findAll();
		lista.forEach(item -> System.out.println(item));
	}
	
	private boolean isOnDB(Long cpf) {
		Optional<Guest> opGuest = guestDAO.findByCpf(cpf);
		
		if(opGuest.isPresent()) {
			return true;
		} else {
			System.out.println("Convidado não encontrado no banco de dados!");
			return false;
		}
	}
	
	public Event getEvent(Integer id) {
		return eventDAO.findById(id).get();
	}
}
