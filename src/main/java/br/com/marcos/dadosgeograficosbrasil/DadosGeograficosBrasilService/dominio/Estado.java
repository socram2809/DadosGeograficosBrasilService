package br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Classe de domínio que representa um estado
 * @author Marcos Rocha Júnior
 *
 */
@Entity
public class Estado {
	
	/**
	 * Código do IBGE que identifica o estado
	 */
	@Id
	private Long codigoIBGE;
	
	/**
	 * Nome do estado
	 */
	@NotEmpty
	private String nome;
	
	/**
	 * País do estado
	 */
	@NotNull
	@ManyToOne
	private Pais pais;

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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}
