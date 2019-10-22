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

import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.Pais;
import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.repository.PaisRepository;

/**
 * Controlador REST que mapeia a URI "/pais" para ser processada
 * @author Marcos Rocha Júnior
 *
 */
@RestController
@RequestMapping("/pais")
public class PaisResource {
	
	/**
	 * Injeção de dependência para utilizar o repositório de País
	 */
	@Autowired
	private PaisRepository paisRepository;
	
	/**
	 * Processa requisições que utilizam o método GET do HTTP para retornar todos os países
	 * @return
	 */
	@GetMapping
	public List<Pais> recuperarPaises(){
		return paisRepository.findAll();
	}
	
	/**
	 * Processa requisições, na URI "/list", que utilizam o método GET do HTTP para retornar uma consulta paginada de países
	 * @param pagina
	 * @param tamanho
	 * @return
	 */
	@GetMapping("/list")
	public List<Pais> recuperarPaisesPaginado(@RequestParam(name="pagina") int pagina, @RequestParam int tamanho){
		Page<Pais> paginaPais = paisRepository.findAll(PageRequest.of(pagina, tamanho));
		return paginaPais.getContent();
	}
	
	/**
	 * Processa requisições que utilizam o método GET do HTTP para retornar um país a partir do seu código do IBGE
	 * @param codigoIBGEPais
	 * @return
	 */
	@GetMapping("/{codigoIBGEPais}")
	public Pais recuperarPais(@PathVariable Long codigoIBGEPais) {
		return paisRepository.findById(codigoIBGEPais).get();
	}
	
	/**
	 * Processa requisições que utilizam o método POST do HTTP para inserir um país
	 * @param pais
	 * @return
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Pais inserirPais(@Valid @RequestBody Pais pais) {
		return paisRepository.save(pais);
	}
	
	/**
	 * Processa requisições que utilizam o método PUT do HTTP para atualizar um país
	 * @param pais
	 * @return
	 */
	@PutMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public Pais atualizarPais(@Valid @RequestBody Pais pais) {
		return paisRepository.save(pais);
	}

	/**
	 * Processa requisições que utilizam o método DELETE do HTTP para remover um país ao buscar pelo código do IBGE
	 * @param pais
	 * @return
	 */
	@DeleteMapping("/{codigoIBGEPais}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removerPais(@PathVariable Long codigoIBGEPais) {
		paisRepository.deleteById(codigoIBGEPais);
	}
}
