package banco;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Cancelamento;
import model.Locacao;

public class CancelamentoBanco extends Locacao {
    private DBConnection connection;

    public CancelamentoBanco() {
        this.connection = new DBConnection();
    }

    public void incluir(Cancelamento cancelamento) {
        try {
            String sql = "CALL inserir_cancelamento(?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, cancelamento.getMotivoCancelamento());
            statement.setString(2, cancelamento.getDataCancelamento().toString());
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
                Cancelamento cancelamento = new Cancelamento();
                    cancelamento.setMotivoCancelamento(rs.getString("motivoCancelamento"));
                    cancelamento.setDataCancelamento(rs.getObject(3, LocalDate.class)); // Convertendo java.sql.Date para LocalDate
              
                cancelamentos.add(cancelamento);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar cancelamentos: " + e.getMessage(), e);
        }
        return cancelamentos;
    }

    public Cancelamento consultar(int idCancelamento) {
        Cancelamento cancelamento = null;
        try {
            String sql = "CALL consultar_cancelamento(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, idCancelamento);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                cancelamento = new Cancelamento();
                   cancelamento.setMotivoCancelamento(rs.getString("motivoCancelamento"));
                   cancelamento.setDataCancelamento(rs.getDate("dataCancelamento").toLocalDate()); 
                
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar cancelamento: " + e.getMessage(), e);
        }
        return cancelamento;
    }

    public void atualizar(Cancelamento cancelamento) {
        try {
            String sql = "CALL atualizar_cancelamento(?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, cancelamento.getMotivoCancelamento());
            
            statement.setString(2,cancelamento.getDataCancelamento().toString());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cancelamento: " + e.getMessage(), e);
        }
    }

    public void deletar(int idCancelamento) {
        try {
            String sql = "CALL deletar_cancelamento(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, idCancelamento);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cancelamento: " + e.getMessage(), e);
        }
    }
}
