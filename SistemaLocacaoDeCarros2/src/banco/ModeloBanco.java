package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Modelo;


public class ModeloBanco {
    private DBConnection connection;

    public ModeloBanco() {
        this.connection = new DBConnection();
    }

    public void incluir(Modelo modelo) {
        try {
            String sql = "CALL inserir_modelo(?, ?, ?, ?, ?, ?,?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, modelo.getNomeModelo());
            statement.setDouble(2, modelo.getValorDiaria());
            statement.setString(3, modelo.getCategoria());
            statement.setInt(4, modelo.getCapacidadePassageiros());
            statement.setString(5, modelo.getTipoCombustivel());
            statement.setDouble(6, modelo.getConsumoMedio());
            statement.setInt(7, modelo.getVeiculo().getIdVeiculo());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir modelo: " + e.getMessage(), e);
        }
    }

    public List<Modelo> listar() {
        List<Modelo> modelos = new ArrayList<>();
        try {
            String sql = "CALL listar_modelos();";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Modelo modelo = new Modelo();
                	modelo.setIdModelo(rs.getInt("idModelo"));
                    modelo.setNomeModelo(rs.getString("nomeModelo"));
                    modelo.setValorDiaria(rs.getDouble("valorDiaria"));
                    modelo.setCategoria(rs.getString("categoria"));
                    modelo.setCapacidadePassageiros(rs.getInt("capacidadePassageiros"));
                    modelo.setTipoCombustivel(rs.getString("tipoCombustivel"));
                    modelo.setConsumoMedio(rs.getDouble("consumoMedio"));
                   
                    
                   
               
                modelos.add(modelo);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar modelos: " + e.getMessage(), e);
        }
        return modelos;
    }

    public Modelo consultar(int nomeModelo) {
        Modelo modelo = null;
        try {
            String sql = "CALL consultar_modelo(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, nomeModelo);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
            	modelo = new Modelo();
            	modelo.setIdModelo(rs.getInt("idModelo"));
                modelo.setNomeModelo(rs.getString("nomeModelo"));
                modelo.setValorDiaria(rs.getDouble("valorDiaria"));
                modelo.setCategoria(rs.getString("categoria"));
                modelo.setCapacidadePassageiros(rs.getInt("capacidadePassageiros"));
                modelo.setTipoCombustivel(rs.getString("tipoCombustivel"));
                modelo.setConsumoMedio(rs.getDouble("consumoMedio"));
               
           
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar modelo: " + e.getMessage(), e);
        }
        return modelo;
    }

    public void atualizar(Modelo modelo) {
        try {
            String sql = "CALL atualizar_modelo(?, ?, ?, ?, ?, ?,?,?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, modelo.getIdModelo());
            statement.setString(2, modelo.getNomeModelo());
            statement.setDouble(3, modelo.getValorDiaria());
            statement.setString(4, modelo.getCategoria());
            statement.setInt(5, modelo.getCapacidadePassageiros());
            statement.setString(6, modelo.getTipoCombustivel());
            statement.setDouble(7, modelo.getConsumoMedio());
            statement.setInt(8, modelo.getVeiculo().getIdVeiculo());
            
            statement.executeUpdate();
            statement.close();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar modelo: " + e.getMessage(), e);
        }
    }

    public void deletar(int nomeModelo) {
        try {
            String sql = "CALL deletar_modelo(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, nomeModelo);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar modelo: " + e.getMessage(), e);
        }
    }
}
