package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Event;

@Repository
public interface EventDAO extends PagingAndSortingRepository<Event, Integer> {
	
	Optional<Event> findById(String id);

}
