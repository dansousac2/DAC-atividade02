package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ifpbdanilo_costa.atv02.atividade02.exceptions.InvalidDate;
import com.edu.ifpbdanilo_costa.atv02.atividade02.exceptions.EventNotFoundedInDBException;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Repository.EventRepository;

@Service
public class ValidationService {
	
	@Autowired
	private EventRepository eventRepository;
	
	public void isValidEvent(Integer id) throws EventNotFoundedInDBException {
		Optional<Event> op = eventRepository.findById(id);
		if(!op.isPresent()) {
			throw new EventNotFoundedInDBException("Not founded in the DB the Event with the ID: " + id);
		}
	}

	public boolean isValidCpf(Long cpf) {
		if (String.valueOf(cpf).length() == 11 && onlyNumbers(cpf)) {
			return true;
		} else {
			System.out.println("CPF deve conter 11 dígitos, sendo estes apenas números!");
			return false;
		}
	}

	public void isValidDate(String date) throws InvalidDate {

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate d = LocalDate.parse(date, formatter);
			if(d.isBefore(LocalDate.now())) {
				throw new InvalidDate("Is not possible create a Event to a past date [" + d + "]");
			}
		} catch (DateTimeParseException e) {
			throw new InvalidDate("Verify if the date format is on dd/MM/yyyy");
		}
	}

	private boolean onlyNumbers(Long cpf) {
		boolean result = true;
		String cpfString = String.valueOf(cpf);
		
		char[] charList = cpfString.toCharArray();
		for (char c : charList) {
			String digit = String.valueOf(c);
			if (!digit.matches("[0-9]")) {
				result = false;
				break;
			}
		}

		return result;
	}
}
