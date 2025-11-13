package model;

public class Avaliacao {
    private Livro livro;
    private Usuario usuario;
    private int nota;

    public Avaliacao(Livro livro, Usuario usuario, int nota) {
        this.livro = livro;
        this.usuario = usuario;
        this.nota = nota;
    }

    public Livro getLivro() { return livro; }
    public Usuario getUsuario() { return usuario; }
    public int getNota() { return nota; }
}