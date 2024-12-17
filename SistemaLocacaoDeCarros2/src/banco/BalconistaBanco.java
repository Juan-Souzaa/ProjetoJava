package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Balconista;


public class BalconistaBanco extends UsuarioBanco {
	private DBConnection connection;

	public BalconistaBanco() {
		this.connection = new DBConnection();
	}

	public void incluir(Balconista balconista) {
		try {
			super.incluir(balconista);
			String sql = "CALL inserir_balconista(?, ?, ? );";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setString(1, balconista.getCpf());
			statement.setString(2, balconista.getTurno());
			statement.setString(3, balconista.getFilial());
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir balconista: " + e.getMessage(), e);
		}

	}

	public List<Balconista> listarBalconistas() {
        List<Balconista> balconistas = new ArrayList<>();
        try {
            String sql = "CALL listar_balconistas();";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
            	  
   	                Balconista balconista = new Balconista();
   	                balconista.setIdUsuario(rs.getInt("idUsuario"));
   	                balconista.setNomeCompleto(rs.getString("nomeCompleto"));
   	                balconista.setEmail(rs.getString("email"));
   	                balconista.setSenha(rs.getString("senha"));
   	                balconista.setTelefone(rs.getString("telefone"));
   	                balconista.setEndereco(rs.getString("endereco"));
   	                balconista.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());
   	                balconista.setNivelAcesso(rs.getString("nivelAcesso"));
   	                balconista.setCpf(rs.getString("cpf"));
   	                balconista.setTurno(rs.getString("turno"));
   	                balconista.setFilial(rs.getString("filial"));
   	                
   	               balconistas.add(balconista);
   	            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar balconistas: " + e.getMessage(), e);
        }
        return balconistas;
    }

	// Consultar balconista por matrícula
	public Balconista consultar(String matricula) {
		Balconista balconista = null;
		try {
			String sql = "CALL consultar_balconista(?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setString(1, matricula);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				balconista = new Balconista();
				balconista.setIdBalconista(rs.getInt("matricula"));
				balconista.setTurno(rs.getString("turno"));

				balconista.setFilial(rs.getString("filial"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao consultar balconista: " + e.getMessage(), e);
		}
		return balconista;
	}

	public void atualizar(Balconista balconista) {
		super.atualizar(balconista);
		try {
			String sql = "CALL atualizar_balconista(?, ?, ? );";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, balconista.getIdUsuario());
			statement.setString(2, balconista.getTurno());

			statement.setString(3, balconista.getFilial());
		
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar balconista: " + e.getMessage(), e);
		}
	}

	public void deletar(int idUsuario) {
		try {
			String sql = "CALL deletar_balconista(?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, idUsuario);
			statement.executeUpdate();
			statement.close();
			
			super.deletar(idUsuario);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar balconista: " + e.getMessage(), e);
		}
	}
}
