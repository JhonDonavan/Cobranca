package com.cobranca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cobranca.model.Cliente;
import com.cobranca.repositorio.Clientes;
import com.cobranca.repositorio.filter.ClienteFilter;
import com.cobranca.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private Clientes clientes;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("/Cliente/Cadastrocliente");
		mv.addObject(new Cliente());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Cliente cliente, Errors erros, RedirectAttributes attributes) {
		if (erros.hasErrors()) {
			return "Cliente/CadastroCliente";
		}

		try {
			clienteService.salvar(cliente);
			attributes.addFlashAttribute("mensagen", "Cliente cadastrado com sucesso");
			return "redirect:/clientes/novo";
		} catch (DataIntegrityViolationException e) {
			erros.rejectValue("dataNascimento", null, "Formato de data invalido");
			return "Cliente/CadastroCliente";
		}
	}
	
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") ClienteFilter filtro){
		List<Cliente> todosClientes = clienteService.filtrarCliente(filtro);
		ModelAndView mv = new ModelAndView("Cliente/PesquisaClientes");
		mv.addObject("clientes", todosClientes);
		return mv;
	}
	
	@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody List<Cliente> pesquisar(String nome){
		return clientes.findByNomeStartingWithIgnoreCase(nome);
	} 
	
	//OBS: O spring entende que se passar uma variavel ele interpreta que voce quer localizar o objeto
	//sendo assim, não ha necessidade desta forma codificada abaixo de usar FindAll do JPA.
	//Passando  "id" e declarando o objeto ele faz automaticamente
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Cliente cliente){
		ModelAndView mv = new ModelAndView("Cliente/Cadastrocliente");
		mv.addObject(cliente);
		return mv;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes){
		clienteService.Excluir(id);
		attributes.addFlashAttribute("mensagen", "Cliente excluido com sucesso");
		return "redirect:/clientes";
	}
	
	
	

}
