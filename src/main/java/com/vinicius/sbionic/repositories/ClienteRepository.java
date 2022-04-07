package com.vinicius.sbionic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vinicius.sbionic.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	// Método findBy + sufixo(nome do campo na entidade)
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
}
