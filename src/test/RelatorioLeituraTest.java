package test;

import controller.RelatorioController;
import controller.AvaliacaoController;
import controller.LivroController;
import controller.UsuarioController;
import dao.AvaliacaoDAO;
import model.Livro;
import model.Usuario;
import model.RelatorioLeitura;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RelatorioLeituraTest {

    private RelatorioController relatorioController;
    private AvaliacaoController avaliacaoController;
    private LivroController livroController;
    private UsuarioController usuarioController;
    private AvaliacaoDAO avaliacaoDAO;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        avaliacaoDAO = new AvaliacaoDAO();
        relatorioController = new RelatorioController(avaliacaoDAO);
        avaliacaoController = new AvaliacaoController(avaliacaoDAO);
        livroController = new LivroController();
        usuarioController = new UsuarioController();

        // Buscar usuário existente no banco
        usuario = usuarioController.login("lucasboschiroli@gmail.com", "666Striker");
        assertNotNull(usuario, "Usuário deve estar cadastrado no banco para executar o teste");
    }

    @Test
    @DisplayName("Relatório de Leitura - Exibir estatísticas dos livros já cadastrados no banco")
    void gerarRelatorioLeitura() throws Exception {
        // Buscar os livros que já existem no banco de dados
        List<Livro> livrosCadastrados = livroController.listarLivros(usuario.getId());

        assertNotNull(livrosCadastrados, "Lista de livros não deve ser nula");
        assertFalse(livrosCadastrados.isEmpty(), "O usuário deve ter livros cadastrados no banco para executar este teste");

        RelatorioLeitura relatorio = relatorioController.gerarRelatorio(usuario, livrosCadastrados);

        // Verificações básicas
        assertNotNull(relatorio, "Relatório não deve ser nulo");
        assertEquals(livrosCadastrados.size(), relatorio.getTotalLivros(), "Total de livros deve corresponder aos cadastrados");

        // Verificar que os percentuais somam 100% (ou próximo, considerando arredondamento)
        double somaPercentuais = relatorio.getPercentualLidos() +
                relatorio.getPercentualEmLeitura() +
                relatorio.getPercentualNaoLidos();
        assertEquals(100.0, somaPercentuais, 0.1, "A soma dos percentuais deve ser 100%");

        // Verificar que as quantidades batem com o total
        int somaQuantidades = relatorio.getLivrosLidos() +
                relatorio.getLivrosEmLeitura() +
                relatorio.getLivrosNaoLidos();
        assertEquals(relatorio.getTotalLivros(), somaQuantidades, "A soma das quantidades deve ser igual ao total de livros");

        // Verificar que a média de notas está no intervalo válido (0 a 5)
        assertTrue(relatorio.getMediaNotas() >= 0 && relatorio.getMediaNotas() <= 5,
                "Média de notas deve estar entre 0 e 5");

        // Exibir o relatório para visualização (opcional, apenas para debug)
        System.out.println("\n=== Relatório Gerado ===");
        System.out.println("Total de livros: " + relatorio.getTotalLivros());
        System.out.println("Livros lidos: " + relatorio.getLivrosLidos() + " (" + String.format("%.2f", relatorio.getPercentualLidos()) + "%)");
        System.out.println("Livros em leitura: " + relatorio.getLivrosEmLeitura() + " (" + String.format("%.2f", relatorio.getPercentualEmLeitura()) + "%)");
        System.out.println("Livros não lidos: " + relatorio.getLivrosNaoLidos() + " (" + String.format("%.2f", relatorio.getPercentualNaoLidos()) + "%)");
        System.out.println("Média de notas: " + String.format("%.2f", relatorio.getMediaNotas()));
    }
}