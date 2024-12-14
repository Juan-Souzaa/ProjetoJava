package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Pagamento;

public class PagamentoBanco {
    private DBConnection connection;

    public PagamentoBanco() {
        this.connection = new DBConnection();
    }

    public void incluir(Pagamento pagamento) {
        try {
            String sql = "CALL inserir_pagamento(?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, pagamento.getIdPagamento());
            statement.setDouble(2, pagamento.getValor());
            statement.setString(3, pagamento.getMetodoPagamento());
            statement.setString(4, pagamento.getDataPagamento().toString());
            statement.setString(5, pagamento.getStatus());
            statement.setString(6, pagamento.getDescricao());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir pagamento: " + e.getMessage(), e);
        }
    }

    public List<Pagamento> listar() {
        List<Pagamento> pagamentos = new ArrayList<>();
        try {
            String sql = "CALL listar_pagamentos();";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Pagamento pagamento = new Pagamento();
                   pagamento.setIdPagamento(rs.getString("idPagamento")); 
                    pagamento.setValor(rs.getDouble("valor"));
                    pagamento.setMetodoPagamento(rs.getString("metodoPagamento"));
                    pagamento.setDataPagamento(rs.getDate("dataPagamento").toLocalDate());
                    pagamento.setStatus(rs.getString("status"));
                    pagamento.setDescricao(rs.getString("descricao"));
                    
              
                pagamentos.add(pagamento);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pagamentos: " + e.getMessage(), e);
        }
        return pagamentos;
    }

    public Pagamento consultar(Integer idPagamento) {
        Pagamento pagamento = null;
        try {
            String sql = "CALL consultar_pagamento(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, idPagamento);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
            	 pagamento = new Pagamento();
                 pagamento.setIdPagamento(rs.getString("idPagamento")); 
                  pagamento.setValor(rs.getDouble("valor"));
                  pagamento.setMetodoPagamento(rs.getString("metodoPagamento"));
                  pagamento.setDataPagamento(rs.getDate("dataPagamento").toLocalDate());
                  pagamento.setStatus(rs.getString("status"));
                  pagamento.setDescricao(rs.getString("descricao"));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar pagamento: " + e.getMessage(), e);
        }
        return pagamento;
    }

    public void atualizar(Pagamento pagamento) {
        try {
            String sql = "CALL atualizar_pagamento(?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, pagamento.getIdPagamento());
            statement.setDouble(2, pagamento.getValor());
            statement.setString(3, pagamento.getMetodoPagamento());
            statement.setString(4, pagamento.getDataPagamento().toString());
            statement.setString(5, pagamento.getStatus());
            statement.setString(6, pagamento.getDescricao());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar pagamento: " + e.getMessage(), e);
        }
    }

    public void deletar(Integer idPagamento) {
        try {
            String sql = "CALL deletar_pagamento(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, idPagamento);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar pagamento: " + e.getMessage(), e);
        }
    }
}
