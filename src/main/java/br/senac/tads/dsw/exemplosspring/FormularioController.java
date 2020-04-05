package br.senac.tads.dsw.exemplosspring;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class FormularioController {
	
	//(Injeção de dependências)Essa anotação é para poder fazer uso da classe dados pessoais(simula banco de dados
	@Autowired
	private DadosPessoaisService service;
	
	@GetMapping("/lista")
	public ModelAndView listar() {
		return new ModelAndView("lista").addObject("dados", service.findAll());	
	}

	/*Esse método trás o formulário vazio
	@GetMapping("/formulario")
	public ModelAndView abrirForm() {
		return new ModelAndView("formulario").addObject("dados", new DadosPessoais());
	}*/
	
	
	//Trás o formulário com os campos preenchidos
	@GetMapping("/formulario")
	public ModelAndView abrirForm() {
		DadosPessoais dados = new DadosPessoais();
		dados.setId(124);
		dados.setNome("Beltrana da Silava");
		dados.setDescricao("Descrição da Beltrana da Silava");
		dados.setDtNascimento(LocalDate.of(2000, 4, 20));
		dados.setEmail("beltrana@teste.com");
		dados.setNumeroSorte(23);
		dados.setAltura(new BigDecimal("1.70"));
		dados.setPeso(new BigDecimal("70.9"));
		dados.setSenha("1234");
		dados.setSexo(0);
		dados.setInteresses(Arrays.asList("Tecnologia", "Esportes"));
		return new ModelAndView("formulario").addObject("dados", dados);
		}
	
	@PostMapping("/salvar")
	// @modelAtributte --> Popula automaticamente o objeto, evita que tenhamos que fazer manualmente
	public ModelAndView salvar(@ModelAttribute DadosPessoais dadosRecebidos) {
		
		//Salvar no BD
		service.save(dadosRecebidos);
		
		return new ModelAndView("resultado").addObject("dados", dadosRecebidos );
	}
	
	@PostMapping("/salvar-prg")
	public ModelAndView salvarPrg(@ModelAttribute DadosPessoais dadosRecebidos, RedirectAttributes redirAttr) {
		service.save(dadosRecebidos);
		redirAttr.addFlashAttribute("dados", dadosRecebidos);
		return new ModelAndView("redirect:/resultado-prg");
	}
	
	//Método para salvamento com validação dos campos
	@PostMapping("/salvar-validacao-prg")
	public ModelAndView salvarValidacaoPrg(
			@ModelAttribute("dados") @Valid DadosPessoais dadosRecebidos, 
			BindingResult bindingResult, 
			RedirectAttributes redirAttr) {
		
		//Codição para que seja validado os campos
		if(bindingResult.hasErrors()) {
			return new ModelAndView("formulario");
		}
		
		service.save(dadosRecebidos);
		redirAttr.addFlashAttribute("dados", dadosRecebidos);
		return new ModelAndView("redirect:/resultado-prg");
	}
	

	@GetMapping("/resultado-prg")
	public ModelAndView resultadoPrg() {
		return new ModelAndView("resultado");
	}
}

