package com.cobranca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cobranca.model.StatusTitulo;
import com.cobranca.model.Titulo;
import com.cobranca.repositorio.Titulos;
import com.cobranca.service.TituloService;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired
	private Titulos titulos;
	
	@Autowired
	private TituloService tituloService;

	@RequestMapping("novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("Titulo/CadastroTitulos");
		mv.addObject(new Titulo());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors erros, RedirectAttributes attributes) {
		if (erros.hasErrors()) {
			return "Titulo/CadastroTitulos";
		}

		try {
			tituloService.salvar(titulo);
			attributes.addFlashAttribute("mensagen", "Titulo salvo com sucesso");
			return "redirect:/titulos/novo";
		} catch (DataIntegrityViolationException e) {
			erros.rejectValue("dataVencimento", null, "Formato de data invalido");
			return "Titulo/CadastroTitulos";
		}
	}

	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> TodosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("Titulo/PesquisaTitulos");
		mv.addObject("titulos", TodosTitulos);
		return mv;
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable Long id) {
		Titulo titulo = titulos.findOne(id);

		ModelAndView mv = new ModelAndView("Titulo/CadastroTitulos");
		mv.addObject(titulo);
		return mv;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		tituloService.excluir(id);
		attributes.addFlashAttribute("mensagen", "Titulo excluido com sucesso");
		return "redirect:/titulos";
	}

	// retorna uma lista de opçaoes cadastradas na classe ENUM StatusTitulo para
	// preenchimento dinamico do campo status na tela Cadastro de titulos
	@ModelAttribute("todosStatusTitulos")
	public StatusTitulo[] todosStatusTitulos() {
		return StatusTitulo.values();
	}
}
