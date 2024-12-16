package model;

import java.util.List;


public class Frota {
	
	private Integer idFrota;
    private Integer totalVeiculos;
    private Integer totalDisponiveis;
    private Integer totalEmManutencao;
    private String localizacao;
    private List<Veiculo> veiculos;

    public Frota(Integer totalVeiculos, Integer totalDisponiveis, Integer totalEmManutencao, String localizacao, List<Veiculo> veiculos) {
        this.totalVeiculos = totalVeiculos;
        this.totalDisponiveis = totalDisponiveis;
        this.totalEmManutencao = totalEmManutencao;
        this.localizacao = localizacao;
        this.veiculos = veiculos;
    }

    public Frota() {
		super();
	}

	public Integer getIdFrota() {
		return idFrota;
	}

	public void setIdFrota(Integer idFrota) {
		this.idFrota = idFrota;
	}

	// Getters e Setters
    public int getTotalVeiculos() {
        return totalVeiculos;
    }

    public void setTotalVeiculos(Integer totalVeiculos) {
        this.totalVeiculos = totalVeiculos;
    }

    public int getTotalDisponiveis() {
        return totalDisponiveis;
    }

    public void setTotalDisponiveis(Integer totalDisponiveis) {
        this.totalDisponiveis = totalDisponiveis;
    }

    public int getTotalEmManutencao() {
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

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
}
