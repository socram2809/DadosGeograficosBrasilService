package br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.Cidade;

/**
 * Interface que estende JpaRepository, sendo possível utilizar as operações CRUD para Cidade
 * @author Marcos Rocha Júnior
 *
 */
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	/**
	 * Realiza a busca de cidade pelo filtro de nome
	 * @param nome
	 * @return
	 */
	public List<Cidade> findByNomeContainingIgnoreCase(String nome);
	
	/**
	 * Realiza a busca de cidade por CEP
	 * @param numeroCEP
	 * @return
	 */
	public List<Cidade> findByCepsNumeroCEP(Long numeroCEP);

}
