package banco;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import model.Veiculo;

public class VeiculoBanco {
    private DBConnection connection;

    public VeiculoBanco() {
        this.connection = new DBConnection();
    }

    public void incluir(Veiculo veiculo) {
        try {
            String sql = "CALL inserir_veiculo( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
           
            statement.setString(1, veiculo.getPlaca());
            statement.setString(2, veiculo.getChassi());
            statement.setString(3, veiculo.getCor());
            statement.setString(4, veiculo.getAno().toString());
            statement.setDouble(5, veiculo.getQuilometragem());
            statement.setBoolean(6, veiculo.isStatusDisponibilidade());
            statement.setString(7, veiculo.getCategoria());
            statement.setBoolean(8, veiculo.isSeguroAtivo());
            statement.setString(9, veiculo.getMarca());
            statement.setString(10, veiculo.getDataUltimaManutencao().toString());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir veículo: " + e.getMessage(), e);
        }
    }

    public List<Veiculo> listar() {
        List<Veiculo> veiculos = new ArrayList<>();
        try {
            String sql = "CALL listar_veiculos();";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
            	 Veiculo veiculo = new Veiculo(); 
                 veiculo.setIdVeiculo(rs.getInt(1));
                 veiculo.setPlaca(rs.getString(2));
                 veiculo.setChassi(rs.getString(3));
                 veiculo.setCor(rs.getString(4));
                 veiculo.setAno(rs.getDate(5).toLocalDate());
                 veiculo.setQuilometragem(rs.getDouble(6));
                 veiculo.setStatusDisponibilidade(rs.getBoolean(7));
                 veiculo.setCategoria(rs.getString(8));
                 veiculo.setSeguroAtivo(rs.getBoolean(9));
                 veiculo.setMarca(rs.getString(10));
                 veiculo.setDataUltimaManutencao(rs.getDate(11).toLocalDate());
                 veiculos.add(veiculo);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar veículos: " + e.getMessage(), e);
        }
        return veiculos;
    }

    public Veiculo consultar(Veiculo veiculo) {
        Veiculo veiculoConsultar = null;
        try {
            String sql = "CALL consultar_veiculo(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, veiculo.getIdVeiculo());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                veiculoConsultar = new Veiculo();
                		 
                        veiculoConsultar.setIdVeiculo(rs.getInt("idveiculo"));
                        veiculoConsultar.setPlaca(rs.getString("placa"));
                        veiculoConsultar.setChassi(rs.getString("chassi"));
                        veiculoConsultar.setCor(rs.getString("cor"));
                        veiculoConsultar.setAno(rs.getDate("ano").toLocalDate());
                        veiculoConsultar.setQuilometragem(rs.getDouble("quilometragem"));
                        veiculoConsultar.setStatusDisponibilidade(rs.getBoolean("statusDisponibilidade"));
                        veiculoConsultar.setCategoria(rs.getString("categoria"));
                        veiculoConsultar.setSeguroAtivo(rs.getBoolean("seguroAtivo"));
                        veiculoConsultar.setMarca(rs.getString("marca"));
                        veiculoConsultar.setDataUltimaManutencao(rs.getDate("dataUltimaManutencao").toLocalDate());
                        
                   }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar veículo: " + e.getMessage(), e);
        }
        return veiculoConsultar;
    }

    public void atualizar(Veiculo veiculo) {
        try {
            String sql = "CALL atualizar_veiculo(?, ?, ?, ?, ?, ?, ?, ?, ?,?,?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, veiculo.getIdVeiculo());
            statement.setString(2, veiculo.getPlaca());
            statement.setString(3, veiculo.getChassi());
            statement.setString(4, veiculo.getCor());
            statement.setString(5, veiculo.getAno().toString());
            statement.setDouble(6, veiculo.getQuilometragem());
            statement.setBoolean(7, veiculo.isStatusDisponibilidade());
            statement.setString(8, veiculo.getCategoria());
            statement.setBoolean(9, veiculo.isSeguroAtivo());
            statement.setString(10, veiculo.getMarca());
            statement.setString(11, veiculo.getDataUltimaManutencao().toString());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar veículo: " + e.getMessage(), e);
        }
    }

    public void deletar(Veiculo veiculo) {
        try {
            String sql = "CALL deletar_veiculo(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, veiculo.getIdVeiculo());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar veículo: " + e.getMessage(), e);
        }
    }
}
