package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Frota;

public class FrotaBanco {
    private DBConnection connection;

    public FrotaBanco() {
        this.connection = new DBConnection();
    }

    public void incluir(Frota frota) {
        try {
            String sql = "CALL inserir_frota(?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, frota.getTotalVeiculos());
            statement.setInt(2, frota.getTotalDisponiveis());
            statement.setInt(3, frota.getTotalEmManutencao());
            statement.setString(4, frota.getLocalizacao());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir frota: " + e.getMessage(), e);
        }
    }

    public List<Frota> listar() {
        List<Frota> frotas = new ArrayList<>();
        try {
            String sql = "CALL listar_frotas();";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Frota frota = new Frota();
                    frota.setTotalVeiculos(rs.getInt("totalVeiculos"));
                    frota.setTotalDisponiveis(rs.getInt("totalDisponiveis"));
                    frota.setTotalEmManutencao(rs.getInt("totalEmManutencao"));
                    frota.setLocalizacao(rs.getString("localizacao"));
               
                frotas.add(frota);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar frotas: " + e.getMessage(), e);
        }
        return frotas;
    }

    public Frota consultar(String localizacao) {
        Frota frota = null;
        try {
            String sql = "CALL consultar_frota(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, localizacao);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
            	frota = new Frota();
                frota.setTotalVeiculos(rs.getInt("totalVeiculos"));
                frota.setTotalDisponiveis(rs.getInt("totalDisponiveis"));
                frota.setTotalEmManutencao(rs.getInt("totalEmManutencao"));
                frota.setLocalizacao(rs.getString("localizacao"));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar frota: " + e.getMessage(), e);
        }
        return frota;
    }

    public void atualizar(Frota frota) {
        try {
            String sql = "CALL atualizar_frota(?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, frota.getTotalVeiculos());
            statement.setInt(2, frota.getTotalDisponiveis());
            statement.setInt(3, frota.getTotalEmManutencao());
            statement.setString(4, frota.getLocalizacao());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar frota: " + e.getMessage(), e);
        }
    }

    public void deletar(String localizacao) {
        try {
            String sql = "CALL deletar_frota(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, localizacao);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar frota: " + e.getMessage(), e);
        }
    }
}
