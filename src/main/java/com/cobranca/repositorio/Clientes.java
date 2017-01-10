package com.cobranca.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobranca.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long>{

}
