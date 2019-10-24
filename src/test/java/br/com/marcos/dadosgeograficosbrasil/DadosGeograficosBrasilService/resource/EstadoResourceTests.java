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

import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.Estado;
import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.Pais;

/**
 * Classe de teste para o controlador REST "EstadoResource"
 * @author Marcos Rocha Júnior
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EstadoResourceTests {
	
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
	 * Realiza teste para verificar se o serviço está retornando todos os estados
	 */
	@Test
	void testRecuperarEstados() {
		HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getUrlRaiz() + "/estado",
        HttpMethod.GET, entity, String.class);
 
        assertNotNull(response.getBody());
	}
	
	/**
	 * Realiza teste para verificar se o serviço está retornando um estado em específico
	 */
	@Test
	void testRecuperarEstado() {
		Estado estado = restTemplate.getForObject(getUrlRaiz() + "/estado/1", Estado.class);
        assertNotNull(estado);
	}
	
	/**
	 * Realiza teste para verificar se o serviço está inserindo estado
	 */
	@Test
	void testInserirEstado() {
        Pais pais = new Pais();
        pais.setCodigoIBGE(new Long(1));
        pais.setNome("Teste País");
        Estado estado = new Estado();
        estado.setCodigoIBGE(new Long(1));
        estado.setNome("Teste Estado");
        estado.setPais(pais);

        ResponseEntity<Estado> postResponse = restTemplate.postForEntity(getUrlRaiz() + "/estado", estado, Estado.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
	}
	
	/**
	 * Realiza teste para verificar se o serviço está atualizando estado 
	 */
	@Test
	void testAtualizarEstado() {
		Long codigoIBGE = new Long(1);
        Estado estado = restTemplate.getForObject(getUrlRaiz() + "/estado/" + codigoIBGE, Estado.class);
        estado.setNome("Novo nome");

        restTemplate.put(getUrlRaiz() + "/estado/" + codigoIBGE, estado);

        Estado estadoAtualizado = restTemplate.getForObject(getUrlRaiz() + "/estado/" + codigoIBGE, Estado.class);
        assertNotNull(estadoAtualizado);
	}
	
	/**
	 * Realiza teste para verificar se o serviço está removendo estado
	 */
	@Test
	void testRemoverEstado() {
		Long codigoIBGE = new Long(2);
        Estado estado = restTemplate.getForObject(getUrlRaiz() + "/estado/" + codigoIBGE, Estado.class);
        assertNotNull(estado);

        restTemplate.delete(getUrlRaiz() + "/estado/" + codigoIBGE);
   
        try {
             estado = restTemplate.getForObject(getUrlRaiz() + "/estado/" + codigoIBGE, Estado.class);
        } catch (final HttpClientErrorException e) {
        	assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
	}

}
