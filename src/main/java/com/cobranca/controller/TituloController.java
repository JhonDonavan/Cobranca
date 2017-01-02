package com.cobranca.controller;

import java.util.List;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cobranca.model.StatusTitulo;
import com.cobranca.model.Titulo;
import com.cobranca.repositorio.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired
	private Titulos titulos;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroTitulos");
		/*mv.addObject("todosStatusTitulo", StatusTitulo.values());*/
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		titulos.save(titulo);
		ModelAndView mv = new ModelAndView("CadastroTitulos");
		/*mv.addObject("todosStatusTitulo", StatusTitulo.values());*/
		mv.addObject("mensagen", "Titulo salvo com sucesso");
		return mv;
	}
	
	
	//retorna uma lista de opçaoes cadastradas na classe ENUM StatusTitulo para 
	//preenchimento dinamico do campo status na tela Cadastro de titulos
	@ModelAttribute("todosStatusTitulos")
	public StatusTitulo[] todosStatusTitulos(){
		return StatusTitulo.values();
	}

}
