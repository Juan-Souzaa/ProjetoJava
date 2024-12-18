package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Fatura;
import model.Locacao;
import model.Pagamento;
import model.Reserva;

public class PagamentoBanco {
	private DBConnection connection;

	public PagamentoBanco() {
		this.connection = new DBConnection();
	}

	public void incluir(Pagamento pagamento) {
		try {
			String sql = "CALL inserir_pagamento(?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);

			statement.setDouble(1, pagamento.getValor());
			statement.setString(2, pagamento.getMetodoPagamento());
			statement.setString(3, pagamento.getDataPagamento().toString());
			statement.setString(4, pagamento.getStatus());
			statement.setString(5, pagamento.getDescricao());
			statement.setInt(6, pagamento.getFatura().getNumeroFatura());

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir pagamento: " + e.getMessage(), e);
		}
	}

	public List<Pagamento> listar() {
		List<Pagamento> pagamentos = new ArrayList<>();
		try {
			String sql = "CALL listar_pagamentos();";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Pagamento pagamento = new Pagamento();
				pagamento.setIdPagamento(rs.getInt("idPagamento"));
				pagamento.setValor(rs.getDouble("valor"));
				pagamento.setMetodoPagamento(rs.getString("metodoPagamento"));
				pagamento.setDataPagamento(rs.getDate("dataPagamento").toLocalDate());
				pagamento.setStatus(rs.getString("statusPagamento"));
				pagamento.setDescricao(rs.getString("descricao"));

				Cliente cliente = new Cliente();
				cliente.setNomeCompleto(rs.getString("nomeCompleto"));

				Reserva reserva = new Reserva();
				reserva.setClienteReserva(cliente);

				Locacao locacao = new Locacao();
				locacao.setReservaLocacao(reserva);

				Fatura fatura = new Fatura();
				fatura.setLocacaoFatura(locacao);

				pagamento.setFatura(fatura);

				pagamentos.add(pagamento);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar pagamentos: " + e.getMessage(), e);
		}
		return pagamentos;
	}

	public Pagamento consultar(Pagamento pagamento) {
		Pagamento pagamentoConsulta = null;
		try {
			String sql = "CALL consultar_pagamento(?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, pagamento.getIdPagamento());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				pagamentoConsulta = new Pagamento();
				pagamentoConsulta.setIdPagamento(rs.getInt("idPagamento"));
				pagamentoConsulta.setValor(rs.getDouble("valor"));
				pagamentoConsulta.setMetodoPagamento(rs.getString("metodoPagamento"));
				pagamentoConsulta.setDataPagamento(rs.getDate("dataPagamento").toLocalDate());
				pagamentoConsulta.setStatus(rs.getString("statusPagamento"));
				pagamentoConsulta.setDescricao(rs.getString("descricao"));

				Cliente cliente = new Cliente();
				cliente.setNomeCompleto(rs.getString("nomeCompleto"));

				Reserva reserva = new Reserva();
				reserva.setClienteReserva(cliente);

				Locacao locacao = new Locacao();
				locacao.setReservaLocacao(reserva);

				Fatura fatura = new Fatura();
				fatura.setLocacaoFatura(locacao);

				pagamentoConsulta.setFatura(fatura);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao consultar pagamento: " + e.getMessage(), e);
		}
		return pagamentoConsulta;
	}

	public void atualizar(Pagamento pagamento) {
		try {
			String sql = "CALL atualizar_pagamento(?, ?, ?, ?, ?, ?,?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, pagamento.getIdPagamento());
			statement.setDouble(2, pagamento.getValor());
			statement.setString(3, pagamento.getMetodoPagamento());
			statement.setString(4, pagamento.getDataPagamento().toString());
			statement.setString(5, pagamento.getStatus());
			statement.setString(6, pagamento.getDescricao());
			statement.setInt(7, pagamento.getFatura().getNumeroFatura());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar pagamento: " + e.getMessage(), e);
		}
	}

	public void deletar(Pagamento pagamento) {
		try {
			String sql = "CALL deletar_pagamento(?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, pagamento.getIdPagamento());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar pagamento: " + e.getMessage(), e);
		}
	}
}
