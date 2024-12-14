package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Balconista;

public class BalconistaBanco extends UsuarioBanco{
    private DBConnection connection;

    public BalconistaBanco() {
        this.connection = new DBConnection();
    }

    
    public void incluir(Balconista balconista) {
        try {
            String sql = "CALL inserir_balconista(?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, balconista.getMatricula());
            statement.setString(2, balconista.getTurno());
            
            statement.setString(4, balconista.getFilial());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir balconista: " + e.getMessage(), e);
        }
    }

    public List<Balconista> listar() {
        List<Balconista> balconistas = new ArrayList<>();
        try {
            String sql = "CALL listar_balconistas();";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Balconista balconista = new Balconista();
                balconista.setMatricula(rs.getString("matricula"));
                balconista.setTurno(rs.getString("turno"));
                
                balconista.setFilial(rs.getString("filial"));
                balconistas.add(balconista);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar balconistas: " + e.getMessage(), e);
        }
        return balconistas;
    }

    // Consultar balconista por matrícula
    public Balconista consultar(String matricula) {
        Balconista balconista = null;
        try {
            String sql = "CALL consultar_balconista(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, matricula);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                balconista = new Balconista();
                balconista.setMatricula(rs.getString("matricula"));
                balconista.setTurno(rs.getString("turno"));
               
                balconista.setFilial(rs.getString("filial"));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar balconista: " + e.getMessage(), e);
        }
        return balconista;
    }

    
    public void atualizar(Balconista balconista) {
        try {
            String sql = "CALL atualizar_balconista(?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, balconista.getMatricula());
            statement.setString(2, balconista.getTurno());
 
            statement.setString(4, balconista.getFilial());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar balconista: " + e.getMessage(), e);
        }
    }

    
    public void deletar(String matricula) {
        try {
            String sql = "CALL deletar_balconista(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, matricula);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar balconista: " + e.getMessage(), e);
        }
    }
}
