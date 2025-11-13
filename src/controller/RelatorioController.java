package controller;

import dao.AvaliacaoDAO;
import model.Livro;
import model.Usuario;
import model.RelatorioLeitura;
import model.Avaliacao;
import java.util.List;

public class RelatorioController {
    private AvaliacaoDAO avaliacaoDAO;

    public RelatorioController() {
        this.avaliacaoDAO = new AvaliacaoDAO();
    }

    public RelatorioController(AvaliacaoDAO avaliacaoDAO) {
        this.avaliacaoDAO = avaliacaoDAO;
    }

    public RelatorioLeitura gerarRelatorio(Usuario usuario, List<Livro> livrosCadastrados) {
        int totalLivros = livrosCadastrados.size();
        int livrosLidos = 0;
        int livrosEmLeitura = 0;
        int livrosNaoLidos = 0;
        double somaNotas = 0;
        int contadorNotas = 0;

        // Percorrer cada livro e verificar seu statusLeitura
        for (Livro livro : livrosCadastrados) {
            String statusLeitura = livro.getStatusLeitura();

            if ("lido".equals(statusLeitura)) {
                livrosLidos++;
                // Buscar avaliação do livro
                Avaliacao avaliacao = avaliacaoDAO.buscar(livro, usuario);
                if (avaliacao != null) {
                    somaNotas += avaliacao.getNota();
                    contadorNotas++;
                }
            } else if ("lendo".equals(statusLeitura)) {
                livrosEmLeitura++;
            } else if ("não lido".equals(statusLeitura)) {
                livrosNaoLidos++;
            }
        }

        // Calcular percentuais
        double percentualLidos = totalLivros > 0 ? (livrosLidos * 100.0) / totalLivros : 0;
        double percentualEmLeitura = totalLivros > 0 ? (livrosEmLeitura * 100.0) / totalLivros : 0;
        double percentualNaoLidos = totalLivros > 0 ? (livrosNaoLidos * 100.0) / totalLivros : 0;

        // Calcular média de notas
        double mediaNotas = contadorNotas > 0 ? somaNotas / contadorNotas : 0;

        return new RelatorioLeitura(
                totalLivros,
                livrosLidos,
                livrosEmLeitura,
                livrosNaoLidos,
                percentualLidos,
                percentualEmLeitura,
                percentualNaoLidos,
                mediaNotas
        );
    }
}