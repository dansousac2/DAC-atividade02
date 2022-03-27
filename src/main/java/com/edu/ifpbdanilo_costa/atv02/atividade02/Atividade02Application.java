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
	private GuestController guesController;
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
				guesController.menu(scan);
			} else if(action.equals("2")) {
				eventController.menu(scan);
			} else if(action.equals("0")) {
				break;
			}
		}
	}
	
}
