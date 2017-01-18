package com.cobranca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobranca.model.StatusTitulo;
import com.cobranca.model.Titulo;
import com.cobranca.repositorio.Titulos;

@Service
public class TituloService {

	@Autowired
	private Titulos titulos;
	
	public void salvar(Titulo titulo){
		titulos.save(titulo);
	}

	public void excluir(Long id) {
		titulos.delete(id);
	}

	public String receber(Long id) {
		Titulo titulo = titulos.findOne(id);
		titulo.setStatus(StatusTitulo.RECEBIDO);
		titulos.save(titulo);
		
		return StatusTitulo.RECEBIDO.getDescricao();
	}
}
