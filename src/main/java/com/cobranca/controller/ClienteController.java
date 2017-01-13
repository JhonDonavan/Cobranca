package com.cobranca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String salvar(@Validated Cliente cliente, Errors erros, RedirectAttributes attributes) {
		if (erros.hasErrors()) {
			return "Cliente/CadastroCliente";
		}

		clientes.save(cliente);
		attributes.addFlashAttribute("mensagen", "Cliente salvo com sucesso");
		return "redirect:/clientes/novo";
	}
	
	@RequestMapping
	public ModelAndView pesquisar(){
		List<Cliente> todosClientes = clientes.findAll();
		ModelAndView mv = new ModelAndView("Cliente/PesquisaClientes");
		mv.addObject("clientes", todosClientes);
		return mv;
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
	

}
