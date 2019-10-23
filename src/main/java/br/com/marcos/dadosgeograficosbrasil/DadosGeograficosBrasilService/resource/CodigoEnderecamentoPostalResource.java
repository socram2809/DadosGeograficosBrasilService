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
	
	/**
	 * Processa requisições, na URI "/list", que utilizam o método GET do HTTP para retornar uma consulta paginada de CEPs
	 * @param pagina
	 * @param tamanho
	 * @return
	 */
	@GetMapping("/list")
	public List<CodigoEnderecamentoPostal> recuperarCepsPaginado(@RequestParam(name="pagina") int pagina, @RequestParam(name="tamanho") int tamanho){
		Page<CodigoEnderecamentoPostal> paginaCep = cepRepository.findAll(PageRequest.of(pagina, tamanho));
		return paginaCep.getContent();
	}
	
	/**
	 * Processa requisições que utilizam o método GET do HTTP para retornar um cep a partir do seu número
	 * @param numeroCEP
	 * @return
	 */
	@GetMapping("/{numeroCEP}")
	public CodigoEnderecamentoPostal recuperarCep(@PathVariable Long numeroCEP) {
		return cepRepository.findById(numeroCEP).get();
	}
	
	/**
	 * Processa requisições que utilizam o método POST do HTTP para inserir um CEP
	 * @param cep
	 * @return
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CodigoEnderecamentoPostal inserirCep(@Valid @RequestBody CodigoEnderecamentoPostal cep) {
		return cepRepository.save(cep);
	}
	
	/**
	 * Processa requisições que utilizam o método PUT do HTTP para atualizar um CEP
	 * @param cep
	 * @return
	 */
	@PutMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public CodigoEnderecamentoPostal atualizarCep(@Valid @RequestBody CodigoEnderecamentoPostal cep) {
		return cepRepository.save(cep);
	}

	/**
	 * Processa requisições que utilizam o método DELETE do HTTP para remover um CEP ao buscar pelo seu número
	 * @param numeroCEP
	 * @return
	 */
	@DeleteMapping("/{numeroCEP}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removerCep(@PathVariable Long numeroCEP) {
		cepRepository.deleteById(numeroCEP);
	}

}
