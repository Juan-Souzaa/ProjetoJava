package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Usuario;

public class ClienteBanco extends UsuarioBanco {

	 private DBConnection connection;

	    public ClienteBanco() {
	        this.connection = new DBConnection();
	    }

	    public void incluir(Cliente cliente) {
	        
	     
	        try {
	        	 super.incluir(cliente); 
	  	      
	        	
	  	        
	            String sql = "CALL inserir_cliente( ?, ?,?);";
	            PreparedStatement statement = connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            
	            statement.setString(1, cliente.getCpf());
	            statement.setString(2, cliente.getDataNascimento().toString());
	            statement.setString(3, cliente.getCategoriaCNH());
	           

	            
	            statement.execute();
	            
	         
	           
	            
	            statement.close();
	        } catch (SQLException e) {
	            throw new RuntimeException("Erro ao inserir cliente: " + e.getMessage(), e);
	        }
	    }

    
    
	    public List<Cliente> listarclientes() {
	        List<Cliente> clientes = new ArrayList<>();
	        try {
	            String sql = "CALL listar_clientes();";
	            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
	            ResultSet rs = statement.executeQuery();
	            
	            while (rs.next()) {
	                Cliente cliente = new Cliente();
	                cliente.setIdUsuario(rs.getInt("idUsuario"));
	                cliente.setNomeCompleto(rs.getString("nomeCompleto"));
	                cliente.setEmail(rs.getString("email"));
	                cliente.setSenha(rs.getString("senha"));
	                cliente.setTelefone(rs.getString("telefone"));
	                cliente.setEndereco(rs.getString("endereco"));
	                cliente.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());
	                cliente.setNivelAcesso(rs.getString("nivelAcesso"));
	                cliente.setCpf(rs.getString("cpf"));
	                cliente.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
	                cliente.setCategoriaCNH(rs.getString("categoriaCNH"));
	                
	                clientes.add(cliente);
	            }
	            
	            rs.close();
	            statement.close();
	        } catch (SQLException e) {
	            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage(), e);
	        }
	        
	        return clientes;
	    }


    public Cliente consultar(int idUsuario) {
        Usuario usuario = super.consultar(idUsuario); 
        Cliente cliente = null;
        try {
            String sql = "CALL consultar_cliente(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, idUsuario);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setCpf(usuario.getCpf());
                cliente.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
                cliente.setCategoriaCNH(rs.getString("categoriaCNH"));
                cliente.setIdUsuario(usuario.getIdUsuario());
                cliente.setNomeCompleto(usuario.getNomeCompleto());
                cliente.setEmail(usuario.getEmail());
                cliente.setSenha(usuario.getSenha());
                cliente.setTelefone(usuario.getTelefone());
                cliente.setEndereco(usuario.getEndereco());
                cliente.setDataCadastro(usuario.getDataCadastro());
                
                cliente.setNivelAcesso(usuario.getNivelAcesso());
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar cliente: " + e.getMessage(), e);
        }
        return cliente;
    }

    public void atualizar(Cliente cliente) {
        super.atualizar(cliente); 
        try {
            String sql = "CALL atualizar_cliente(?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, cliente.getIdUsuario());
            
            statement.setString(2, cliente.getDataNascimento().toString());
            statement.setString(3, cliente.getCategoriaCNH());
           
            statement.executeUpdate();//////
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage(), e);
        }
    }

    public void deletar(int idUsuario) {
        
        try {
            String sql = "CALL deletar_cliente(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, idUsuario);
            statement.executeUpdate();
            statement.close();
            
            super.deletar(idUsuario); 
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cliente: " + e.getMessage(), e);
        }
    }
}
