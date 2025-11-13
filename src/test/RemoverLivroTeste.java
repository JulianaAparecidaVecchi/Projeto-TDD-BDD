package test;

import controller.LivroController;
import controller.UsuarioController;
import model.Livro;
import model.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RemoverLivroTeste {

    private LivroController livroController;
    private UsuarioController usuarioController;
    private Usuario usuarioLogado;

    @Before
    public void setUp() {
        livroController = new LivroController();
        usuarioController = new UsuarioController();

        // Simula login de um usuário existente (ajuste conforme seu banco)
        usuarioLogado = usuarioController.login("lucasboschiroli@gmail.com", "666Striker");

        assertNotNull("Usuário deve estar logado para os testes", usuarioLogado);
    }

    @Test
    public void deveRemoverLivroComSucesso() {
        boolean cadastrado = livroController.cadastrarLivro(
                "Livro para Remover",
                "Autor Teste",
                "Suspense",
                "não lido",
                usuarioLogado.getId()
        );
        assertTrue("O livro deve ser cadastrado com sucesso", cadastrado);

        List<Livro> livrosAntes = livroController.listarLivros(usuarioLogado.getId());
        assertNotNull("A lista de livros não deve ser nula", livrosAntes);
        assertFalse("Deve haver ao menos um livro cadastrado", livrosAntes.isEmpty());

        Livro livroParaRemover = livrosAntes.get(livrosAntes.size() - 1);

        // === WHEN ===
        boolean removido = livroController.removerLivro(livroParaRemover.getId(), usuarioLogado.getId());

        // === THEN ===
        assertTrue("O livro deve ser removido com sucesso", removido);

        // Verifica se o livro não aparece mais na listagem
        List<Livro> livrosDepois = livroController.listarLivros(usuarioLogado.getId());
        boolean aindaExiste = livrosDepois.stream()
                .anyMatch(l -> l.getId() == livroParaRemover.getId());

        assertFalse("O livro removido não deve mais aparecer na listagem", aindaExiste);
    }

    @Test
    public void naoDeveRemoverLivroInexistente() {
        // === GIVEN ===
        int idLivroInexistente = 999999; // ID que não existe no banco

        // === WHEN ===
        boolean resultado = livroController.removerLivro(idLivroInexistente, usuarioLogado.getId());

        // === THEN ===
        assertFalse("Não deve remover livro inexistente", resultado);
    }

    @Test
    public void deveManterOutrosLivrosAposRemocao() {
        // === GIVEN ===
        // Cadastra dois livros
        livroController.cadastrarLivro("Livro 1", "Autor A", "Ação", "lido", usuarioLogado.getId());
        livroController.cadastrarLivro("Livro 2", "Autor B", "Drama", "lendo", usuarioLogado.getId());

        List<Livro> livrosAntes = livroController.listarLivros(usuarioLogado.getId());
        assertTrue("Deve haver ao menos dois livros", livrosAntes.size() >= 2);

        Livro livroParaRemover = livrosAntes.get(livrosAntes.size() - 1);

        // === WHEN ===
        boolean removido = livroController.removerLivro(livroParaRemover.getId(), usuarioLogado.getId());

        // === THEN ===
        assertTrue("O livro deve ser removido com sucesso", removido);

        // Verifica se os outros livros permanecem
        List<Livro> livrosDepois = livroController.listarLivros(usuarioLogado.getId());
        assertTrue("Ainda deve haver outros livros cadastrados", livrosDepois.size() >= 1);
        assertFalse("O livro removido não deve mais existir",
                livrosDepois.stream().anyMatch(l -> l.getId() == livroParaRemover.getId()));
    }
}
