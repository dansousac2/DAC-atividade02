package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
	
	Optional<Event> findById(String id);

}
