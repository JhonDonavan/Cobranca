package com.cobranca.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobranca.model.Titulo;

public interface Titulos extends JpaRepository<Titulo, Long>{
	
	public List<Titulo> findByDescricaoContaining(String descricao);

}
