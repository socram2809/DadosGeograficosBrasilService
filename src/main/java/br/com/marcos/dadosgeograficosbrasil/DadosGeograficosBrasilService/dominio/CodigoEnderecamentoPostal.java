package br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Classe de domínio que representa um Código de Endereçamento Postal (CEP) 
 * @author Marcos Rocha Júnior
 *
 */
@Entity
public class CodigoEnderecamentoPostal {
	
	/**
	 * Identificador do CEP
	 */
	@Id
	private Long numeroCEP;
	
	/**
	 * Logradouro
	 */
	@NotEmpty
	private String logradouro;
	
	/**
	 * Código do IBGE da cidade
	 */
	@NotNull
	private Long codigoIBGECidade;

	public Long getNumeroCEP() {
		return numeroCEP;
	}

	public void setNumeroCEP(Long numeroCEP) {
		this.numeroCEP = numeroCEP;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Long getCodigoIBGECidade() {
		return codigoIBGECidade;
	}

	public void setCodigoIBGECidade(Long codigoIBGECidade) {
		this.codigoIBGECidade = codigoIBGECidade;
	}

}
