package br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio.Estado;

/**
 * Interface que estende JpaRepository, sendo possível utilizar as operações CRUD para Estado
 * @author Marcos Rocha Júnior
 *
 */
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
