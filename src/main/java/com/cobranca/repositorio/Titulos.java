package com.cobranca.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobranca.model.Titulo;

public interface Titulos extends JpaRepository<Titulo, Long>{
	

}
