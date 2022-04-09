package com.edu.ifpbdanilo_costa.atv02.atividade02.model.Repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.edu.ifpbdanilo_costa.atv02.atividade02.model.Guest;

@Repository
public interface GuestDAO extends PagingAndSortingRepository<Guest, String> {

	Optional<Guest> findByCpf(Long cpf);
	
}
