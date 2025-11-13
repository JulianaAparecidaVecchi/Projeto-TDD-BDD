package test;

import controller.AvaliacaoController;
import dao.AvaliacaoDAO;
import model.Avaliacao;
import model.Livro;
import model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrarAvaliacaoTest {

    private AvaliacaoController controller;
    private AvaliacaoDAO dao;

    @BeforeEach
    void setUp() {
        dao = new AvaliacaoDAO();
        controller = new AvaliacaoController(dao);
    }

    @Test
    @DisplayName("Registrar Avaliação de Leitura - TDD")
    void registrarAvaliacaoLeitura() {
        Livro livro = new Livro("Clean Code", "Robert Martin", "Programação", "Lido", 11);
        Usuario usuario = new Usuario("Lucas", "joao@email.com", "senha123");

        int nota = 4;
        String mensagem = controller.registrarAvaliacao(livro, usuario, nota);

        Avaliacao avaliacaoSalva = controller.buscarAvaliacao(livro, usuario);
        assertNotNull(avaliacaoSalva, "A avaliação deve ter sido salva");
        assertEquals(livro, avaliacaoSalva.getLivro());
        assertEquals(usuario, avaliacaoSalva.getUsuario());
        assertEquals(nota, avaliacaoSalva.getNota());
        assertEquals("Avaliação realizada com sucesso", mensagem);
    }
}