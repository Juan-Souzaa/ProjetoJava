package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Manutencao;
import model.Veiculo;

public class ManutencaoBanco {
    private DBConnection connection;

    public ManutencaoBanco() {
        this.connection = new DBConnection();
    }

    public void incluir(Manutencao manutencao) {
        try {
            String sql = "CALL inserir_manutencao(?, ?, ?, ?,?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, manutencao.getVeiculoManutencao().getIdVeiculo());
            statement.setString(2, manutencao.getDataManutencao().toString());
            statement.setString(3, manutencao.getTipoManutencao());
            statement.setDouble(4, manutencao.getCusto());
            statement.setString(5,manutencao.getDescricao());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir manutenção: " + e.getMessage(), e);
        }
    }

    public List<Manutencao> listar() {
        List<Manutencao> manutencoes = new ArrayList<>();
        try {
            String sql = "CALL listar_manutencao();";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Manutencao manutencao = new Manutencao();
                   manutencao.setIdManutencao(rs.getInt("idManutencao")); 
                   manutencao.setDataManutencao(rs.getDate("dataManutencao").toLocalDate());
                   manutencao.setTipoManutencao(rs.getString("tipoManutencao")); 
                   manutencao.setCusto(rs.getDouble("custo"));
                   manutencao.setDescricao(rs.getString("descricao")); 
                   
                   Veiculo veiculoManutencao = new Veiculo();
                   veiculoManutencao.setMarca(rs.getString("marca"));
                   veiculoManutencao.setPlaca(rs.getString("placa"));
                   veiculoManutencao.setDataUltimaManutencao(rs.getDate("dataUltimaManutencao").toLocalDate());
                   
                   manutencao.setVeiculomanutencao(veiculoManutencao);
               
                manutencoes.add(manutencao);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar manutenções: " + e.getMessage(), e);
        }
        return manutencoes;
    }

    public Manutencao consultar(Manutencao manutencao) {
        Manutencao manutencaoConsultar = null;
        try {
            String sql = "CALL consultar_manutencao(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, manutencao.getIdManutencao());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
            	manutencaoConsultar = new Manutencao();
                manutencaoConsultar.setIdManutencao(rs.getInt("idManutencao")); 
                manutencaoConsultar.setDataManutencao(rs.getDate("dataManutencao").toLocalDate());
                manutencaoConsultar.setTipoManutencao(rs.getString("tipoManutencao")); 
                manutencaoConsultar.setCusto(rs.getDouble("custo"));
                manutencaoConsultar.setDescricao(rs.getString("descricao")); 
                
                Veiculo veiculoManutencao = new Veiculo();
                veiculoManutencao.setMarca(rs.getString("marca"));
                veiculoManutencao.setPlaca(rs.getString("placa"));
                veiculoManutencao.setDataUltimaManutencao(rs.getDate("dataUltimaManutencao").toLocalDate());
                
                manutencaoConsultar.setVeiculomanutencao(veiculoManutencao);
            
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar manutenção: " + e.getMessage(), e);
        }
        return manutencaoConsultar;
    }

    public void atualizar(Manutencao manutencao) {
        try {
            String sql = "CALL atualizar_manutencao(?, ?, ?, ?,?,?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
          
            statement.setInt(1, manutencao.getIdManutencao());
            statement.setString(2, manutencao.getDataManutencao().toString());
            statement.setString(3, manutencao.getTipoManutencao());
            statement.setDouble(4, manutencao.getCusto());
            statement.setString(5,manutencao.getDescricao());
            statement.setInt(6, manutencao.getVeiculoManutencao().getIdVeiculo());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar manutenção: " + e.getMessage(), e);
        }
    }

    public void deletar(Manutencao manutencao) {
        try {
            String sql = "CALL deletar_manutencao(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, manutencao.getIdManutencao());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar manutenção: " + e.getMessage(), e);
        }
    }
}
