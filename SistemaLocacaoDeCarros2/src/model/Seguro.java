package model;

import java.time.LocalDate;

public class Seguro {
	private String idSeguro;
	private String tipoSeguro;
	private Double valorCobertura;
	private Double franquia;
	private LocalDate vigencia;

	// Relacionamento
	private Locacao locacao; // Seguro está associado a uma locação

	public Seguro(String idSeguro, String tipoSeguro, Double valorCobertura, Double franquia, LocalDate vigencia,
			Locacao locacao) {
		this.idSeguro = idSeguro;
		this.tipoSeguro = tipoSeguro;
		this.valorCobertura = valorCobertura;
		this.franquia = franquia;
		this.vigencia = vigencia;
		this.locacao = locacao;
	}

	public Seguro(String idSeguro, String tipoSeguro, Double valorCobertura, Double franquia, LocalDate vigencia) {
		super();
		this.idSeguro = idSeguro;
		this.tipoSeguro = tipoSeguro;
		this.valorCobertura = valorCobertura;
		this.franquia = franquia;
		this.vigencia = vigencia;
	}

	// Getters e Setters
	public String getIdSeguro() {
		return idSeguro;
	}

	public void setIdSeguro(String idSeguro) {
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
