package banco;

import model.Cliente;
import model.Locacao;
import model.Reserva;
import model.Veiculo;

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
            String sql = "CALL inserir_locacao(?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
           
            statement.setString(1, locacao.getDataLocacao().toString());
            statement.setString(2, locacao.getDataDevolucaoPrevista().toString());
            statement.setString(3, locacao.getDataDevolucaoReal().toString());
            statement.setDouble(4, locacao.getValorTotal());
            statement.setString(5, locacao.getTipoLocacao());
            statement.setString(6, locacao.getObservacoes());
            
            statement.setInt(7, locacao.getVeiculoLocacao().getIdVeiculo());
            statement.setInt(8, locacao.getReservaLocacao().getIdReserva());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir locação: " + e.getMessage(), e);
        }
    }

    public List<Locacao> listar() {
        List<Locacao> locacoes = new ArrayList<>();
        try {
            String sql = "CALL listar_locacoes();"; // Chama a procedure para listar locações
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                // Criando uma nova instância de Locacao
                Locacao locacao = new Locacao();
                
                // Dados principais da locação
                locacao.setIdLocacao(rs.getInt("idLocacao"));
                locacao.setDataLocacao(rs.getDate("dataLocacao").toLocalDate());
                locacao.setDataDevolucaoPrevista(rs.getDate("dataDevolucaoPrevista").toLocalDate());
                locacao.setDataDevolucaoReal(rs.getDate("dataDevolucaoReal") != null 
                    ? rs.getDate("dataDevolucaoReal").toLocalDate() : null);
                locacao.setValorTotal(rs.getDouble("valorTotal"));
                locacao.setTipoLocacao(rs.getString("tipoLocacao"));
                locacao.setObservacoes(rs.getString("observacoes"));
                
                // Criando e associando a reserva à locação
                Reserva reserva = new Reserva();
                
                reserva.setDataReserva(rs.getDate("dataReserva").toLocalDate());
                
                reserva.setDataRetirada(rs.getDate("dataRetirada").toLocalDate());
               
               
                
                // Adicionando cliente à reserva (cliente vem de usuário)
                Cliente cliente = new Cliente();
                cliente.setNomeCompleto(rs.getString("nomeCompletoCliente"));
                reserva.setClienteReserva(cliente); // Atribuindo o cliente à reserva
                
                // Adicionando a reserva à locação
                locacao.setReservaLocacao(reserva);
                
                // Criando e associando o veículo à locação
                Veiculo veiculo = new Veiculo();
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setPlaca(rs.getString("veiculoPlaca"));
                veiculo.setCategoria(rs.getString("veiculoCategoria"));
                locacao.setVeiculoLocacao(veiculo); // Associando o veículo à locação
                
                // Adicionando a locação à lista
                locacoes.add(locacao);
            }
            
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar locações: " + e.getMessage(), e);
        }
        
        return locacoes;
    }


    public Locacao consultar(Locacao locacao) {
        Locacao locacaoConsultar = null;
        try {
            String sql = "CALL consultar_locacao(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, locacao.getIdLocacao());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
            	locacaoConsultar = new Locacao();
            	locacaoConsultar.setIdLocacao(rs.getInt("idLocacao"));
                locacaoConsultar.setDataLocacao(rs.getDate("dataLocacao").toLocalDate());
                locacaoConsultar.setDataDevolucaoPrevista(rs.getDate("dataDevolucaoPrevista").toLocalDate());
                locacaoConsultar.setDataDevolucaoReal(rs.getDate("dataDevolucaoReal").toLocalDate());
                locacaoConsultar.setValorTotal(rs.getDouble("valorTotal"));
                locacaoConsultar.setTipoLocacao(rs.getString("tipoLocacao"));
                locacaoConsultar.setObservacoes(rs.getString("observacoes"));
               
                Reserva reserva = new Reserva();
                
                reserva.setDataReserva(rs.getDate("dataReserva").toLocalDate());
                
                reserva.setDataRetirada(rs.getDate("dataRetirada").toLocalDate());
               
               
                
                
                Cliente cliente = new Cliente();
                cliente.setNomeCompleto(rs.getString("nomeCompleto"));
                reserva.setClienteReserva(cliente); 
                
               
                locacaoConsultar.setReservaLocacao(reserva);
                
               
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("veiculoPlaca"));
                veiculo.setCategoria(rs.getString("veiculoCategoria"));
                locacaoConsultar.setVeiculoLocacao(veiculo); 
                
               
             
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar locação: " + e.getMessage(), e);
        }
        return locacaoConsultar;
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
            statement.setInt(8, locacao.getReservaLocacao().getIdReserva());
            statement.setInt(9, locacao.getVeiculoLocacao().getIdVeiculo());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar locação: " + e.getMessage(), e);
        }
    }

    public void deletar(Locacao locacao) {
        try {
            String sql = "CALL deletar_locacao(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, locacao.getIdLocacao());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar locação: " + e.getMessage(), e);
        }
    }
}
