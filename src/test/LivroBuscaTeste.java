package test;

import controller.LivroController;
import controller.UsuarioController;
import model.Livro;
import model.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LivroBuscaTeste {
    private LivroController livroController;
    private UsuarioController usuarioController;
    private Usuario usuarioLogado;

    @Before
    public void setUp(){
        livroController = new LivroController();
        usuarioController = new UsuarioController();
        usuarioLogado = usuarioController.login("lucasboschiroli@gmail.com", "666Striker");
    }

    @Test
    public void deveBuscarTodososLivrosPeloAutor(){
        assertNotNull("Usuário deve estar logado", usuarioLogado);
        String autor = "George Orwell";
        List<Livro> livros =  livroController.buscarLivroPorAutor(autor, usuarioLogado.getId());
        assertNotNull("A lista foi retornada",livros);
        assertFalse("O usuario possuia um livro com o autor escolhido e ele foi exibido", livros.isEmpty());
    }



}
