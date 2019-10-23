package br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.Cidade;
import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.CodigoEnderecamentoPostal;
import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.Estado;
import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.Pais;
import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.repository.CidadeRepository;
import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.repository.CodigoEnderecamentoPostalRepository;
import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.repository.EstadoRepository;
import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.repository.PaisRepository;

/**
 * Controlador REST que mapeia a URI "/cidade" para ser processada
 * @author Marcos Rocha Júnior
 *
 */
@RestController
@RequestMapping("/cidade")
public class CidadeResource {
	
	/**
	 * Código do IBGE para o Brasil
	 */
	private static long CODIGO_IBGE_BRASIL = 1058;
	
	/**
	 * Nome do Brasil
	 */
	private static String NOME_BRASIL = "Brasil";
	
	/**
	 * Injeção de dependência para utilizar o repositório de Cidade
	 */
	@Autowired
	private CidadeRepository cidadeRepository;
	
	/**
	 * Injeção de dependência para utilizar o repositório de Estado
	 */
	@Autowired
	private EstadoRepository estadoRepository;
	
	/**
	 * Injeção de dependência para utilizar o repositório de País
	 */
	@Autowired
	private PaisRepository paisRepository;
	
	/**
	 * Injeção de dependência para utilizar o repositório de CEP
	 */
	@Autowired
	private CodigoEnderecamentoPostalRepository cepRepository;
	
	/**
	 * Processa requisições que utilizam o método GET do HTTP para retornar todas as cidades
	 * @return
	 */
	@GetMapping
	public List<Cidade> recuperarCidades(){
		return cidadeRepository.findAll();
	}
	
	/**
	 * Processa requisições, na URI "/list", que utilizam o método GET do HTTP para retornar uma consulta paginada de cidades
	 * @param pagina
	 * @param tamanho
	 * @return
	 */
	@GetMapping("/list")
	public List<Cidade> recuperarCidadesPaginado(@RequestParam(name="pagina") int pagina, @RequestParam(name="tamanho") int tamanho){
		Page<Cidade> paginaCidade = cidadeRepository.findAll(PageRequest.of(pagina, tamanho));
		return paginaCidade.getContent();
	}
	
	/**
	 * Processa requisições que utilizam o método GET do HTTP para retornar uma cidade a partir do seu código do IBGE
	 * @param codigoIBGECidade
	 * @return
	 */
	@GetMapping("/{codigoIBGECidade}")
	public Cidade recuperarCidade(@PathVariable Long codigoIBGECidade) {
		return cidadeRepository.findById(codigoIBGECidade).get();
	}
	
	/**
	 * Processa requisições que utilizam o método POST do HTTP para inserir uma cidade
	 * @param cidade
	 * @return
	 */
	@PostMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cidade inserirCidade(@Valid @RequestBody Cidade cidade) {
		return cidadeRepository.save(cidade);
	}
	
	/**
	 * Processa requisições que utilizam o método PUT do HTTP para atualizar uma cidade
	 * @param cidade
	 * @return
	 */
	@PutMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public Cidade atualizarCidade(@Valid @RequestBody Cidade cidade) {
		return cidadeRepository.save(cidade);
	}

	/**
	 * Processa requisições que utilizam o método DELETE do HTTP para remover uma cidade ao buscar pelo código do IBGE
	 * @param codigoIBGECidade
	 * @return
	 */
	@DeleteMapping("/{codigoIBGECidade}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removerCidade(@PathVariable Long codigoIBGECidade) {
		cidadeRepository.deleteById(codigoIBGECidade);
	}
	
	/**
	 * Processa requisições, na URI "/search", que utilizam o método GET do HTTP para buscar cidades pelo nome
	 * @param nome
	 * @return
	 */
	@GetMapping("/search")
	public List<Cidade> recuperarCidadesComFiltros(@RequestParam(name="nome") String nome){
		return cidadeRepository.findByNomeContainingIgnoreCase(nome);
	}
	
