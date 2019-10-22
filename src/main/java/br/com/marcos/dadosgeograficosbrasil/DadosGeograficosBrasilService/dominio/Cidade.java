package br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

/**
 * Classe de domínio que representa uma cidade
 * @author Marcos Rocha Júnior
 *
 */
@Entity
public class Cidade {
	
	/**
	 * Código do IBGE que identifica a cidade
	 */
	@Id
	private Long codigoIBGE;
	
	/**
	 * Nome da cidade
	 */
	@NotEmpty
	private String nome;
	
	/**
	 * Estado da cidade
	 */
	@ManyToOne
	private Estado estado;

	public Long getCodigoIBGE() {
		return codigoIBGE;
	}

	public void setCodigoIBGE(Long codigoIBGE) {
		this.codigoIBGE = codigoIBGE;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
