package model;

import java.time.LocalDate;

public class Reserva {
	private Integer idReserva;
	private LocalDate dataReserva;
	private String statusReserva;
	private LocalDate dataRetirada;
	private LocalDate dataDevolucao;
	private String observacoes;
	

	// Relacionamentos
	private Cliente clienteReserva; // Uma reserva pertence a um cliente
	private Modelo modeloReserva; // Uma reserva está associada a um veículo

	public Reserva(LocalDate dataReserva, String statusReserva, LocalDate dataRetirada,
			LocalDate dataDevolucao, String observacoes,  Cliente cliente, Modelo veiculo) {
		
		this.dataReserva = dataReserva;
		this.statusReserva = statusReserva;
		this.dataRetirada = dataRetirada;
		this.dataDevolucao = dataDevolucao;
		this.observacoes = observacoes;
		
		this.clienteReserva= cliente;
		this.modeloReserva = veiculo;
	}

	public Reserva(Integer idReserva, LocalDate dataReserva, String statusReserva, LocalDate dataRetirada,
			LocalDate dataDevolucao, String observacoes) {
		super();
		this.idReserva = idReserva;
		this.dataReserva = dataReserva;
		this.statusReserva = statusReserva;
		this.dataRetirada = dataRetirada;
		this.dataDevolucao = dataDevolucao;
		this.observacoes = observacoes;
	}
	
	

	public Reserva() {
		super();
	}

	// Getters e Setters
	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public LocalDate getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(LocalDate dataReserva) {
		this.dataReserva = dataReserva;
	}

	public String getStatusReserva() {
		return statusReserva;
	}

	public void setStatusReserva(String statusReserva) {
		this.statusReserva = statusReserva;
	}

	public LocalDate getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	

	public Modelo getModeloReserva() {
		return modeloReserva;
	}

	public void setModeloReserva(Modelo veiculo) {
		this.modeloReserva = veiculo;
	}

	public Cliente getClienteReserva() {
		return clienteReserva;
	}

	public void setClienteReserva(Cliente clienteReserva) {
		this.clienteReserva = clienteReserva;
	}
}
