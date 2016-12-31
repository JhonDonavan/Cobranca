package com.cobranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cobranca.model.Titulo;
import com.cobranca.repositorio.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private Titulos titulos;
	
	@RequestMapping("/novo")
	public String novo(){
		return "CadastroTitulos"; 	
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Titulo titulo){
		
		titulos.save(titulo);
		
		return "CadastroTitulos";
	}

}
