package test;

import controller.LivroController;
import controller.UsuarioController;
import model.Livro;
import model.Usuario;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class LivroAtualizacaoTeste {

    private LivroController livroController;
    private UsuarioController usuarioController;
    private Usuario usuarioLogado;

    @Before
    public void setUp() {
        livroController = new LivroController();
        usuarioController = new UsuarioController();

        // Simula um usuário logado (ajuste o e-mail e senha para um usuário válido do seu banco)
        usuarioLogado = usuarioController.login("lucasboschiroli@gmail.com", "666Striker");

        assertNotNull("Usuário deve estar logado para os testes", usuarioLogado);
    }

    @Test
    public void deveAtualizarUmLivroComSucesso() {
        // === GIVEN ===
        // Cadastrar um novo livro para garantir que exista algo a atualizar
        boolean cadastroSucesso = livroController.cadastrarLivro(
                "Livro de Teste",
                "Autor Original",
                "Ficção",
                "não lido",
                usuarioLogado.getId()
        );

        assertTrue("O livro deve ser cadastrado com sucesso", cadastroSucesso);

        // Recuperar a lista de livros do usuário para pegar o livro recém-criado
        List<Livro> livros = livroController.listarLivros(usuarioLogado.getId());
        assertNotNull("A lista de livros não deve ser nula", livros);
        assertFalse("Deve haver pelo menos um livro cadastrado", livros.isEmpty());

        Livro livroParaAtualizar = livros.get(livros.size() - 1);

        // === WHEN ===
        boolean atualizado = livroController.atualizarLivro(
                livroParaAtualizar.getId(),
                "Livro de Teste Atualizado",
                "Autor Atualizado",
                "Romance",
                "lido",
                usuarioLogado.getId()
        );

        // === THEN ===
        assertTrue("O livro deve ser atualizado com sucesso", atualizado);

        // Buscar novamente os livros para confirmar a atualização
        List<Livro> livrosAtualizados = livroController.listarLivros(usuarioLogado.getId());
        Livro livroAtualizado = livrosAtualizados.stream()
                .filter(l -> l.getId() == livroParaAtualizar.getId())
                .findFirst()
                .orElse(null);

        assertNotNull("O livro atualizado deve existir", livroAtualizado);
        assertEquals("Livro de Teste Atualizado", livroAtualizado.getTitulo());
        assertEquals("Autor Atualizado", livroAtualizado.getAutor());
        assertEquals("Romance", livroAtualizado.getCategoria());
        assertEquals("lido", livroAtualizado.getStatusLeitura());
    }

    @Test
    public void naoDeveAtualizarLivroComTituloVazio() {
        // === GIVEN ===
        List<Livro> livros = livroController.listarLivros(usuarioLogado.getId());
        if (livros.isEmpty()) {
            livroController.cadastrarLivro("Livro Temp", "Autor Temp", "Drama", "não lido", usuarioLogado.getId());
            livros = livroController.listarLivros(usuarioLogado.getId());
        }

        Livro livroParaAtualizar = livros.get(0);

        // === WHEN ===
        boolean atualizado = livroController.atualizarLivro(
                livroParaAtualizar.getId(),
                " ", // título inválido
                "Autor Teste",
                "Terror",
                "lendo",
                usuarioLogado.getId()
        );

        // === THEN ===
        assertFalse("Não deve permitir atualização com título vazio", atualizado);
    }

    @Test
    public void naoDeveAtualizarLivroComAutorVazio() {
        // === GIVEN ===
        List<Livro> livros = livroController.listarLivros(usuarioLogado.getId());
        if (livros.isEmpty()) {
            livroController.cadastrarLivro("Livro Temporário", "Autor X", "Ação", "não lido", usuarioLogado.getId());
            livros = livroController.listarLivros(usuarioLogado.getId());
        }

        Livro livroParaAtualizar = livros.get(0);

        // === WHEN ===
        boolean atualizado = livroController.atualizarLivro(
                livroParaAtualizar.getId(),
                "Novo Título",
                "", // autor inválido
                "Aventura",
                "lendo",
                usuarioLogado.getId()
        );

        // === THEN ===
        assertFalse("Não deve permitir atualização com autor vazio", atualizado);
    }
}
