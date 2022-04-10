package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ifpbdanilo_costa.atv02.atividade02.exceptions.CpfFormatInvalidException;
import com.edu.ifpbdanilo_costa.atv02.atividade02.exceptions.IdNotFoundedInDBException;
import com.edu.ifpbdanilo_costa.atv02.atividade02.exceptions.InvalidDateException;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Repository.EventRepository;

@Service
public class ValidationService {
	
	@Autowired
	private EventRepository eventRepository;
	
	public void isValidEvent(Integer id) throws IdNotFoundedInDBException   {
		Optional<Event> op = eventRepository.findById(id);
		if(!op.isPresent()) {
			throw new IdNotFoundedInDBException("Not founded in the DB the Event with the ID: " + id);
		}
	}

	public void isValidCpf(Long cpf) throws CpfFormatInvalidException {
		if (!(String.valueOf(cpf).length() == 11 && onlyNumbers(cpf))) {
			throw new CpfFormatInvalidException("CPF is not in right pattern");
		}
	}

	public void isValidDate(String date) throws InvalidDateException {

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate d = LocalDate.parse(date, formatter);
			if(d.isBefore(LocalDate.now())) {
				throw new InvalidDateException("Is not possible create a Event to a past date [" + d + "]");
			}
		} catch (DateTimeParseException e) {
			throw new InvalidDateException("Verify if the date format is on dd/MM/yyyy");
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
