package br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	
	/**
	 * Realiza a busca de cidades pelo CEP
	 * @param numeroCEPs
	 * @param numeroCEPsString
	 * @return
	 */
	@Query(value = "select cid from Cidade cid join cid.ceps cep where cep.numeroCEP in :numeroCEPs "
			     + "order by POSITION(cep.numeroCEP, :numeroCEPsString)")
	public List<Cidade> findByCepsNumeroCEPIn(@Param("numeroCEPs") List<Long> numeroCEPs, @Param("numeroCEPsString") String numeroCEPsString);

}
