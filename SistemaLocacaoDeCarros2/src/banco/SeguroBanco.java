package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            statement.setString(1, seguro.getIdSeguro());
            statement.setString(2, seguro.getTipoSeguro());
            statement.setDouble(3, seguro.getValorCobertura());
            statement.setDouble(4, seguro.getFranquia());
            
            statement.setString(5, seguro.getVigencia().toString());
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
                Seguro seguro = new Seguro(
                    rs.getString("idSeguro"),
                    rs.getString("tipoSeguro"),
                    rs.getDouble("valorCobertura"),
                    rs.getDouble("franquia"),
                    rs.getDate("vigencia").toLocalDate() 
                );
                seguros.add(seguro);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar seguros: " + e.getMessage(), e);
        }
        return seguros;
    }

    public Seguro consultar(String idSeguro) {
        Seguro seguro = null;
        try {
            String sql = "CALL consultar_seguro(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, idSeguro);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                seguro = new Seguro(
                    rs.getString("idSeguro"),
                    rs.getString("tipoSeguro"),
                    rs.getDouble("valorCobertura"),
                    rs.getDouble("franquia"),
                    rs.getDate("vigencia").toLocalDate() 
                );
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
            String sql = "CALL atualizar_seguro(?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, seguro.getIdSeguro());
            statement.setString(2, seguro.getTipoSeguro());
            statement.setDouble(3, seguro.getValorCobertura());
            statement.setDouble(4, seguro.getFranquia());
            
            statement.setString(5, seguro.getVigencia().toString());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar seguro: " + e.getMessage(), e);
        }
    }

    public void deletar(String idSeguro) {
        try {
            String sql = "CALL deletar_seguro(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, idSeguro);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar seguro: " + e.getMessage(), e);
        }
    }
}
