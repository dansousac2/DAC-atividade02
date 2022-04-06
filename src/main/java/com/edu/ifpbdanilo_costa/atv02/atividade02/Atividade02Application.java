package com.edu.ifpbdanilo_costa.atv02.atividade02;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Controller.EventController;
import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Controller.GuestController;

@SpringBootApplication
public class Atividade02Application implements CommandLineRunner{

	@Autowired
	private GuestController guestController;
	@Autowired
	private EventController eventController;
	
	public static void main(String[] args) {
		SpringApplication.run(Atividade02Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		String action = "";
		while(action != "0") {
			System.out.println("\nIndique o que deseja fazer: "
					+ "\n[0] Encerrar"
					+ "\n[1] Acessar Menu de Convidados"
					+ "\n[2] Acessar Menu de Eventos");
			
			System.out.printf("=> ");
			action = scan.nextLine();
			
			if(action.equals("1")) {
				String resp = "";
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
						System.out.println("\n= = Cadastrar convidado = =");
						
						System.out.printf("Informe o nome do convidado: ");
						String name = scan.nextLine();
						
						System.out.printf("Informe o CPF: ");
						String cpf = scan.nextLine();
						
						System.out.printf("Informe o id do evento: ");
						Integer id = Integer.parseInt(scan.nextLine());
						
						guestController.save(name, Long.parseLong(cpf), id);
						break;
						
					case "2":
						System.out.println("\n= = Atualizar convidado existente = =");
						
						System.out.printf("Informe o nome do convidado: ");
						String name2 = scan.nextLine();
						
						System.out.printf("Informe o CPF: ");
						String cpf2 = scan.nextLine();
						
						System.out.printf("Informe o id do evento: ");
						Integer id2 = Integer.parseInt(scan.nextLine());
						
						guestController.update(name2, Long.parseLong(cpf2), id2);
						break;
					
					case "3":
						System.out.println("\n= = Deletar convidado existente = =");
						
						System.out.printf("Informe o CPF: ");
						String cpf3 = scan.nextLine();
						
						guestController.delete(Long.parseLong(cpf3));
						break;
					
					case "4":
						System.out.println("\n= = Listar convidados = =");
						guestController.listAll();
						break;
						
					case "0":
						resp = "0";
						break;
					}
				}
			} else if(action.equals("2")) {
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
						
						eventController.save(name, date, adress);
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
						
						eventController.update(Integer.parseInt(id2), name2, date2, adress2);
						break;
						
					case "3":
						System.out.println("\n= = Exclusão de evento = =\n");
						
						System.out.printf("Informe o ID do evento: ");
						String id3 = scan.nextLine();
						
						eventController.delete(Integer.parseInt(id3));
						break;
						
					case "4":
						System.out.println("\n= = Listagem de eventos = =\n");
						eventController.listAll();
						break;
						
					case "0":
						resp = "0";
						break;
					}
					
				}
			} else if(action.equals("0")) {
				scan.close();
				break;
			}
		}
	}
	
}
