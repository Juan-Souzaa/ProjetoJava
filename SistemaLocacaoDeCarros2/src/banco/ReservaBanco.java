package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Reserva;

public class ReservaBanco {
    private DBConnection connection;

    public ReservaBanco() {
        this.connection = new DBConnection();
    }

    public void incluir(Reserva reserva) {
        try {
            String sql = "CALL inserir_reserva(?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, reserva.getDataReserva().toString());
            statement.setString(2, reserva.getStatusReserva());
            statement.setString(3, reserva.getDataRetirada().toString());
            statement.setString(4, reserva.getDataDevolucao().toString());
            statement.setString(5, reserva.getObservacoes());
            statement.setInt(6, reserva.getClienteReserva().getIdCliente());
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
                Reserva reserva = new Reserva(
                    rs.getInt("idReserva"),
                    rs.getDate("dataReserva").toLocalDate(),
                    rs.getString("statusReserva"),
                    rs.getDate("dataRetirada").toLocalDate(),
                    rs.getDate("dataDevolucao").toLocalDate(),
                    rs.getString("observacoes")
                );
                reservas.add(reserva);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar reservas: " + e.getMessage(), e);
        }
        return reservas;
    }

    public Reserva consultar(int idReserva) {
        Reserva reserva = null;
        try {
            String sql = "CALL consultar_reserva(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, idReserva);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                reserva = new Reserva(
                    rs.getInt("idReserva"),
                    rs.getDate("dataReserva").toLocalDate(),
                    rs.getString("statusReserva"),
                    rs.getDate("dataRetirada").toLocalDate(),
                    rs.getDate("dataDevolucao").toLocalDate(),
                    rs.getString("observacoes")
                );
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar reserva: " + e.getMessage(), e);
        }
        return reserva;
    }

    public void atualizar(Reserva reserva) {
        try {
            String sql = "CALL atualizar_reserva(?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, reserva.getDataReserva().toString());
            statement.setString(2, reserva.getStatusReserva());
            statement.setString(3, reserva.getDataRetirada().toString());
            statement.setString(4, reserva.getDataDevolucao().toString());
            statement.setString(5, reserva.getObservacoes());
            statement.setInt(6, reserva.getClienteReserva().getIdCliente());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar reserva: " + e.getMessage(), e);
        }
    }

    public void deletar(int idReserva) {
        try {
            String sql = "CALL deletar_reserva(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, idReserva);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar reserva: " + e.getMessage(), e);
        }
    }
}
