package dao;

import model.Livro;
import util.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    // RF03 - Adicionar livro ao acervo pessoal
    public boolean salvarLivro(Livro livro) {
        String sql = "INSERT INTO livro (titulo, autor, categoria, status_leitura, id_usuario, comentario) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getCategoria());
            stmt.setString(4, livro.getStatusLeitura());
            stmt.setInt(5, livro.getIdUsuario());
            stmt.setString(6, livro.getComentario());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
            return false;
        }
    }

    // RF04 - Listar todos os livros do usuário
    public List<Livro> listarLivrosPorUsuario(int idUsuario) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE id_usuario = ? ORDER BY titulo";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setCategoria(rs.getString("categoria"));
                livro.setStatusLeitura(rs.getString("status_leitura"));
                livro.setIdUsuario(rs.getInt("id_usuario"));
                livro.setComentario(rs.getString("comentario"));
                livros.add(livro);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }

        return livros;
    }

    // Método auxiliar para buscar livro por Autor(útil para outras funcionalidades)
    public List<Livro> buscarPorAutor(String nomeAutor, int id_usuario) {
        List<Livro> livros= new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE autor = ? AND id_usuario = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeAutor);
            stmt.setInt(2, id_usuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setCategoria(rs.getString("categoria"));
                livro.setStatusLeitura(rs.getString("status_leitura"));
                livro.setIdUsuario(rs.getInt("id_usuario"));
                livro.setComentario(rs.getString("comentario"));
                livros.add(livro);

            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar livros: " + e.getMessage());
        }
        return livros;

    }

    // Método para atualizar livro (para futuras funcionalidades)
    public boolean atualizarLivro(Livro livro) {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, categoria = ?, status_leitura = ? WHERE id = ? AND id_usuario = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getCategoria());
            stmt.setString(4, livro.getStatusLeitura());
            stmt.setInt(5, livro.getId());
            stmt.setInt(6, livro.getIdUsuario());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar livro: " + e.getMessage());
            return false;
        }
    }

    // Método para remover livro (para futuras funcionalidades)
    public boolean removerLivro(int id, int idUsuario) {
        String sql = "DELETE FROM livro WHERE id = ? AND id_usuario = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setInt(2, idUsuario);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao remover livro: " + e.getMessage());
            return false;
        }
    }

    public boolean enviarComentario(String comentario, String nomeLivro, int idUsuario){
        String sql = "UPDATE livro SET comentario = ? WHERE titulo = ? AND id_usuario = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, comentario);
            stmt.setString(2, nomeLivro);
            stmt.setInt(3, idUsuario);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas != 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar livro: " + e.getMessage());
            return false;
        }
    }
}
