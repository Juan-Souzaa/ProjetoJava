package banco;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import model.Cancelamento;
import model.Cliente;
import model.Locacao;
import model.Reserva;

public class CancelamentoBanco {
	private DBConnection connection;

	public CancelamentoBanco() {
		this.connection = new DBConnection();
	}

	public void incluir(Cancelamento cancelamento) {
		try {
			String sql = "CALL inserir_cancelamento(?, ?,?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setString(1, cancelamento.getMotivoCancelamento());
			statement.setString(2, cancelamento.getDataCancelamento().toString());
			statement.setInt(3, cancelamento.getLocacaoCancelamento().getIdLocacao());
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir cancelamento: " + e.getMessage(), e);
		}
	}

	public List<Cancelamento> listar() {

		List<Cancelamento> cancelamentos = new ArrayList<>();
		try {
			String sql = "CALL listar_cancelamentos();";
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

				Cancelamento cancelamento = new Cancelamento();
				cancelamento.setIdCancelamento(rs.getInt("idCancelamento"));
				cancelamento.setMotivoCancelamento(rs.getString("motivoCancelamento"));
				cancelamento.setDataCancelamento(rs.getDate("dataCancelamento").toLocalDate()); // Convertendo
																								// java.sql.Date para
																								// LocalDate
				cancelamento.setLocacaoCancelamento(locacao);

				cancelamentos.add(cancelamento);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar cancelamentos: " + e.getMessage(), e);
		}
		return cancelamentos;
	}

	public Cancelamento consultar(Cancelamento cancelamento) {
		Cancelamento cancelamentoConsulta= null;
		try {
			String sql = "CALL consultar_cancelamento(?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, cancelamento.getIdCancelamento());
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

				cancelamentoConsulta = new Cancelamento();
				cancelamentoConsulta.setIdCancelamento(rs.getInt("idCancelamento"));
				cancelamentoConsulta.setMotivoCancelamento(rs.getString("motivoCancelamento"));
				cancelamentoConsulta.setDataCancelamento(rs.getDate("dataCancelamento").toLocalDate()); // Convertendo
																								// java.sql.Date para
																								// LocalDate
				cancelamentoConsulta.setLocacaoCancelamento(locacao);

			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao consultar cancelamento: " + e.getMessage(), e);
		}
		
		return cancelamentoConsulta;
	}

	public void atualizar(Cancelamento cancelamento) {
		try {
			String sql = "CALL atualizar_cancelamento(?, ?,?,?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			
			statement.setInt(1, cancelamento.getIdCancelamento());
			statement.setString(2, cancelamento.getMotivoCancelamento());
			statement.setString(3, cancelamento.getDataCancelamento().toString());
			statement.setInt(4, cancelamento.getLocacaoCancelamento().getIdLocacao());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar cancelamento: " + e.getMessage(), e);
		}
	}

	public void deletar(Cancelamento cancelamento) {
		try {
			String sql = "CALL deletar_cancelamento(?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, cancelamento.getIdCancelamento());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar cancelamento: " + e.getMessage(), e);
		}
	}
}
