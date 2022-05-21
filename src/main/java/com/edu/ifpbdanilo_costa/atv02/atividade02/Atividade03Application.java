package com.edu.ifpbdanilo_costa.atv02.atividade02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
public class Atividade03Application implements WebMvcConfigurer {
	
	public static void main(String[] args) {
		SpringApplication.run(Atividade03Application.class, args);
	}
	
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/**")
			.allowedMethods("GET","POST", "PUT", "DELETE", "OPTIONS", "PATCH");
	}
	
}
