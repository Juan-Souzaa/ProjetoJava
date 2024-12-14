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
	private Veiculo veiculo; // Uma reserva está associada a um veículo

	public Reserva(Integer idReserva, LocalDate dataReserva, String statusReserva, LocalDate dataRetirada,
			LocalDate dataDevolucao, String observacoes,  Cliente cliente, Veiculo veiculo) {
		this.idReserva = idReserva;
		this.dataReserva = dataReserva;
		this.statusReserva = statusReserva;
		this.dataRetirada = dataRetirada;
		this.dataDevolucao = dataDevolucao;
		this.observacoes = observacoes;
		
		this.setClienteReserva(cliente);
		this.veiculo = veiculo;
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

	

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente getClienteReserva() {
		return clienteReserva;
	}

	public void setClienteReserva(Cliente clienteReserva) {
		this.clienteReserva = clienteReserva;
	}
}
