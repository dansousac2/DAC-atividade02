package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service.EventService;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Service.ValidationService;

@Controller
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private ValidationService validation;
	
	public void menu(Scanner scan) {
		String resp = "3";
		while (resp != "0") {
			System.out.println("\nIndique o que deseja fazer:" 
					+ "\n[0] Voltar ao menu anterior"
					+ "\n[1] Cadastrar evento"
					+ "\n[2] Atualizar evento existente"
					+ "\n[3] Deletar evento existente" 
					+ "\n[4] Listar todos os eventos");

			System.out.printf("=> ");
			resp = scan.nextLine();

			switch (resp) {
			case "1":
				System.out.println("\n= = Cadastro de evento = =\n");
				
				System.out.printf("Informe o nome do evento: ");
				String name = scan.nextLine() ;
				
				System.out.printf("Informe a data do evento (dd/MM/yyyy): ");
				String date = scan.nextLine();
				
				System.out.printf("Informe o endereço do evento: ");
				String adress = scan.nextLine();
				
				save(name, date, adress);
				break;
				
			case "2":
				System.out.println("\n= = Atualização de evento = =\n");
				
				System.out.printf("Informe o ID do evento: ");
				String id2 = scan.nextLine();
				
				System.out.printf("Informe o nome do evento: ");
				String name2 = scan.nextLine();
				
				System.out.printf("Informe a data do evento (dd/MM/yyyy): ");
				String date2 = scan.nextLine();
				
				System.out.printf("Informe o endereço do evento: ");
				String adress2 = scan.nextLine();
				
				update(Integer.parseInt(id2), name2, date2, adress2);
				break;
				
			case "3":
				System.out.println("\n= = Exclusão de evento = =\n");
				
				System.out.printf("Informe o ID do evento: ");
				String id3 = scan.nextLine();
				
				delete(Integer.parseInt(id3));
				break;
				
			case "4":
				System.out.println("\n= = Listagem de eventos = =\n");
				listAll();
				break;
			}
			
		}
	}
	
	public void save(String name, String date, String adress) {
		if(validation.validateDate(date)) {
			eventService.save(new Event(name, date, adress));
		}
	}
	
	public void update(Integer id, String name, String date, String adress) {
		if(validation.validateDate(date)) {
			//informa se o id não for encontrado
			eventService.update(id, name, date, adress);
		}
	}
	
	public void delete(Integer id) {
		//informa se o id não for encontrado
		eventService.delete(id);
	}
	
	public void listAll() {
		eventService.showAll();
	}

}
