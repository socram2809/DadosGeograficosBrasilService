package br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	/**
	 * Processa requisições, na URI "/list", que utilizam o método GET do HTTP para retornar uma consulta paginada de estados
	 * @param pagina
	 * @param tamanho
	 * @return
	 */
	@GetMapping("/list")
	public List<Estado> recuperarEstadosPaginado(@RequestParam(name="pagina") int pagina, @RequestParam(name="tamanho") int tamanho){
		Page<Estado> paginaEstado = estadoRepository.findAll(PageRequest.of(pagina, tamanho));
		return paginaEstado.getContent();
	}
	
	/**
	 * Processa requisições que utilizam o método GET do HTTP para retornar um estado a partir do seu código do IBGE
	 * @param codigoIBGEEstado
	 * @return
	 */
	@GetMapping("/{codigoIBGEEstado}")
	public Estado recuperarEstado(@PathVariable Long codigoIBGEEstado) {
		return estadoRepository.findById(codigoIBGEEstado).get();
	}
	
	/**
	 * Processa requisições que utilizam o método POST do HTTP para inserir um estado
	 * @param estado
	 * @return
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Estado inserirEstado(@Valid @RequestBody Estado estado) {
		return estadoRepository.save(estado);
	}
	
	/**
	 * Processa requisições que utilizam o método PUT do HTTP para atualizar um estado
	 * @param estado
	 * @return
	 */
	@PutMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public Estado atualizarEstado(@Valid @RequestBody Estado estado) {
		return estadoRepository.save(estado);
	}

	/**
	 * Processa requisições que utilizam o método DELETE do HTTP para remover um estado ao buscar pelo código do IBGE
	 * @param codigoIBGEEstado
	 * @return
	 */
	@DeleteMapping("/{codigoIBGEEstado}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removerEstado(@PathVariable Long codigoIBGEEstado) {
		estadoRepository.deleteById(codigoIBGEEstado);
	}
	
	/**
	 * Processa requisições, na URI "/search", que utilizam o método GET do HTTP para buscar estados pelo nome
	 * @param nome
	 * @return
	 */
	@GetMapping("/search")
	public List<Estado> recuperarEstadosComFiltros(@RequestParam(name="nome") String nome){
		return estadoRepository.findByNomeContainingIgnoreCase(nome);
	}

}
