package banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioBanco {
    private DBConnection connection;

    public UsuarioBanco() {
        this.connection = new DBConnection();
    }

    public void incluir(Usuario usuario) {
        try {
            String sql = "CALL inserir_usuario(?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, usuario.getNomeCompleto());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, usuario.getTelefone());
            statement.setString(5, usuario.getEndereco());
            statement.setString(6, usuario.getDataCadastro().toString());
            statement.setString(7, usuario.getNivelAcesso());
            statement.setString(8, usuario.getCpf());

    
            statement.execute();
            
         
            
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir usuário: " + e.getMessage(), e);
        }
	
      
    }


    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String sql = "CALL listar_usuarios();";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNomeCompleto(rs.getString("nomeCompleto"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setEndereco(rs.getString("endereco"));
                usuario.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());
                
                usuario.setNivelAcesso(rs.getString("nivelAcesso"));
                usuarios.add(usuario);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários: " + e.getMessage(), e);
        }
        return usuarios;
    }

  
    public Usuario consultar(int idUsuario) {
        Usuario usuario = null;
        try {
            String sql = "CALL consultar_usuario(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, idUsuario);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNomeCompleto(rs.getString("nomeCompleto"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setEndereco(rs.getString("endereco"));
                usuario.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());
                
                usuario.setNivelAcesso(rs.getString("nivelAcesso"));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar usuário: " + e.getMessage(), e);
        }
        return usuario;
    }

    // Função para atualizar um usuário
    public void atualizar(Usuario usuario) {
        try {
            String sql = "CALL atualizar_usuario(?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setString(1, usuario.getNomeCompleto());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, usuario.getTelefone());
            statement.setString(5, usuario.getEndereco());
            statement.setString(6, usuario.getDataCadastro().toString());
           
            statement.setString(8, usuario.getNivelAcesso());
            statement.setInt(9, usuario.getIdUsuario());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage(), e);
        }
    }

   
    public void deletar(int idUsuario) {
        try {
            String sql = "CALL deletar_usuario(?);";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1, idUsuario);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar usuário: " + e.getMessage(), e);
        }
    }
}
