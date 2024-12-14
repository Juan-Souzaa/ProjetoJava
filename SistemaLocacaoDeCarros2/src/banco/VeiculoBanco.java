package banco;

import java.sql.*;
import java.time.LocalDate;
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
            String sql = "CALL inserir_veiculo(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
                 veiculo.setAno(rs.getObject(5, LocalDate.class));
                 veiculo.setQuilometragem(rs.getDouble(6));
                 veiculo.setStatusDisponibilidade(rs.getBoolean(7));
                 veiculo.setCategoria(rs.getString(8));
                 veiculo.setSeguroAtivo(rs.getBoolean(9));
                 veiculo.setMarca(rs.getString(10));
                 veiculo.setDataUltimaManutencao(rs.getObject(11, LocalDate.class));
                 veiculos.add(veiculo);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar veículos: " + e.getMessage(), e);
        }
        return veiculos;
    }

    public Veiculo consultar(String idVeiculo) {
        Veiculo veiculo = null;
        try {
            String sql = "CALL consultar_veiculo(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, idVeiculo);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                veiculo = new Veiculo();
                		Veiculo veiculoConsulta = new Veiculo(); 
                        veiculoConsulta.setIdVeiculo(rs.getInt(1));
                        veiculoConsulta.setPlaca(rs.getString(2));
                        veiculoConsulta.setChassi(rs.getString(3));
                        veiculoConsulta.setCor(rs.getString(4));
                        veiculoConsulta.setAno(rs.getObject(5, LocalDate.class));
                        veiculoConsulta.setQuilometragem(rs.getDouble(6));
                        veiculoConsulta.setStatusDisponibilidade(rs.getBoolean(7));
                        veiculoConsulta.setCategoria(rs.getString(8));
                        veiculoConsulta.setSeguroAtivo(rs.getBoolean(9));
                        veiculoConsulta.setMarca(rs.getString(10));
                        veiculoConsulta.setDataUltimaManutencao(rs.getObject(11, LocalDate.class));
                        
                   }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar veículo: " + e.getMessage(), e);
        }
        return veiculo;
    }

    public void atualizar(Veiculo veiculo) {
        try {
            String sql = "CALL atualizar_veiculo(?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar veículo: " + e.getMessage(), e);
        }
    }

    public void deletar(Integer idVeiculo) {
        try {
            String sql = "CALL deletar_veiculo(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, idVeiculo);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar veículo: " + e.getMessage(), e);
        }
    }
}
