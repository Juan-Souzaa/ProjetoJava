package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Modelo;
import model.Reserva;

public class ReservaBanco {
	private DBConnection connection;

	public ReservaBanco() {
		this.connection = new DBConnection();
	}

	public void incluir(Reserva reserva) {
		try {
			String sql = "CALL inserir_reserva(?, ?, ?, ?, ?, ?,?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setString(1, reserva.getDataReserva().toString());
			statement.setString(2, reserva.getStatusReserva());
			statement.setString(3, reserva.getDataRetirada().toString());
			statement.setString(4, reserva.getDataDevolucao().toString());
			statement.setString(5, reserva.getObservacoes());
			statement.setInt(6, reserva.getClienteReserva().getIdUsuario());
			statement.setInt(7, reserva.getModeloReserva().getIdModelo());

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir reserva: " + e.getMessage(), e);
		}
	}

	public List<Reserva> listar() {
		List<Reserva> reservas = new ArrayList<>();
		try {
			String sql = "CALL listar_reservas();";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Reserva reserva = new Reserva();
				reserva.setIdReserva(rs.getInt("idReserva"));
				reserva.setDataReserva(rs.getDate("dataReserva").toLocalDate());
				reserva.setStatusReserva(rs.getString("statusReserva"));
				reserva.setDataRetirada(rs.getDate("dataRetirada").toLocalDate());
				reserva.setDataDevolucao(rs.getDate("dataDevolucao").toLocalDate());
				reserva.setObservacoes(rs.getString("observacoes"));

		
				Cliente cliente = new Cliente();

				cliente.setNomeCompleto(rs.getString("nomeCompleto"));

				Modelo modelo = new Modelo();

				modelo.setNomeModelo(rs.getString("nomeModelo"));
				modelo.setCategoria(rs.getString("categoria"));

				
				reserva.setClienteReserva(cliente);
				reserva.setModeloReserva(modelo);

			
				reservas.add(reserva);

			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar reservas: " + e.getMessage(), e);
		}
		return reservas;
	}

	public Reserva consultar(Reserva reserva) {
		Reserva reservaConsultar = null;
		try {
			String sql = "CALL consultar_reserva(?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, reserva.getIdReserva());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				reservaConsultar = new Reserva();
				reservaConsultar.setIdReserva(rs.getInt("idReserva"));
				reservaConsultar.setDataReserva(rs.getDate("dataReserva").toLocalDate());
				reservaConsultar.setStatusReserva(rs.getString("statusReserva"));
				reservaConsultar.setDataRetirada(rs.getDate("dataRetirada").toLocalDate());
				reservaConsultar.setDataDevolucao(rs.getDate("dataDevolucao").toLocalDate());
				reservaConsultar.setObservacoes(rs.getString("observacoes"));

				
				Cliente cliente = new Cliente();

				cliente.setNomeCompleto(rs.getString("nomeCompleto"));

				Modelo modelo = new Modelo();

				modelo.setNomeModelo(rs.getString("nomeModelo"));
				modelo.setCategoria(rs.getString("categoria"));

			
				reservaConsultar.setClienteReserva(cliente);
				reservaConsultar.setModeloReserva(modelo);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao consultar reserva: " + e.getMessage(), e);
		}
		return reservaConsultar;
	}

	public void atualizar(Reserva reserva) {
		try {
			String sql = "CALL atualizar_reserva(?,?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, reserva.getIdReserva());
			statement.setString(2, reserva.getDataReserva().toString());
			statement.setString(3, reserva.getStatusReserva());
			statement.setString(4, reserva.getDataRetirada().toString());
			statement.setString(5, reserva.getDataDevolucao().toString());
			statement.setString(6, reserva.getObservacoes());
			statement.setInt(7, reserva.getClienteReserva().getIdUsuario());
			statement.setInt(8, reserva.getModeloReserva().getIdModelo());
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar reserva: " + e.getMessage(), e);
		}
	}

	public void deletar(Reserva reserva) {
		try {
			String sql = "CALL deletar_reserva(?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, reserva.getIdReserva());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar reserva: " + e.getMessage(), e);
		}
	}
}
