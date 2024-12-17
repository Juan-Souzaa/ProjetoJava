package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Locacao;
import model.Reserva;
import model.Retirada;

public class RetiradaBanco {
	private DBConnection connection;

	public RetiradaBanco() {
		this.connection = new DBConnection();
	}

	public void incluir(Retirada retirada) {
		try {
			String sql = "CALL inserir_retirada(?, ?, ?, ?,?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setString(1, retirada.getDataRetirada().toString());
			statement.setString(2, retirada.getLocalRetirada());
			statement.setBoolean(3, retirada.getDocumentosVerificados());
			statement.setBoolean(4, retirada.getStatusRetirada());
			statement.setInt(5, retirada.getLocacaoRetirada().getIdLocacao());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir retirada: " + e.getMessage(), e);
		}
	}

	public List<Retirada> listar() {
		List<Retirada> retiradas = new ArrayList<>();
		try {
			String sql = "CALL listar_retiradas();";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				
				Reserva reserva = new Reserva();
				Cliente cliente = new Cliente();
				cliente.setIdUsuario(rs.getInt("idUsuario"));
				cliente.setNomeCompleto(rs.getString("nomeCompleto"));
				reserva.setClienteReserva(cliente);

				Locacao locacao = new Locacao();
				locacao.setIdLocacao(rs.getInt("idLocacao"));
				locacao.setReservaLocacao(reserva);
				
				
				Retirada retirada = new Retirada();
				retirada.setIdRetirada(rs.getInt("idRetirada"));
				retirada.setDataRetirada(rs.getDate("dataRetirada").toLocalDate());
				retirada.setLocalRetirada(rs.getString("localRetirada"));
				retirada.setDocumentosVerificados(rs.getBoolean("documentosVerificados"));
				retirada.setStatusRetirada(rs.getBoolean("statusRetirada"));
				retirada.setLocacaoRetirada(locacao);
				retiradas.add(retirada);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar retiradas: " + e.getMessage(), e);
		}
		return retiradas;
	}

	public Retirada consultar(int idRetirada) {
		Retirada retirada = null;
		try {
			String sql = "CALL consultar_retirada(?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, idRetirada);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				Reserva reserva = new Reserva();
				Cliente cliente = new Cliente();
				cliente.setIdUsuario(rs.getInt("idUsuario"));
				cliente.setNomeCompleto(rs.getString("nomeCompleto"));
				reserva.setClienteReserva(cliente);

				Locacao locacao = new Locacao();
				locacao.setIdLocacao(rs.getInt("idLocacao"));
				locacao.setReservaLocacao(reserva);
				
				
				retirada = new Retirada();
				retirada.setIdRetirada(rs.getInt("idRetirada"));
				retirada.setDataRetirada(rs.getDate("dataRetirada").toLocalDate());
				retirada.setLocalRetirada(rs.getString("localRetirada"));
				retirada.setDocumentosVerificados(rs.getBoolean("documentosVerificados"));
				retirada.setStatusRetirada(rs.getBoolean("statusRetirada"));
				retirada.setLocacaoRetirada(locacao);
				
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao consultar retirada: " + e.getMessage(), e);
		}
		return retirada;
	}

	public void atualizar(Retirada retirada) {
		try {
			String sql = "CALL atualizar_retirada(?, ?, ?, ?, ?,?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);

			// Passa os dados da retirada para a procedure
			statement.setInt(1, retirada.getIdRetirada()); // Passa o ID da retirada
			statement.setString(2, retirada.getDataRetirada().toString());
			statement.setString(3, retirada.getLocalRetirada());
			statement.setBoolean(4, retirada.getDocumentosVerificados());
			statement.setBoolean(5, retirada.getStatusRetirada());
			statement.setInt(6, retirada.getLocacaoRetirada().getIdLocacao());

			statement.executeUpdate(); // Atualiza a retirada
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar retirada: " + e.getMessage(), e);
		}
	}

	public void deletar(int idRetirada) {
		try {
			String sql = "CALL deletar_retirada(?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, idRetirada); // Passa o ID da retirada para a procedure
			statement.executeUpdate(); // Deleta a retirada
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar retirada: " + e.getMessage(), e);
		}
	}

}
