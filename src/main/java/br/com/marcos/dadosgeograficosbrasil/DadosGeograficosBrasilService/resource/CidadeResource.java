package br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.Cidade;
import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.repository.CidadeRepository;

/**
 * Controlador REST que mapeia a URI "/cidade" para ser processada
 * @author Marcos Rocha Júnior
 *
 */
@RestController
@RequestMapping("/cidade")
public class CidadeResource {
	
	/**
	 * Injeção de dependência para utilizar o repositório de Cidade
	 */
	@Autowired
	private CidadeRepository cidadeRepository;
	
	/**
	 * Processa requisições que utilizam o método GET do HTTP para retornar todas as cidades
	 * @return
	 */
	@GetMapping
	public List<Cidade> recuperarCidades(){
		return cidadeRepository.findAll();
	}

}
