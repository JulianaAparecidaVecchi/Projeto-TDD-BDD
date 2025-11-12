package model;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String categoria;
    private String statusLeitura; // "não lido", "lendo", "lido"
    private int idUsuario;

    public Livro() {}

    public Livro(String titulo, String autor, String categoria, String statusLeitura, int idUsuario) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.statusLeitura = statusLeitura;
        this.idUsuario = idUsuario;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getStatusLeitura() {
        return statusLeitura;
    }

    public void setStatusLeitura(String statusLeitura) {
        this.statusLeitura = statusLeitura;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Título: %s | Autor: %s | Categoria: %s | Status: %s",
                id, titulo, autor, categoria, statusLeitura);
    }
}