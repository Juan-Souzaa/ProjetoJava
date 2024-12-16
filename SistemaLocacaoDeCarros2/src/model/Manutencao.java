package model;

import java.time.LocalDate;

public class Manutencao {
	private Integer idManutencao;
	private LocalDate dataManutencao;
	private String tipoManutencao;
	private Double custo;
	private String descricao;

	// Relacionamento
	private Veiculo veiculoManutencao; // Manutenção está associada a um veículo

	public Manutencao( LocalDate dataManutencao, String tipoManutencao, Double custo, String descricao,
			Veiculo veiculo) {
		
		this.dataManutencao = dataManutencao;
		this.tipoManutencao = tipoManutencao;
		this.custo = custo;
		this.descricao = descricao;
		this.veiculoManutencao = veiculo;
	}

	




	public Manutencao(Integer idManutencao, LocalDate dataManutencao, String tipoManutencao, Double custo,
			String descricao, Veiculo veiculoManutencao) {
		super();
		this.idManutencao = idManutencao;
		this.dataManutencao = dataManutencao;
		this.tipoManutencao = tipoManutencao;
		this.custo = custo;
		this.descricao = descricao;
		this.veiculoManutencao = veiculoManutencao;
	}






	public Manutencao() {
		super();
	}



	// Getters e Setters
	public Integer getIdManutencao() {
		return idManutencao;
	}

	public void setIdManutencao(Integer idManutencao) {
		this.idManutencao = idManutencao;
	}

	public LocalDate getDataManutencao() {
		return dataManutencao;
	}

	public void setDataManutencao(LocalDate dataManutencao) {
		this.dataManutencao = dataManutencao;
	}

	public String getTipoManutencao() {
		return tipoManutencao;
	}

	public void setTipoManutencao(String tipoManutencao) {
		this.tipoManutencao = tipoManutencao;
	}

	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Veiculo getVeiculoManutencao() {
		return veiculoManutencao;
	}

	public void setVeiculomanutencao(Veiculo veiculo) {
		this.veiculoManutencao = veiculo;
	}
}
