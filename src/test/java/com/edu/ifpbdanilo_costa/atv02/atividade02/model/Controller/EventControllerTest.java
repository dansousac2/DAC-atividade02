package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest //testa usando a aplicação de fato!
class EventControllerTest {

	@Autowired
	private EventController controller;
	
	@Test
	void test() {
		controller.findById(1);
	}

}
