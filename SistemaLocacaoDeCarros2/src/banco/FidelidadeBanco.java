package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Fidelidade;

public class FidelidadeBanco {
    private DBConnection connection;

    public FidelidadeBanco() {
        this.connection = new DBConnection();
    }

    public void incluir(Fidelidade fidelidade) {
        try {
            String sql = "CALL inserir_fidelidade(?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            
            statement.setInt(1, fidelidade.getPontos());
            statement.setString(2, fidelidade.getNivel());
            statement.setString(3, fidelidade.getDataUltimaAtualizacao().toString());
            statement.setInt(4, fidelidade.getClienteFidelidade().getIdUsuario());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir fidelidade: " + e.getMessage(), e);
        }
    }

    public List<Fidelidade> listar() {
        List<Fidelidade> fidelidades = new ArrayList<>();
        try {
            String sql = "CALL listar_fidelidades();";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Fidelidade fidelidade = new Fidelidade();
                   fidelidade.setIdFidelidade(rs.getInt("idFidelidade"));
                   fidelidade.setPontos(rs.getInt("pontos"));
                   fidelidade.setNivel(rs.getString("nivel"));
                   fidelidade.setDataUltimaAtualizacao(rs.getDate("dataUltimaAtualizacao").toLocalDate()); 
                   
                   Cliente cliente = new Cliente();
                   cliente.setIdUsuario(rs.getInt("idUsuario"));
                   cliente.setNomeCompleto(rs.getString("nomeCompleto"));
                   
                   fidelidade.setClienteFidelidade(cliente);
               
                fidelidades.add(fidelidade);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar fidelidades: " + e.getMessage(), e);
        }
        return fidelidades;
    }

    public Fidelidade consultar(Fidelidade fidelidade) {
        Fidelidade fidelidadeConsultar = null;
        try {
            String sql = "CALL consultar_fidelidade(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, fidelidade.getIdFidelidade());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
            	fidelidadeConsultar = new Fidelidade();
                fidelidadeConsultar.setIdFidelidade(rs.getInt("idFidelidade"));
                fidelidadeConsultar.setPontos(rs.getInt("pontos"));
                fidelidadeConsultar.setNivel(rs.getString("nivel"));
                fidelidadeConsultar.setDataUltimaAtualizacao(rs.getDate("dataUltimaAtualizacao").toLocalDate()); 
                
                Cliente cliente = new Cliente();
                cliente.setIdUsuario(rs.getInt("idUsuario"));
                cliente.setNomeCompleto(rs.getString("nomeCompleto"));
                
                fidelidadeConsultar.setClienteFidelidade(cliente);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar fidelidade: " + e.getMessage(), e);
        }
        return fidelidadeConsultar;
    }

    public void atualizar(Fidelidade fidelidade) {
        try {
            String sql = "CALL atualizar_fidelidade(?, ?, ?, ?,?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, fidelidade.getIdFidelidade());
            statement.setInt(2, fidelidade.getPontos());
            statement.setString(3, fidelidade.getNivel());
            statement.setString(4, fidelidade.getDataUltimaAtualizacao().toString());
            statement.setInt(5, fidelidade.getClienteFidelidade().getIdUsuario());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar fidelidade: " + e.getMessage(), e);
        }
    }

    public void deletar(Fidelidade fidelidade) {
        try {
            String sql = "CALL deletar_fidelidade(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, fidelidade.getIdFidelidade());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar fidelidade: " + e.getMessage(), e);
        }
    }
}
