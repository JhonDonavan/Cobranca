package com.cobranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobranca.model.Cliente;
import com.cobranca.repositorio.Clientes;
import com.cobranca.repositorio.filter.ClienteFilter;

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

	public List<Cliente> filtrarCliente(ClienteFilter filtro){
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();
		return clientes.findByNomeContaining(nome);
	}

	
	
	
	
	
	
}
