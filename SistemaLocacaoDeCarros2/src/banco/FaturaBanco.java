package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Fatura;
import model.Locacao;
import model.Reserva;

public class FaturaBanco {
    private DBConnection connection;

    public FaturaBanco() {
        this.connection = new DBConnection();
    }

    public void incluir(Fatura fatura) {
        try {
            String sql = "CALL inserir_fatura(?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            
            statement.setString(1, fatura.getDataEmissao().toString());
            statement.setDouble(2, fatura.getValorTotal());
            statement.setString(3, fatura.getObservacoes());
            statement.setInt(4, fatura.getLocacaoFatura().getIdLocacao());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir fatura: " + e.getMessage(), e);
        }
    }

    public List<Fatura> listar() {
        List<Fatura> faturas = new ArrayList<>();
        try {
            String sql = "CALL listar_faturas();";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Fatura fatura = new Fatura();
                    fatura.setNumeroFatura(rs.getInt("idFatura"));
                    fatura.setDataEmissao(rs.getDate("dataEmissao").toLocalDate());
                    fatura.setValorTotal(rs.getDouble("valorTotal"));
                    fatura.setObservacoes(rs.getString("observacoes"));
                    
                    Reserva reserva = new Reserva();
    				Cliente cliente = new Cliente();
    				cliente.setNomeCompleto(rs.getString("nomeCompleto"));
    				reserva.setClienteReserva(cliente);
    				
    				
    				
    				
    				
                    
                    Locacao locacao = new Locacao();
                    locacao.setReservaLocacao(reserva);
                    locacao.setIdLocacao(rs.getInt("idLocacao")); 
                    fatura.setLocacaoFatura(locacao);

                 
               
                faturas.add(fatura);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar faturas: " + e.getMessage(), e);
        }
        return faturas;
    }

    public Fatura consultar(String numeroFatura) {
        Fatura fatura = null;
        try {
            String sql = "CALL consultar_fatura(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, numeroFatura);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                fatura = new Fatura();
                   fatura.setNumeroFatura(rs.getInt("numeroFatura")); 
                    fatura.setDataEmissao(rs.getDate("dataEmissao").toLocalDate());
                    fatura.setValorTotal(rs.getDouble("valorTotal"));
                    fatura.setObservacoes(rs.getString("observacoes"));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar fatura: " + e.getMessage(), e);
        }
        return fatura;
    }

    public void atualizar(Fatura fatura) {
        try {
            String sql = "CALL atualizar_fatura(?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, fatura.getNumeroFatura());
            statement.setString(2, fatura.getDataEmissao().toString());
            statement.setDouble(3, fatura.getValorTotal());
            statement.setString(4, fatura.getObservacoes());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar fatura: " + e.getMessage(), e);
        }
    }

    public void deletar(int numeroFatura) {
        try {
            String sql = "CALL deletar_fatura(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, numeroFatura);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar fatura: " + e.getMessage(), e);
        }
    }
}
