package br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.Cidade;

/**
 * Interface que estende JpaRepository, sendo possível utilizar as operações CRUD para Cidade
 * @author Marcos Rocha Júnior
 *
 */
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
