package model;

import java.time.LocalDate;

public class Seguro {
	private Integer idSeguro;
	private String tipoSeguro;
	private Double valorCobertura;
	private Double franquia;
	private LocalDate vigencia;

	// Relacionamento
	private Locacao locacao; // Seguro está associado a uma locação

	public Seguro( String tipoSeguro, Double valorCobertura, Double franquia, LocalDate vigencia,
			Locacao locacao) {
		
		this.tipoSeguro = tipoSeguro;
		this.valorCobertura = valorCobertura;
		this.franquia = franquia;
		this.vigencia = vigencia;
		this.locacao = locacao;
	}

	

	public Seguro() {
		super();
	}



	// Getters e Setters
	public Integer getIdSeguro() {
		return idSeguro;
	}

	public void setIdSeguro(Integer idSeguro) {
		this.idSeguro = idSeguro;
	}

	public String getTipoSeguro() {
		return tipoSeguro;
	}

	public void setTipoSeguro(String tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}

	public Double getValorCobertura() {
		return valorCobertura;
	}

	public void setValorCobertura(Double valorCobertura) {
		this.valorCobertura = valorCobertura;
	}

	public Double getFranquia() {
		return franquia;
	}

	public void setFranquia(Double franquia) {
		this.franquia = franquia;
	}

	public LocalDate getVigencia() {
		return vigencia;
	}

	public void setVigencia(LocalDate vigencia) {
		this.vigencia = vigencia;
	}

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}
}
