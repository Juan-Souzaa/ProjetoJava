package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Retirada;

public class RetiradaBanco {
    private DBConnection connection;

    public RetiradaBanco() {
        this.connection = new DBConnection();
    }

    public void incluir(Retirada retirada) {
        try {
            String sql = "CALL inserir_retira(?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, retirada.getDataRetirada().toString());
            statement.setString(2, retirada.getLocalRetirada());
            statement.setBoolean(3, retirada.getDocumentosVerificados());
            statement.setString(4, retirada.getStatusRetirada());
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
                Retirada retirada = new Retirada();
                retirada.setDataRetirada(rs.getDate("dataRetirada").toLocalDate());
                retirada.setLocalRetirada(rs.getString("localRetirada"));
                retirada.setDocumentosVerificados(rs.getBoolean("documentosVerificados"));
                retirada.setStatusRetirada(rs.getString("statusRetirada"));
                retiradas.add(retirada);
            } rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar retiradas: " + e.getMessage(), e);
        }
        return retiradas;
    }

    public Retirada consultar(int idRetirada) {
        Retirada retirada = null;
        try {
            String sql = "CALL consultar_retira(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, idRetirada);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                retirada = new Retirada();
                retirada.setDataRetirada(rs.getDate("dataRetirada").toLocalDate());
                retirada.setLocalRetirada(rs.getString("localRetirada"));
                retirada.setDocumentosVerificados(rs.getBoolean("documentosVerificados"));
                retirada.setStatusRetirada(rs.getString("statusRetirada"));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar retirada: " + e.getMessage(), e);
        }
        return retirada;
    }
}
