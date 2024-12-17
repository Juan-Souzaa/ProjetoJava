package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Devolucao;
import model.Locacao;
import model.Reserva;

public class DevolucaoBanco  {
    private DBConnection connection;

    public DevolucaoBanco() {
        this.connection = new DBConnection();
    }

    
    public void incluir(Devolucao devolucao) {
        try {
            String sql = "CALL inserir_devolucao(?, ?, ?, ?,?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, devolucao.getDataDevolucao().toString()); 
            statement.setString(2, devolucao.getCondicaoVeiculo());
            statement.setDouble(3, devolucao.getTaxaAtraso());
            statement.setBoolean(4, devolucao.getStatusDevolucao());
            statement.setInt(5, devolucao.getLocacaoDevolucao().getIdLocacao());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir devolução: " + e.getMessage(), e);
        }
    }

   
    public List<Devolucao> listar() {
        List<Devolucao> devolucoes = new ArrayList<>();
        try {
            String sql = "CALL listar_devolucoes();";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
            	
            	Reserva reserva = new Reserva();
				Cliente cliente = new Cliente();
				cliente.setIdUsuario(rs.getInt("idUsuario"));
				cliente.setNomeCompleto(rs.getString("nomeCompleto"));
				reserva.setClienteReserva(cliente);

				Locacao locacao = new Locacao();
				locacao.setIdLocacao(rs.getInt("idLocacao"));
				locacao.setReservaLocacao(reserva);
            	
                Devolucao devolucao = new Devolucao();
                	devolucao.setIdDevolucao(rs.getInt("idDevolucao"));
                    devolucao.setDataDevolucao(rs.getDate("dataDevolucao").toLocalDate());
                    devolucao.setCondicaoVeiculo(rs.getString("condicaoVeiculo"));
                    devolucao.setTaxaAtraso(rs.getDouble("taxaAtraso"));
                    devolucao.setStatusDevolucao(rs.getBoolean("statusDevolucao"));
                    devolucao.setLocacaoDevolucao(locacao);
                     
                devolucoes.add(devolucao);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar devoluções: " + e.getMessage(), e);
        }
        return devolucoes;
    }

    public Devolucao consultar(Devolucao devolucao) {
        Devolucao devolucaoConsulta = null;
        try {
            String sql = "CALL consultar_devolucao(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, devolucao.getIdDevolucao());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
            	
            	Reserva reserva = new Reserva();
				Cliente cliente = new Cliente();
				cliente.setIdUsuario(rs.getInt("idUsuario"));
				cliente.setNomeCompleto(rs.getString("nomeCompleto"));
				reserva.setClienteReserva(cliente);

				Locacao locacao = new Locacao();
				locacao.setIdLocacao(rs.getInt("idLocacao"));
				locacao.setReservaLocacao(reserva);
				
            	devolucaoConsulta = new Devolucao();
            	devolucaoConsulta.setIdDevolucao(rs.getInt("idDevolucao"));
                devolucaoConsulta.setDataDevolucao(rs.getDate("dataDevolucao").toLocalDate());
                devolucaoConsulta.setCondicaoVeiculo(rs.getString("condicaoVeiculo"));
                devolucaoConsulta.setTaxaAtraso(rs.getDouble("taxaAtraso"));
                devolucaoConsulta.setStatusDevolucao(rs.getBoolean("statusDevolucao"));
                devolucaoConsulta.setLocacaoDevolucao(locacao);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar devolução: " + e.getMessage(), e);
        }
        return devolucaoConsulta;
    }

    
    public void atualizar(Devolucao devolucao) {
        try {
            String sql = "CALL atualizar_devolucao(?, ?, ?, ?,?,?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            
            
            statement.setInt(1, devolucao.getIdDevolucao());
            statement.setString(2,devolucao.getDataDevolucao().toString());  
            statement.setString(3, devolucao.getCondicaoVeiculo());
            statement.setDouble(4, devolucao.getTaxaAtraso());
            statement.setBoolean(5, devolucao.getStatusDevolucao());
            statement.setInt(6, devolucao.getLocacaoDevolucao().getIdLocacao());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar devolução: " + e.getMessage(), e);
        }
    }

    public void deletar(Devolucao devolucao) {
        try {
            String sql = "CALL deletar_devolucao(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, devolucao.getIdDevolucao());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar devolução: " + e.getMessage(), e);
        }
    }
}

