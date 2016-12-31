package com.cobranca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cobranca.model.Titulo;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@RequestMapping("/novo")
	public String novo(){
		return "CadastroTitulos"; 	
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Titulo titulo){
		//TODO: salvar no banco de dados
		System.out.println(">>> " + titulo.getDescricao());
		return "CadastroTitulos";
	}

}
