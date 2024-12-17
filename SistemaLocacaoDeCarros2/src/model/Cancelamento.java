package model;

import java.time.LocalDate;

public class Cancelamento {
	private Integer idCancelamento;
	private String motivoCancelamento;
	private LocalDate dataCancelamento;

	private Locacao locacaoCancelamento;

	public Cancelamento(String motivoCancelamento, LocalDate dataCancelamento, Locacao locacaoCancelamento) {
		super();
		this.motivoCancelamento = motivoCancelamento;
		this.dataCancelamento = dataCancelamento;
		this.locacaoCancelamento = locacaoCancelamento;
	}

	public Cancelamento(Integer idCancelamento, String motivoCancelamento, LocalDate dataCancelamento,
			Locacao locacaoCancelamento) {
		super();
		this.idCancelamento = idCancelamento;
		this.motivoCancelamento = motivoCancelamento;
		this.dataCancelamento = dataCancelamento;
		this.locacaoCancelamento = locacaoCancelamento;
	}

	public Cancelamento() {

	}

	public Integer getIdCancelamento() {
		return idCancelamento;
	}

	public void setIdCancelamento(Integer idCancelamento) {
		this.idCancelamento = idCancelamento;
	}

	// Getters e Setters
	public String getMotivoCancelamento() {
		return motivoCancelamento;
	}

	public void setMotivoCancelamento(String motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}

	public LocalDate getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(LocalDate dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public Locacao getLocacaoCancelamento() {
		return locacaoCancelamento;
	}

	public void setLocacaoCancelamento(Locacao locacaoCancelamento) {
		this.locacaoCancelamento = locacaoCancelamento;
	}
}
