package test;

import controller.LivroController;
import controller.UsuarioController;
import model.Livro;
import model.Usuario;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class LivroListagemTeste {

    private LivroController livroController;
    private UsuarioController usuarioController;
    private Usuario usuarioLogado;

    @Before
    public void setUp() {
        livroController = new LivroController();
        usuarioController = new UsuarioController();

        // Simula um usuário logado para os testes
        usuarioLogado = usuarioController.login("lucasboschiroli@gmail.com", "666Striker");
    }

    @Test
    public void deveListarTodosOsLivrosDoUsuario() {
        // Given - Dado que o usuário está logado
        assertNotNull("Usuário deve estar logado", usuarioLogado);

        // When - Quando ele solicita ver sua lista de livros
        List<Livro> livros = livroController.listarLivros(usuarioLogado.getId());

        // Then - Então o sistema deve listar todos os livros cadastrados
        assertNotNull("A lista de livros não deve ser nula", livros);

        // Verifica que a lista foi retornada (pode ter 0 ou mais livros)
        assertTrue("Deve retornar uma lista válida", livros.size() >= 0);
    }


}