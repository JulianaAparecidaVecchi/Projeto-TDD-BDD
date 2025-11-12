package test;

import controller.LivroController;
import controller.UsuarioController;
import model.Usuario;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LivroCadastroTeste {

    private LivroController livroController;
    private UsuarioController usuarioController;
    private Usuario usuarioLogado;

    @Before
    public void setUp() {
        livroController = new LivroController();
        usuarioController = new UsuarioController();
        
        usuarioLogado = usuarioController.login("lucasboschiroli@gmail.com", "666Striker");

    }

    @Test
    public void deveCadastrarLivroComDadosValidos() {
        // Given - Dado que o usuário está logado no sistema
        assertNotNull("Usuário deve estar logado", usuarioLogado);

        // When - Quando ele informa título, autor, gênero e status de leitura
        String titulo = "1984";
        String autor = "George Orwell";
        String categoria = "Ficção";
        String statusLeitura = "lido";

        boolean resultado = livroController.cadastrarLivro(
                titulo,
                autor,
                categoria,
                statusLeitura,
                usuarioLogado.getId()
        );

        // Then - Então o sistema deve registrar o livro e confirmar sucesso
        assertTrue("O livro deve ser cadastrado com sucesso", resultado);
    }

    @Test
    public void naoDeveCadastrarLivroSemTitulo() {
        assertNotNull("Usuário deve estar logado", usuarioLogado);

        String titulo = ""; // Título vazio
        String autor = "Autor Teste";
        String categoria = "Teste";
        String statusLeitura = "não lido";

        boolean resultado = livroController.cadastrarLivro(
                titulo,
                autor,
                categoria,
                statusLeitura,
                usuarioLogado.getId()
        );

        assertFalse("Não deve cadastrar livro sem título", resultado);
    }

    @Test
    public void naoDeveCadastrarLivroSemAutor() {
        assertNotNull("Usuário deve estar logado", usuarioLogado);

        String titulo = "Livro Teste";
        String autor = ""; // Autor vazio
        String categoria = "Teste";
        String statusLeitura = "não lido";

        boolean resultado = livroController.cadastrarLivro(
                titulo,
                autor,
                categoria,
                statusLeitura,
                usuarioLogado.getId()
        );

        assertFalse("Não deve cadastrar livro sem autor", resultado);
    }
}