package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Multa;
import model.Veiculo;

public class MultaBanco {
	private DBConnection connection;

	public MultaBanco() {
		this.connection = new DBConnection();
	}

	public void incluir(Multa multa) {
		try {
			String sql = "CALL inserir_multa(?, ?, ?, ?, ?,?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);

			statement.setString(1, multa.getMotivo());
			statement.setDouble(2, multa.getValorMulta());
			statement.setString(3, multa.getDataOcorrencia().toString());
			statement.setString(4, multa.getStatusMulta());
			statement.setString(5, multa.getObservacoes());
			statement.setInt(6, multa.getVeiculo().getIdVeiculo());
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir multa: " + e.getMessage(), e);
		}
	}

	public List<Multa> listar() {
		List<Multa> multas = new ArrayList<>();
		try {
			String sql = "CALL listar_multas();";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Multa multa = new Multa();
				multa.setIdMulta(rs.getInt("idMulta"));
				multa.setMotivo(rs.getString("motivo"));
				multa.setValorMulta(rs.getDouble("valorMulta"));
				multa.setDataOcorrencia(rs.getDate("dataOcorrencia").toLocalDate());
				multa.setStatusMulta(rs.getString("statusMulta"));
				multa.setObservacoes(rs.getString("observacoes"));

				Veiculo veiculoMulta = new Veiculo();
				veiculoMulta.setChassi(rs.getString("chassi"));
				veiculoMulta.setPlaca(rs.getString("placa"));

				multa.setVeiculo(veiculoMulta);

				multas.add(multa);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar multas: " + e.getMessage(), e);
		}
		return multas;
	}

	public Multa consultar(Multa multa) {
		Multa multaConsultar = null;
		try {
			String sql = "CALL consultar_multa(?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, multa.getIdMulta());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				multaConsultar = new Multa();
				multaConsultar.setIdMulta(rs.getInt("idMulta"));
				multaConsultar.setMotivo(rs.getString("motivo"));
				multaConsultar.setValorMulta(rs.getDouble("valorMulta"));
				multaConsultar.setDataOcorrencia(rs.getDate("dataOcorrencia").toLocalDate());
				multaConsultar.setStatusMulta(rs.getString("statusMulta"));
				multaConsultar.setObservacoes(rs.getString("observacoes"));

				Veiculo veiculoMulta = new Veiculo();
				veiculoMulta.setChassi(rs.getString("chassi"));
				veiculoMulta.setPlaca(rs.getString("placa"));

				multaConsultar.setVeiculo(veiculoMulta);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao consultar multa: " + e.getMessage(), e);
		}
		return multaConsultar;
	}

	public void atualizar(Multa multa) {
		try {
			String sql = "CALL atualizar_multa(?, ?, ?, ?, ?,?,?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, multa.getIdMulta());
			statement.setString(2, multa.getMotivo());
			statement.setDouble(3, multa.getValorMulta());
			statement.setString(4, multa.getDataOcorrencia().toString());
			statement.setString(5, multa.getStatusMulta());
			statement.setString(6, multa.getObservacoes());
			statement.setInt(7, multa.getVeiculo().getIdVeiculo());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar multa: " + e.getMessage(), e);
		}
	}

	public void deletar(Multa multa) {
		try {
			String sql = "CALL deletar_multa(?);";
			PreparedStatement statement = connection.getConnection().prepareStatement(sql);
			statement.setInt(1, multa.getIdMulta());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar multa: " + e.getMessage(), e);
		}
	}
}
