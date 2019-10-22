package br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

/**
 * Classe de domínio que representa um país
 * @author Marcos Rocha Júnior
 *
 */
@Entity
public class Pais {

	/**
	 * Código do IBGE que identifica o país
	 */
	@Id
	private Long codigoIBGE;
	
	/**
	 * Nome do país
	 */
	@NotEmpty
	private String nome;

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
	
}
