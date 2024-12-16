package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Locacao;
import model.Reserva;
import model.Seguro;

public class SeguroBanco {
	private DBConnection connection;

	public SeguroBanco() {
		this.connection = new DBConnection();
	}

	public void incluir(Seguro seguro) {
		try {
			String sql = "CALL inserir_seguro(?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);

			statement.setString(1, seguro.getTipoSeguro());
			statement.setDouble(2, seguro.getValorCobertura());
			statement.setDouble(3, seguro.getFranquia());
			statement.setString(4, seguro.getVigencia().toString());
			statement.setInt(5, seguro.getLocacao().getIdLocacao());
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir seguro: " + e.getMessage(), e);
		}
	}

	public List<Seguro> listar() {
		List<Seguro> seguros = new ArrayList<>();
		try {
			String sql = "CALL listar_seguros();";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				
				
				Reserva reserva = new Reserva();
				Cliente cliente = new Cliente();
				cliente.setIdUsuario(rs.getInt("idUsuario"));
				cliente.setNomeCompleto(rs.getString("nomeCompleto"));
				reserva.setClienteReserva(cliente);
				
				
				
				
				Locacao locacao = new Locacao ();
				locacao.setIdLocacao(rs.getInt("idLocacao"));
				locacao.setReservaLocacao(reserva);
				
				Seguro seguro = new Seguro();
				seguro.setIdSeguro(rs.getInt("idSeguro"));
				seguro.setTipoSeguro(rs.getString("tipoSeguro"));
				seguro.setValorCobertura(rs.getDouble("valorCobertura"));
				seguro.setFranquia(rs.getDouble("franquia"));
				seguro.setVigencia(rs.getDate("vigencia").toLocalDate());
				seguro.setLocacao(locacao);
				

				seguros.add(seguro);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar seguros: " + e.getMessage(), e);
		}
		return seguros;
	}

	public Seguro consultar(int idSeguro) {
		Seguro seguro = null;
		try {
			String sql = "CALL consultar_seguro(?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, idSeguro);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				seguro = new Seguro();
				seguro.setIdSeguro(rs.getInt("idSeguro"));
				seguro.setTipoSeguro(rs.getString("tipoSeguro"));
				seguro.setValorCobertura(rs.getDouble("valorCobertura"));
				seguro.setFranquia(rs.getDouble("franquia"));
				seguro.setVigencia(rs.getDate("vigencia").toLocalDate());
				
				Reserva reserva = new Reserva();
				Cliente cliente = new Cliente();
				cliente.setIdUsuario(rs.getInt("idUsuario"));
				cliente.setNomeCompleto(rs.getString("nomeCompleto"));
				reserva.setClienteReserva(cliente);
				
				
				
				
				Locacao locacao = new Locacao ();
				locacao.setIdLocacao(rs.getInt("idLocacao"));
				locacao.setReservaLocacao(reserva);
				
				
				seguro.setLocacao(locacao);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao consultar seguro: " + e.getMessage(), e);
		}
		return seguro;
	}

	public void atualizar(Seguro seguro) {
		try {
			String sql = "CALL atualizar_seguro(?, ?, ?, ?, ?,?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, seguro.getIdSeguro());
			statement.setString(2, seguro.getTipoSeguro());
			statement.setDouble(3, seguro.getValorCobertura());
			statement.setDouble(4, seguro.getFranquia());
			statement.setString(5, seguro.getVigencia().toString());
			statement.setInt(6, seguro.getLocacao().getIdLocacao());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar seguro: " + e.getMessage(), e);
		}
	}

	public void deletar(int idSeguro) {
		try {
			String sql = "CALL deletar_seguro(?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, idSeguro);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar seguro: " + e.getMessage(), e);
		}
	}
}
