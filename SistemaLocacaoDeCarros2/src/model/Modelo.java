package model;

public class Modelo {
	private Integer idModelo;
	private String nomeModelo;
	private Double valorDiaria;
	private String categoria;
	private Integer capacidadePassageiros;
	private String tipoCombustivel;
	private Double consumoMedio;

	// Relacionamento
	private Veiculo veiculo; // Modelo está associado a um veículo

	public Modelo(String nomeModelo, Double valorDiaria, String categoria, Integer capacidadePassageiros,
			String tipoCombustivel, Double consumoMedio, Veiculo veiculo) {
		this.nomeModelo = nomeModelo;
		this.valorDiaria = valorDiaria;
		this.categoria = categoria;
		this.capacidadePassageiros = capacidadePassageiros;
		this.tipoCombustivel = tipoCombustivel;
		this.consumoMedio = consumoMedio;
		this.veiculo = veiculo;
	}

	public Modelo() {
		super();
	}

	

	public Modelo(Integer idModelo, String nomeModelo, Double valorDiaria, String categoria,
			Integer capacidadePassageiros, String tipoCombustivel, Double consumoMedio, Veiculo veiculo) {
		super();
		this.idModelo = idModelo;
		this.nomeModelo = nomeModelo;
		this.valorDiaria = valorDiaria;
		this.categoria = categoria;
		this.capacidadePassageiros = capacidadePassageiros;
		this.tipoCombustivel = tipoCombustivel;
		this.consumoMedio = consumoMedio;
		this.veiculo = veiculo;
	}

	public Integer getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(Integer idModelo) {
		this.idModelo = idModelo;
	}

	// Getters e Setters
	public String getNomeModelo() {
		return nomeModelo;
	}

	public void setNomeModelo(String nomeModelo) {
		this.nomeModelo = nomeModelo;
	}

	public Double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getCapacidadePassageiros() {
		return capacidadePassageiros;
	}

	public void setCapacidadePassageiros(Integer capacidadePassageiros) {
		this.capacidadePassageiros = capacidadePassageiros;
	}

	public String getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(String tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	public Double getConsumoMedio() {
		return consumoMedio;
	}

	public void setConsumoMedio(Double consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
}
