package br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.Pais;

/**
 * Interface que estende JpaRepository, sendo possível utilizar as operações CRUD para País
 * @author Marcos Rocha Júnior
 *
 */
public interface PaisRepository extends JpaRepository<Pais, Long> {

}
