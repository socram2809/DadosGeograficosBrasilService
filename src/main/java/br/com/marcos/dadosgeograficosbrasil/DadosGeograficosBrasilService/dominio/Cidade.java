package br.com.marcos.dadosgeograficosbrasil.DadosGeograficosBrasilService.dominio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	@NotNull
	@ManyToOne
	private Estado estado;
	
	/**
	 * CEPs da cidade
	 */
	@OneToMany(mappedBy = "codigoIBGECidade")
	private List<CodigoEnderecamentoPostal> ceps;

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
	
	public List<CodigoEnderecamentoPostal> getCeps() {
		return ceps;
	}

	public void setCeps(List<CodigoEnderecamentoPostal> ceps) {
		this.ceps = ceps;
	}
	
}
