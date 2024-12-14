package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.AgenteLocacao;
import model.Usuario;

public class AgenteLocacaoBanco extends UsuarioBanco {
	
	 private DBConnection connection;

	    public AgenteLocacaoBanco() {
	        this.connection = new DBConnection();
	    }


    public void incluir(AgenteLocacao agente) {
        super.incluir(agente); 
        try {
            String sql = "CALL inserir_agente_locacao(?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, agente.getCodigoAgente());
            statement.setString(2, agente.getRegiaoAtuacao());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir agente de locação: " + e.getMessage(), e);
        }
    }

    public List<AgenteLocacao> listarAgentes() {
        List<AgenteLocacao> agentes = new ArrayList<>();
        List<Usuario> usuarios = super.listar(); // Lista os usuários da tabela Usuario
        try {
            String sql = "CALL listar_agentes_locacao();";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                AgenteLocacao agente = new AgenteLocacao();
                agente.setCodigoAgente(rs.getInt("codigoAgente"));
                agente.setRegiaoAtuacao(rs.getString("regiaoAtuacao"));

        
                for (Usuario usuario : usuarios) {
                    if (usuario.getIdUsuario().equals(rs.getInt("idUsuario"))) {
                        agente.setIdUsuario(usuario.getIdUsuario());
                        agente.setNomeCompleto(usuario.getNomeCompleto());
                        agente.setEmail(usuario.getEmail());
                        agente.setSenha(usuario.getSenha());
                        agente.setTelefone(usuario.getTelefone());
                        agente.setEndereco(usuario.getEndereco());
                        agente.setDataCadastro(usuario.getDataCadastro());
                     
                        agente.setNivelAcesso(usuario.getNivelAcesso());
                        break;
                    }
                }
                agentes.add(agente);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar agentes de locação: " + e.getMessage(), e);
        }
        return agentes;
    }

    public AgenteLocacao consultar(int codigoAgente) {
        Usuario usuario = super.consultar(codigoAgente);
        AgenteLocacao agente = null;
        try {
            String sql = "CALL consultar_agente_locacao(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, codigoAgente);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                agente = new AgenteLocacao();
                agente.setCodigoAgente(rs.getInt("codigoAgente"));
                agente.setRegiaoAtuacao(rs.getString("regiaoAtuacao"));

                
                agente.setIdUsuario(usuario.getIdUsuario());
                agente.setNomeCompleto(usuario.getNomeCompleto());
                agente.setEmail(usuario.getEmail());
                agente.setSenha(usuario.getSenha());
                agente.setTelefone(usuario.getTelefone());
                agente.setEndereco(usuario.getEndereco());
                agente.setDataCadastro(usuario.getDataCadastro());
                
                agente.setNivelAcesso(usuario.getNivelAcesso());
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar agente de locação: " + e.getMessage(), e);
        }
        return agente;
    }

    public void atualizar(AgenteLocacao agente) {
        super.atualizar(agente); 
        try {
            String sql = "CALL atualizar_agente_locacao(?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, agente.getCodigoAgente());
            statement.setString(2, agente.getRegiaoAtuacao());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar agente de locação: " + e.getMessage(), e);
        }
    }

    public void deletar(int codigoAgente) {
        super.deletar(codigoAgente);
        try {
            String sql = "CALL deletar_agente_locacao(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, codigoAgente);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar agente de locação: " + e.getMessage(), e);
        }
    }
}
