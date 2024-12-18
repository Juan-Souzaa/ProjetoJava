package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.AgenteLocacao;



public class AgenteLocacaoBanco extends UsuarioBanco {
	
	 private DBConnection connection;

	    public AgenteLocacaoBanco() {
	        this.connection = new DBConnection();
	    }


    public void incluir(AgenteLocacao agente) {
        super.incluir(agente); 
        try {
            String sql = "CALL inserir_agente(?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, agente.getCpf());
            statement.setString(2, agente.getRegiaoAtuacao());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir agente de locação: " + e.getMessage(), e);
        }
    }

    public List<AgenteLocacao> listarAgentes() {
        List<AgenteLocacao> agentes = new ArrayList<>();
        try {
            String sql = "CALL listar_agentes();";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                AgenteLocacao agente = new AgenteLocacao();
                
              
	                agente.setIdUsuario(rs.getInt("idUsuario"));
	                agente.setNomeCompleto(rs.getString("nomeCompleto"));
	                agente.setEmail(rs.getString("email"));
	                agente.setSenha(rs.getString("senha"));
	                agente.setTelefone(rs.getString("telefone"));
	                agente.setEndereco(rs.getString("endereco"));
	                agente.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());
	                agente.setNivelAcesso(rs.getString("nivelAcesso"));
	                agente.setCpf(rs.getString("cpf"));
	         
	                agente.setRegiaoAtuacao(rs.getString("regiaoAtuacao"));
	                
	               agentes.add(agente);
          
             
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar agentes de locação: " + e.getMessage(), e);
        }
        return agentes;
    }

  

    public void atualizar(AgenteLocacao agente) {
        super.atualizar(agente); 
        try {
            String sql = "CALL atualizar_agente(?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, agente.getIdUsuario());
            statement.setString(2, agente.getRegiaoAtuacao());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar agente de locação: " + e.getMessage(), e);
        }
    }

    public void deletar(AgenteLocacao agente) {
      
        try {
            String sql = "CALL deletar_agente(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, agente.getIdUsuario());
            statement.executeUpdate();
            statement.close();
            
            super.deletar(agente);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar agente de locação: " + e.getMessage(), e);
        }
    }
}
