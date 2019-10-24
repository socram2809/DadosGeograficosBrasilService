package br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.Cidade;
import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.Estado;
import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.Pais;

/**
 * Classe de teste para o controlador REST "CidadeResource"
 * @author Marcos Rocha Júnior
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CidadeResourceTests {
	
	/**
	 * Injeção de dependência para o template REST de testes
	 */
	@Autowired 
	private TestRestTemplate restTemplate;
	
	/**
	 * Porta do servidor local
	 */
	@LocalServerPort
	private int porta;
	
	/**
	 * Retorna a URL raiz da aplicação
	 * @return
	 */
	private String getUrlRaiz() {
		return "http://localhost:" + porta;
	}
	
	/**
	 * Realiza teste para verificar se o serviço está retornando todas as cidades
	 */
	@Test
	void testRecuperarCidades() {
		HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getUrlRaiz() + "/cidade",
        HttpMethod.GET, entity, String.class);
 
        assertNotNull(response.getBody());
	}
	
	/**
	 * Realiza teste para verificar se o serviço está retornando uma cidade em específico
	 */
	@Test
	void testRecuperarCidade() {
		Cidade cidade = restTemplate.getForObject(getUrlRaiz() + "/cidade/1", Cidade.class);
        assertNotNull(cidade);
	}
	
	/**
	 * Realiza teste para verificar se o serviço está inserindo cidade
	 */
	@Test
	void testInserirCidade() {
        Pais pais = new Pais();
        pais.setCodigoIBGE(new Long(1));
        pais.setNome("Teste País");
        Estado estado = new Estado();
        estado.setCodigoIBGE(new Long(1));
        estado.setNome("Teste Estado");
        estado.setPais(pais);
		Cidade cidade = new Cidade();
        cidade.setCodigoIBGE(new Long(1));
        cidade.setNome("Teste Cidade");
        cidade.setEstado(estado);

        ResponseEntity<Cidade> postResponse = restTemplate.postForEntity(getUrlRaiz() + "/cidade", cidade, Cidade.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
	}
	
	/**
	 * Realiza teste para verificar se o serviço está atualizando cidade 
	 */
	@Test
	void testAtualizarCidade() {
		Long codigoIBGE = new Long(1);
        Cidade cidade = restTemplate.getForObject(getUrlRaiz() + "/cidade/" + codigoIBGE, Cidade.class);
        cidade.setNome("Novo nome");

        restTemplate.put(getUrlRaiz() + "/cidade/" + codigoIBGE, cidade);

        Cidade cidadeAtualizada = restTemplate.getForObject(getUrlRaiz() + "/cidade/" + codigoIBGE, Cidade.class);
        assertNotNull(cidadeAtualizada);
	}
	
	/**
	 * Realiza teste para verificar se o serviço está removendo cidade
	 */
	@Test
	void testRemoverCidade() {
		Long codigoIBGE = new Long(2);
        Cidade cidade = restTemplate.getForObject(getUrlRaiz() + "/cidade/" + codigoIBGE, Cidade.class);
        assertNotNull(cidade);

        restTemplate.delete(getUrlRaiz() + "/cidade/" + codigoIBGE);
   
        try {
             cidade = restTemplate.getForObject(getUrlRaiz() + "/cidade/" + codigoIBGE, Cidade.class);
        } catch (final HttpClientErrorException e) {
        	assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
	}

}
