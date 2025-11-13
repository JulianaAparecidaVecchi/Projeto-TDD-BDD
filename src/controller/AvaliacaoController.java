package controller;

import dao.AvaliacaoDAO;
import model.Avaliacao;
import model.Livro;
import model.Usuario;

public class AvaliacaoController {
    private AvaliacaoDAO dao;

    public AvaliacaoController() {
        this.dao = new AvaliacaoDAO();
    }

    public AvaliacaoController(AvaliacaoDAO dao) {
        this.dao = dao;
    }

    public String registrarAvaliacao(Livro livro, Usuario usuario, int nota) {
        // Validar nota entre 0 e 5
        if (nota < 0 || nota > 5) {
            return "Erro: A nota deve estar entre 0 e 5";
        }

        // Salvar avaliação
        Avaliacao avaliacao = new Avaliacao(livro, usuario, nota);
        dao.salvar(avaliacao);

        // Retornar mensagem de sucesso
        return "Avaliação realizada com sucesso";
    }

    public Avaliacao buscarAvaliacao(Livro livro, Usuario usuario) {
        return dao.buscar(livro, usuario);
    }
}