package model;

import java.time.LocalDate;

public class Cancelamento extends Locacao {
	private String motivoCancelamento;
	private LocalDate dataCancelamento;

	public Cancelamento(Integer idLocacao, LocalDate dataLocacao, LocalDate dataDevolucaoPrevista,
			LocalDate dataDevolucaoReal, Double valorTotal, String tipoLocacao, String observacoes, Cliente cliente,
			Veiculo veiculo, Fatura fatura, String motivoCancelamento, LocalDate dataCancelamento) {
		super(idLocacao, dataLocacao, dataDevolucaoPrevista, dataDevolucaoReal, valorTotal, tipoLocacao, observacoes,
				cliente, veiculo, fatura);
		this.motivoCancelamento = motivoCancelamento;
		this.dataCancelamento = dataCancelamento;
	}

	

	public Cancelamento() {
		
	}



	


	public Cancelamento(String motivoCancelamento, LocalDate dataCancelamento) {
		super();
		this.motivoCancelamento = motivoCancelamento;
		this.dataCancelamento = dataCancelamento;
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
}
