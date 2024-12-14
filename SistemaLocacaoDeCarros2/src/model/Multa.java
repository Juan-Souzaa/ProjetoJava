package model;

import java.time.LocalDate;

public class Multa {
	private String idMulta;
	private String motivo;
	private Double valorMulta;
	private LocalDate dataOcorrencia;
	private String statusMulta;
	private String observacoes;

	// Relacionamento
	private Veiculo veiculo; // Uma multa está associada a um veículo

	public Multa(String idMulta, String motivo, Double valorMulta, LocalDate dataOcorrencia, String statusMulta,
			String observacoes, Veiculo veiculo) {
		this.idMulta = idMulta;
		this.motivo = motivo;
		this.valorMulta = valorMulta;
		this.dataOcorrencia = dataOcorrencia;
		this.statusMulta = statusMulta;
		this.observacoes = observacoes;
		this.veiculo = veiculo;
	}

	public Multa(String idMulta, String motivo, Double valorMulta, LocalDate dataOcorrencia, String statusMulta) {
		super();
		this.idMulta = idMulta;
		this.motivo = motivo;
		this.valorMulta = valorMulta;
		this.dataOcorrencia = dataOcorrencia;
		this.statusMulta = statusMulta;

	}

	// Getters e Setters
	public String getIdMulta() {
		return idMulta;
	}

	public void setIdMulta(String idMulta) {
		this.idMulta = idMulta;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Double getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(Double valorMulta) {
		this.valorMulta = valorMulta;
	}

	public LocalDate getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(LocalDate dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public String getStatusMulta() {
		return statusMulta;
	}

	public void setStatusMulta(String statusMulta) {
		this.statusMulta = statusMulta;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
}