	/**
	 * Processa requisições, na URI "/buscaPorCep", que utilizam o método GET do HTTP para buscar cidades pelo CEP
	 * @param numeroCEP
	 * @return
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 */
	@GetMapping("/buscaPorCep")
	public List<Cidade> recuperarCidadePeloCep(@RequestParam(name="numeroCEP") Long numeroCEP) throws JsonMappingException, JsonProcessingException{
		List<Cidade> cidades = cidadeRepository.findByCepsNumeroCEP(numeroCEP);
		if((cidades != null && cidades.isEmpty()) || cidades == null) {
			Cidade cidade = consomeViaCepAPI(numeroCEP);
			//Caso tenha encontrado a cidade, ela deve ser retornada para o usuário
			if(cidade != null) {
				cidades = new ArrayList<>();
				cidade.setCeps(cepRepository.findByCodigoIBGECidade(cidade.getCodigoIBGE()));
				cidades.add(cidade);
			}
		}
		return cidades;
	}

	/**
	 * Consome a API do Via CEP
	 * @param numeroCEP
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 * @return
	 */
	private Cidade consomeViaCepAPI(Long numeroCEP) throws JsonProcessingException, JsonMappingException {
		RestTemplate restTemplate = new RestTemplate();
		Cidade cidade = new Cidade();

		String viacepUrl = "https://viacep.com.br/ws/" + numeroCEP + "/json/";
		ResponseEntity<String> resposta = restTemplate.getForEntity(viacepUrl, String.class);
		if(resposta != null && resposta.getStatusCode().equals(HttpStatus.OK)) {
			//Lê o corpo da resposta do Web Service "viacep.com.br"
			ObjectMapper mapper = new ObjectMapper();
			JsonNode raiz = mapper.readTree(resposta.getBody());
			JsonNode logradouro = raiz.path("logradouro");
			JsonNode codigoIBGE = raiz.path("ibge");
			JsonNode uf = raiz.path("uf");
			JsonNode localidade = raiz.path("localidade");
			
			//Cria a cidade
			criaCidade(numeroCEP, logradouro, codigoIBGE, uf, localidade, cidade);
		}
		
		return cidade;
	}

	/**
	 * Insere registro da cidade no banco de dados 
	 * @param numeroCEP
	 * @param logradouro
	 * @param codigoIBGE
	 * @param uf
	 * @param localidade
	 */
	private void criaCidade(Long numeroCEP, JsonNode logradouro, JsonNode codigoIBGE, JsonNode uf,
			JsonNode localidade, Cidade cidade) {
		//Salva o país
		Pais pais = new Pais();
		pais.setCodigoIBGE(CODIGO_IBGE_BRASIL);
		pais.setNome(NOME_BRASIL);
		pais = paisRepository.save(pais);
		
		//Salva o estado
		Estado estado = new Estado();
		estado.setCodigoIBGE(Long.valueOf(codigoIBGE.asText().substring(0, 2)));
		estado.setNome(uf.asText());
		estado.setPais(pais);
		estado = estadoRepository.save(estado);
		
		//Salva a cidade
		cidade.setCodigoIBGE(Long.valueOf(codigoIBGE.asText()));
		cidade.setEstado(estado);
		cidade.setNome(localidade.asText());
		cidade = cidadeRepository.save(cidade);
		
		//Salva o CEP
		CodigoEnderecamentoPostal cep = new CodigoEnderecamentoPostal();
		cep.setNumeroCEP(numeroCEP);
		cep.setLogradouro(logradouro.asText());
		cep.setCodigoIBGECidade(cidade.getCodigoIBGE());
		cep = cepRepository.save(cep);
	}
	
	/**
	 * Processa requisições, na URI "/retornaTrajeto", que utilizam o método GET do HTTP para retornar uma lista de cidades a partir 
	 * de uma lista de CEPs
	 * @param numeroCEPs
	 * @return
	 */
	@GetMapping("/retornaTrajeto")
	public List<Cidade> retornarTrajetoCidadesPelosCeps(@RequestParam(name = "numeroCEPs") List<Long> numeroCEPs){
		List<Cidade> cidades = cidadeRepository.findByCepsNumeroCEPIn(numeroCEPs, 
				numeroCEPs.stream().map(Object::toString).collect(Collectors.joining(",")));
		List<Cidade> cidadesSemRepeticoesSeq = new ArrayList<>();
		
		if(cidades != null && !cidades.isEmpty()) {
			//não repetir cidades em sequência
			cidadesSemRepeticoesSeq = IntStream
		            .range(0, cidades.size())
		            .filter(i -> ((i < cidades.size() - 1 && !cidades.get(i).equals(cidades
		                    .get(i + 1))) || i == cidades.size() - 1))
		            .mapToObj(i -> cidades.get(i)).collect(Collectors.toList());
		}
		
		return cidadesSemRepeticoesSeq;
	}

}
