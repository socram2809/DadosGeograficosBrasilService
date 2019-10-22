package br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.Estado;
import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.repository.EstadoRepository;

/**
 * Controlador REST que mapeia a URI "/estado" para ser processada
 * @author Marcos Rocha Júnior
 *
 */
@RestController
@RequestMapping("/estado")
public class EstadoResource {
	
	/**
	 * Injeção de dependência para utilizar o repositório de Estado
	 */
	@Autowired
	private EstadoRepository estadoRepository;
	
	/**
	 * Processa requisições que utilizam o método GET do HTTP para retornar todos os estados
	 * @return
	 */
	@GetMapping
	public List<Estado> recuperarEstados(){
		return estadoRepository.findAll();
	}

}
