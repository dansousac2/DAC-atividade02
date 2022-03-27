package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Guest;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service.GuestService;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service.ValidationService;

@Controller
public class GuestController {

	@Autowired
	private GuestService guestService;

	@Autowired
	private ValidationService validation;

	public void menu(Scanner scan) {
		String resp = "3";
		while (resp != "0") {
			System.out.println("\nIndique o que deseja fazer:" 
					+ "\n[0] Voltar ao menu anterior"
					+ "\n[1] Cadastrar convidado"
					+ "\n[2] Atualizar convidado existente"
					+ "\n[3] Deletar convidado existente" 
					+ "\n[4] Listar todos os convidados");

			System.out.printf("=> ");
			resp = scan.nextLine();

			switch (resp) {
			case "1":
				System.out.println("= = Cadastrar convidado = =");
				
				System.out.printf("Informe o nome do convidado: ");
				String name = scan.nextLine();
				
				System.out.printf("Informe o CPF: ");
				String cpf = scan.nextLine();
				
				System.out.printf("Informe o id do evento: ");
				Integer id = Integer.parseInt(scan.nextLine());
				
				save(name, Long.parseLong(cpf), id);
				break;
				
			case "2":
				System.out.println("= = Atualizar convidado existente = =");
				
				System.out.printf("Informe o nome do convidado: ");
				String name2 = scan.nextLine();
				
				System.out.printf("Informe o CPF: ");
				String cpf2 = scan.nextLine();
				
				System.out.printf("Informe o id do evento: ");
				Integer id2 = Integer.parseInt(scan.nextLine());
				
				update(name2, Long.parseLong(cpf2), id2);
				break;
			}
		}
	}

	public void save(String name, Long cpf, Integer eventId) {
		if (validation.isValidCpf(cpf) && validation.isValidEvent(eventId)) {
			Event event = guestService.getEvent(eventId);
			guestService.save(new Guest(name, cpf, event));
		}
	}

	public void update(String name, Long cpf, Integer eventId) {
		if (validation.isValidCpf(cpf) && validation.isValidEvent(eventId)) {
			Event event = guestService.getEvent(eventId);
			//informa se o CPF não for encontrado
			guestService.update(name, cpf, event);
		}
	}

	public void delete(Long cpf) {
		if (validation.isValidCpf(cpf)) {
			guestService.delete(cpf);
		}
	}

	public void listAll() {
		guestService.showAll();
	}
}
