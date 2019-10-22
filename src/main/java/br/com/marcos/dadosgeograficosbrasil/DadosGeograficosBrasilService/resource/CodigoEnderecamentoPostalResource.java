package br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.CodigoEnderecamentoPostal;
import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.repository.CodigoEnderecamentoPostalRepository;

/**
 * Controlador REST que mapeia a URI "/cep" para ser processada
 * @author Marcos Rocha Júnior
 *
 */
@RestController
@RequestMapping("/cep")
public class CodigoEnderecamentoPostalResource {

	/**
	 * Injeção de dependência para utilizar o repositório de CEP
	 */
	@Autowired
	private CodigoEnderecamentoPostalRepository cepRepository;
	
	/**
	 * Processa requisições que utilizam o método GET do HTTP para retornar todos os CEPs
	 * @return
	 */
	@GetMapping
	public List<CodigoEnderecamentoPostal> recuperarCeps(){
		return cepRepository.findAll();
	}
	
}
