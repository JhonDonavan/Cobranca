package com.cobranca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobranca.model.Cliente;
import com.cobranca.repositorio.Clientes;

@Service
public class ClienteService {

	@Autowired
	private Clientes clientes;

	public void salvar(Cliente cliente) {
		clientes.save(cliente);
	}

	public void Excluir(Long id) {
		clientes.delete(id);
	}
	
	
	
	
	
}
