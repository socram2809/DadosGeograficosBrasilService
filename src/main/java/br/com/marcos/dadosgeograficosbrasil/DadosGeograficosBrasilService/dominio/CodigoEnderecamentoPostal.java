package br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Número do CEP
	 */
	@NotEmpty
	private Long numeroCEP;
	
	/**
	 * Cidade do CEP
	 */
	@ManyToOne
	private Cidade cidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumeroCEP() {
		return numeroCEP;
	}

	public void setNumeroCEP(Long numeroCEP) {
		this.numeroCEP = numeroCEP;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
