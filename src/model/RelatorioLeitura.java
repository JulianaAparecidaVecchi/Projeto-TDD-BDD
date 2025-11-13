package model;

public class RelatorioLeitura {
    private int totalLivros;
    private int livrosLidos;
    private int livrosEmLeitura;
    private int livrosNaoLidos;
    private double percentualLidos;
    private double percentualEmLeitura;
    private double percentualNaoLidos;
    private double mediaNotas;

    public RelatorioLeitura(int totalLivros, int livrosLidos, int livrosEmLeitura,
                            int livrosNaoLidos, double percentualLidos,
                            double percentualEmLeitura, double percentualNaoLidos,
                            double mediaNotas) {
        this.totalLivros = totalLivros;
        this.livrosLidos = livrosLidos;
        this.livrosEmLeitura = livrosEmLeitura;
        this.livrosNaoLidos = livrosNaoLidos;
        this.percentualLidos = percentualLidos;
        this.percentualEmLeitura = percentualEmLeitura;
        this.percentualNaoLidos = percentualNaoLidos;
        this.mediaNotas = mediaNotas;
    }

    public int getTotalLivros() { return totalLivros; }
    public int getLivrosLidos() { return livrosLidos; }
    public int getLivrosEmLeitura() { return livrosEmLeitura; }
    public int getLivrosNaoLidos() { return livrosNaoLidos; }
    public double getPercentualLidos() { return percentualLidos; }
    public double getPercentualEmLeitura() { return percentualEmLeitura; }
    public double getPercentualNaoLidos() { return percentualNaoLidos; }
    public double getMediaNotas() { return mediaNotas; }
}
