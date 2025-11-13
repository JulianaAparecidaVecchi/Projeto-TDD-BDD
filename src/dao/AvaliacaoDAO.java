package dao;

import model.Avaliacao;
import model.Livro;
import model.Usuario;
import java.util.HashMap;
import java.util.Map;

public class AvaliacaoDAO {
    private Map<String, Avaliacao> avaliacoes = new HashMap<>();

    public void salvar(Avaliacao avaliacao) {
        String chave = avaliacao.getLivro().getId() + "-" + avaliacao.getUsuario().getId();
        avaliacoes.put(chave, avaliacao);
    }

    public Avaliacao buscar(Livro livro, Usuario usuario) {
        String chave = livro.getId() + "-" + usuario.getId();
        return avaliacoes.get(chave);
    }
}