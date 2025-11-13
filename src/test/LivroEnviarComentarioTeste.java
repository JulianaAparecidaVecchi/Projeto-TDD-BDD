package test;

import controller.LivroController;
import controller.UsuarioController;
import model.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class LivroEnviarComentarioTeste {
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
    public void deveEnviarComentarioDoLivro(){
        Assert.assertNotNull("O login deve ter sido realizado", usuarioLogado);
        String mensagem = "Livro muito bom.";
        String livro = "1984";
        boolean resultado = livroController.enviarComentario(mensagem, livro, usuarioLogado.getId());
        Assert.assertTrue("Mensagem foi enviada com sucesso", resultado);
    }
}
