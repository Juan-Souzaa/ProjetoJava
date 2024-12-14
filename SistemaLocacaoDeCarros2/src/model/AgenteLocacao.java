package model;

public class AgenteLocacao extends Usuario {
	private Integer codigoAgente;
	private String regiaoAtuacao;

	// Construtor completo
	public AgenteLocacao(Integer codigoAgente, String regiaoAtuacao) {
		super();
		this.codigoAgente = codigoAgente;
		this.regiaoAtuacao = regiaoAtuacao;
	}

	// Construtor vazio
	public AgenteLocacao() {
	}

	public AgenteLocacao(String nomeCompleto, String email, String senha, String telefone, String endereco,
			String regiaoAtuacao2, String tipoUsuario, String nivelAcesso) {
		// TODO Auto-generated constructor stub
	}

	// Getters e setters
	public Integer getCodigoAgente() {
		return codigoAgente;
	}

	public void setCodigoAgente(Integer codigoAgente) {
		this.codigoAgente = codigoAgente;
	}

	public String getRegiaoAtuacao() {
		return regiaoAtuacao;
	}

	public void setRegiaoAtuacao(String regiaoAtuacao) {
		this.regiaoAtuacao = regiaoAtuacao;
	}
}
