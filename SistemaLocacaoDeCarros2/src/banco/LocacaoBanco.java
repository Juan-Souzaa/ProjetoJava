package banco;

import model.Cliente;
import model.Locacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocacaoBanco {
    private DBConnection connection;

    public LocacaoBanco() {
        this.connection = new DBConnection();
    }

    public void incluir(Locacao locacao) {
        try {
            String sql = "CALL inserir_locacao(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, locacao.getIdLocacao());
            statement.setString(2, locacao.getDataLocacao().toString());
            statement.setString(3, locacao.getDataDevolucaoPrevista().toString());
            statement.setString(4, locacao.getDataDevolucaoReal().toString());
            statement.setDouble(5, locacao.getValorTotal());
            statement.setString(6, locacao.getTipoLocacao());
            statement.setString(7, locacao.getObservacoes());
            statement.setInt(8, locacao.getClienteLocacao().getIdCliente());
            statement.setInt(9, locacao.getVeiculoLocacao().getIdVeiculo());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir locação: " + e.getMessage(), e);
        }
    }

    public List<Locacao> listar() {
        List<Locacao> locacoes = new ArrayList<>();
        try {
            String sql = "CALL listar_locacoes();";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Locacao locacao = new Locacao();
                    locacao.setIdLocacao(rs.getInt("idLocacao"));
                    locacao.setDataLocacao(rs.getDate("dataLocacao").toLocalDate());
                    locacao.setDataDevolucaoPrevista(rs.getDate("dataDevolucaoPrevista").toLocalDate());
                    locacao.setDataDevolucaoReal(rs.getDate("dataDevolucaoReal").toLocalDate());
                    locacao.setValorTotal(rs.getDouble("valorTotal"));
                    locacao.setTipoLocacao(rs.getString("tipoLocacao"));
                    locacao.setObservacoes(rs.getString("observacoes"));
                    locacao.setClienteLocacao((Cliente) rs.getObject("clienteLocacao"));
                locacoes.add(locacao);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar locações: " + e.getMessage(), e);
        }
        return locacoes;
    }

    public Locacao consultar(int idLocacao) {
        Locacao locacao = null;
        try {
            String sql = "CALL consultar_locacao(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, idLocacao);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
            	locacao = new Locacao();
            	locacao.setIdLocacao(rs.getInt("idLocacao"));
                locacao.setDataLocacao(rs.getDate("dataLocacao").toLocalDate());
                locacao.setDataDevolucaoPrevista(rs.getDate("dataDevolucaoPrevista").toLocalDate());
                locacao.setDataDevolucaoReal(rs.getDate("dataDevolucaoReal").toLocalDate());
                locacao.setValorTotal(rs.getDouble("valorTotal"));
                locacao.setTipoLocacao(rs.getString("tipoLocacao"));
                locacao.setObservacoes(rs.getString("observacoes"));
                locacao.setClienteLocacao((Cliente) rs.getObject("clienteLocacao"));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar locação: " + e.getMessage(), e);
        }
        return locacao;
    }

    public void atualizar(Locacao locacao) {
        try {
            String sql = "CALL atualizar_locacao(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, locacao.getIdLocacao());
            statement.setString(2, locacao.getDataLocacao().toString());
            statement.setString(3, locacao.getDataDevolucaoPrevista().toString());
            statement.setString(4, locacao.getDataDevolucaoReal().toString());
            statement.setDouble(5, locacao.getValorTotal());
            statement.setString(6, locacao.getTipoLocacao());
            statement.setString(7, locacao.getObservacoes());
            statement.setInt(8, locacao.getClienteLocacao().getIdCliente());
            statement.setInt(9, locacao.getVeiculoLocacao().getIdVeiculo());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar locação: " + e.getMessage(), e);
        }
    }

    public void deletar(int idLocacao) {
        try {
            String sql = "CALL deletar_locacao(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, idLocacao);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar locação: " + e.getMessage(), e);
        }
    }
}
