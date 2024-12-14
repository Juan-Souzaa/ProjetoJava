package model;

import java.time.LocalDate;

public class Veiculo {
	private Integer idVeiculo;
    private String placa;
    private String chassi;
    private String cor;
    private LocalDate ano;
    private Double quilometragem;
    private Boolean statusDisponibilidade;
    private String categoria;
    private Boolean seguroAtivo;
    private String marca;
    private LocalDate dataUltimaManutencao;
    private Multa multaVeiculo;
    private Manutencao manutencaoVeiculo;

   
    
    
    
    public Veiculo(Integer idVeiculo, String placa, String chassi, String cor, LocalDate ano, Double quilometragem,
			Boolean statusDisponibilidade, String categoria, Boolean seguroAtivo, String marca,
			LocalDate dataUltimaManutencao, Multa multaVeiculo, Manutencao manutencaoVeiculo) {
		super();
		this.idVeiculo = idVeiculo;
		this.placa = placa;
		this.chassi = chassi;
		this.cor = cor;
		this.ano = ano;
		this.quilometragem = quilometragem;
		this.statusDisponibilidade = statusDisponibilidade;
		this.categoria = categoria;
		this.seguroAtivo = seguroAtivo;
		this.marca = marca;
		this.dataUltimaManutencao = dataUltimaManutencao;
		this.multaVeiculo = multaVeiculo;
		this.manutencaoVeiculo = manutencaoVeiculo;
	}
    
    

	public Veiculo(Integer idVeiculo, String placa, String chassi, String cor, LocalDate ano, Double quilometragem,
			Boolean statusDisponibilidade, String categoria, Boolean seguroAtivo, String marca,
			LocalDate dataUltimaManutencao) {
		
		this.idVeiculo = idVeiculo;
		this.placa = placa;
		this.chassi = chassi;
		this.cor = cor;
		this.ano = ano;
		this.quilometragem = quilometragem;
		this.statusDisponibilidade = statusDisponibilidade;
		this.categoria = categoria;
		this.seguroAtivo = seguroAtivo;
		this.marca = marca;
		this.dataUltimaManutencao = dataUltimaManutencao;
	}




	public Veiculo() {
		super();
	}





	// Getters e Setters
    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public LocalDate getAno() {
        return ano;
    }

    public void setAno(LocalDate anoVeiculo) {
        this.ano = anoVeiculo;
    }

    public Double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Double quilometragem) {
        this.quilometragem = quilometragem;
    }

    public Boolean isStatusDisponibilidade() {
        return statusDisponibilidade;
    }

    public void setStatusDisponibilidade(Boolean statusDisponibilidade) {
        this.statusDisponibilidade = statusDisponibilidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Boolean isSeguroAtivo() {
        return seguroAtivo;
    }

    public void setSeguroAtivo(Boolean seguroAtivo) {
        this.seguroAtivo = seguroAtivo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public LocalDate getDataUltimaManutencao() {
        return dataUltimaManutencao;
    }

    public void setDataUltimaManutencao(LocalDate dataUltimaManutencao) {
        this.dataUltimaManutencao = dataUltimaManutencao;
    }

    public Multa getMultaVeiculo() {
        return multaVeiculo;
    }

    public void setMultaVeiculo(Multa multaVeiculo) {
        this.multaVeiculo = multaVeiculo;
    }

    public Manutencao getManutencaoVeiculo() {
        return manutencaoVeiculo;
    }

    public void setManutencaoVeiculo(Manutencao manutencaoVeiculo) {
        this.manutencaoVeiculo = manutencaoVeiculo;
    }
}
