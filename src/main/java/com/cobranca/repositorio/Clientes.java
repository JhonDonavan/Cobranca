package com.cobranca.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobranca.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long>{
	
	public List<Cliente> findByNomeContaining(String nome);

}
