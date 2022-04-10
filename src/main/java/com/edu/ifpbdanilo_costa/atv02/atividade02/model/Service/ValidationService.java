package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Repository.EventRepository;

@Service
public class ValidationService {
	
	@Autowired
	private EventRepository eventRepository;
	
	public boolean isValidEvent(Integer id) {
		Optional<Event> op = eventRepository.findById(id);
		if(op.isPresent()) {
			return true;
		} else {
			System.out.println("Id do evento não confere com banco de dados!");
			return false;
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

	public void validateDate(String date) {

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate d = LocalDate.parse(date, formatter);
			if(d.isBefore(LocalDate.now())) {
				// lançar exceção
				System.out.println("A data do evento não pode ser anterior ao dia atual!");
			}
		} catch (DateTimeParseException e) {
			// criar nova exceção
			System.out.println("Data informada inválida ou no formato errado (dd/mm/aaa)!");
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
