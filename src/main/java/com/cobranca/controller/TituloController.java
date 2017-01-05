package com.cobranca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
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
		mv.addObject(new Titulo());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Titulo titulo, Errors erros) {
		ModelAndView mv = new ModelAndView("CadastroTitulos");

		if (erros.hasErrors()) {
			return mv;
		}

		titulos.save(titulo);

		mv.addObject("mensagen", "Titulo salvo com sucesso");
		return mv;
	}

	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> TodosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", TodosTitulos);
		return mv;
	}

	// retorna uma lista de opçaoes cadastradas na classe ENUM StatusTitulo para
	// preenchimento dinamico do campo status na tela Cadastro de titulos
	@ModelAttribute("todosStatusTitulos")
	public StatusTitulo[] todosStatusTitulos() {
		return StatusTitulo.values();
	}

}
