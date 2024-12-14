package model;

public class Frota {
	private Integer totalVeiculos;
	private Integer totalDisponiveis;
	private Integer totalEmManutencao;
	private String localizacao;

	
	private Veiculo veiculo; 

	
	
	public Frota() {
		super();
	}

	public Frota(Integer totalVeiculos, Integer totalDisponiveis, Integer totalEmManutencao, String localizacao,
			Veiculo veiculo) {
		this.totalVeiculos = totalVeiculos;
		this.totalDisponiveis = totalDisponiveis;
		this.totalEmManutencao = totalEmManutencao;
		this.localizacao = localizacao;
		this.veiculo = veiculo;
	}

	public Frota(Integer totalVeiculos, Integer totalDisponiveis, Integer totalEmManutencao, String localizacao) {

		this.totalVeiculos = totalVeiculos;
		this.totalDisponiveis = totalDisponiveis;
		this.totalEmManutencao = totalEmManutencao;
		this.localizacao = localizacao;
	}

	// Getters e Setters
	public Integer getTotalVeiculos() {
		return totalVeiculos;
	}

	public void setTotalVeiculos(Integer totalVeiculos) {
		this.totalVeiculos = totalVeiculos;
	}

	public Integer getTotalDisponiveis() {
		return totalDisponiveis;
	}

	public void setTotalDisponiveis(Integer totalDisponiveis) {
		this.totalDisponiveis = totalDisponiveis;
	}

	public Integer getTotalEmManutencao() {
		return totalEmManutencao;
	}

	public void setTotalEmManutencao(Integer totalEmManutencao) {
		this.totalEmManutencao = totalEmManutencao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
}
