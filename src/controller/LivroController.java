package controller;

import dao.LivroDAO;
import model.Livro;
import java.util.List;

public class LivroController {
    private LivroDAO livroDAO = new LivroDAO();

    // RF03 - Cadastrar livro
    public boolean cadastrarLivro(String titulo, String autor, String categoria, String statusLeitura, int idUsuario) {

        // Validações básicas
        if (titulo == null || titulo.trim().isEmpty()) {
            System.out.println("O título não pode estar vazio!");
            return false;
        }

        if (autor == null || autor.trim().isEmpty()) {
            System.out.println("O autor não pode estar vazio!");
            return false;
        }

        Livro novoLivro = new Livro(titulo, autor, categoria, statusLeitura, idUsuario);
        return livroDAO.salvarLivro(novoLivro);
    }

    // RF04 - Listar livros do usuário
    public List<Livro> listarLivros(int idUsuario) {
        return livroDAO.listarLivrosPorUsuario(idUsuario);
    }

     //Método auxiliar para buscar livros específicos (Rafael se quiser usar)
    public List<Livro> buscarLivroPorAutor(String autor, int idUsuario) {
        return livroDAO.buscarPorAutor(autor, idUsuario);
    }

    // Método para atualizar livros (Arthur se quiser usar)
    public boolean atualizarLivro(int id, String titulo, String autor, String categoria, String statusLeitura, int idUsuario) {
        if (titulo == null || titulo.trim().isEmpty()) {
            System.out.println("O título não pode estar vazio!");
            return false;
        }

        if (autor == null || autor.trim().isEmpty()) {
            System.out.println("O autor não pode estar vazio!");
            return false;
        }

        Livro livro = new Livro(titulo, autor, categoria, statusLeitura, idUsuario);
        livro.setId(id);
        return livroDAO.atualizarLivro(livro);
    }

    // Método para remover livros (Arthur se quiser usar)
    public boolean removerLivro(int idLivro, int idUsuario) {
        return livroDAO.removerLivro(idLivro, idUsuario);
    }
}