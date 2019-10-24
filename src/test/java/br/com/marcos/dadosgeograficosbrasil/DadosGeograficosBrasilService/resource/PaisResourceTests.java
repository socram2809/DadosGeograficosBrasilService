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

import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.Pais;

/**
 * Classe de teste para o controlador REST "PaisResource"
 * @author Marcos Rocha Júnior
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PaisResourceTests {
	
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
	 * Realiza teste para verificar se o serviço está retornando todos os países
	 */
	@Test
	void testRecuperarPaises() {
		HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getUrlRaiz() + "/pais",
        HttpMethod.GET, entity, String.class);
 
        assertNotNull(response.getBody());
	}
	
	/**
	 * Realiza teste para verificar se o serviço está retornando um país em específico
	 */
	@Test
	void testRecuperarPais() {
		Pais pais = restTemplate.getForObject(getUrlRaiz() + "/pais/1", Pais.class);
        assertNotNull(pais);
	}
	
	/**
	 * Realiza teste para verificar se o serviço está inserindo país
	 */
	@Test
	void testInserirPais() {
        Pais pais = new Pais();
        pais.setCodigoIBGE(new Long(1));
        pais.setNome("Teste País");

        ResponseEntity<Pais> postResponse = restTemplate.postForEntity(getUrlRaiz() + "/pais", pais, Pais.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
	}
	
	/**
	 * Realiza teste para verificar se o serviço está atualizando país 
	 */
	@Test
	void testAtualizarPais() {
		Long codigoIBGE = new Long(1);
        Pais pais = restTemplate.getForObject(getUrlRaiz() + "/pais/" + codigoIBGE, Pais.class);
        pais.setNome("Novo nome");

        restTemplate.put(getUrlRaiz() + "/pais/" + codigoIBGE, pais);

        Pais paisAtualizado = restTemplate.getForObject(getUrlRaiz() + "/pais/" + codigoIBGE, Pais.class);
        assertNotNull(paisAtualizado);
	}
	
	/**
	 * Realiza teste para verificar se o serviço está removendo país
	 */
	@Test
	void testRemoverPais() {
		Long codigoIBGE = new Long(2);
        Pais pais = restTemplate.getForObject(getUrlRaiz() + "/pais/" + codigoIBGE, Pais.class);
        assertNotNull(pais);

        restTemplate.delete(getUrlRaiz() + "/pais/" + codigoIBGE);
   
        try {
             pais = restTemplate.getForObject(getUrlRaiz() + "/pais/" + codigoIBGE, Pais.class);
        } catch (final HttpClientErrorException e) {
        	assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
	}

}
