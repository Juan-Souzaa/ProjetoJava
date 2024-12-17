package model;

import java.time.LocalDate;


public class Retirada {
	private Integer idRetirada;
	private LocalDate dataRetirada;
	private String localRetirada;
	private Boolean documentosVerificados;
	private Boolean statusRetirada;

	// Relacionamento
	
	
	private Locacao locacaoRetirada;
	
	
	
	
	
	
	
	



	public Retirada(LocalDate dataRetirada, String localRetirada, Boolean documentosVerificados, Boolean statusRetirada,
			Locacao locacaoRetirada) {
		super();
		this.dataRetirada = dataRetirada;
		this.localRetirada = localRetirada;
		this.documentosVerificados = documentosVerificados;
		this.statusRetirada = statusRetirada;
		this.locacaoRetirada = locacaoRetirada;
	}



	public Retirada(Integer idRetirada, LocalDate dataRetirada, String localRetirada, Boolean documentosVerificados,
			Boolean statusRetirada, Locacao locacaoRetirada) {
		super();
		this.idRetirada = idRetirada;
		this.dataRetirada = dataRetirada;
		this.localRetirada = localRetirada;
		this.documentosVerificados = documentosVerificados;
		this.statusRetirada = statusRetirada;
		this.locacaoRetirada = locacaoRetirada;
	}



	public Integer getIdRetirada() {
		return idRetirada;
	}



	public void setIdRetirada(Integer idRetirada) {
		this.idRetirada = idRetirada;
	}



	public Locacao getLocacaoRetirada() {
		return locacaoRetirada;
	}



	public void setLocacaoRetirada(Locacao locacaoRetirada) {
		this.locacaoRetirada = locacaoRetirada;
	}



	public Retirada() {
	}

	

	// Getters e Setters
	public LocalDate getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public String getLocalRetirada() {
		return localRetirada;
	}

	public void setLocalRetirada(String localRetirada) {
		this.localRetirada = localRetirada;
	}

	public Boolean getDocumentosVerificados() {
		return documentosVerificados;
	}

	public void setDocumentosVerificados(Boolean documentosVerificados) {
		this.documentosVerificados = documentosVerificados;
	}

	public Boolean getStatusRetirada() {
		return statusRetirada;
	}

	public void setStatusRetirada(Boolean statusRetirada) {
		this.statusRetirada = statusRetirada;
	}

	
}
