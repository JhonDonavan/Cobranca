package com.cobranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cobranca.model.Cliente;
import com.cobranca.repositorio.Clientes;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private Clientes clientes;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("/Cliente/Cadastrocliente");
		mv.addObject(new Cliente());
		return mv;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Cliente cliente){
		clientes.save(cliente);
		return "redirect:/clientes/novo";
	}
	
}
