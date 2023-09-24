package io.github.lucianodacunha.aula01.model;

public class Titulo {
    private String titulo;
    private int anoDeLancamento;
    private int duracaoEmMinutos;
    private boolean estaIncluidoNoPlano;
    private int somaDasAvaliacoes;
    private int quantidadeDeAvaliacoes;

    private double mediaDasAvaliacoes;

    public void exibeFichaTecnica(){
        System.out.printf("""
                Título: %s
                Ano de Lançamento: %s
                Duração em Minutos: %d
                Incluído no Plano: %s
                Total de Avaliações: %d
                Média das Avaliações: %.2f\n
                """, getTitulo(), getAnoDeLancamento(), getDuracaoEmMinutos(),
                isEstaIncluidoNoPlano(), getQuantidadeDeAvaliacoes(),
                getMediaDasAvaliacoes());
    }

    public double exibeAMedia(){
        if (somaDasAvaliacoes > 0 && getQuantidadeDeAvaliacoes() > 0)
            return this.somaDasAvaliacoes / getQuantidadeDeAvaliacoes();
        return 0.0;
    }

    public void avalia(int nota){
        this.somaDasAvaliacoes += nota;
        this.quantidadeDeAvaliacoes++;
        this.calcularMediaDasAvaliacoes();
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public boolean isEstaIncluidoNoPlano(){
        return estaIncluidoNoPlano;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getQuantidadeDeAvaliacoes(){
        return quantidadeDeAvaliacoes;
    }

    private void calcularMediaDasAvaliacoes(){
        this.mediaDasAvaliacoes = this.somaDasAvaliacoes / this.quantidadeDeAvaliacoes;
    }

    public double getMediaDasAvaliacoes(){return this.mediaDasAvaliacoes;}

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public void setEstaIncluidoNoPlano(boolean estaIncluidoNoPlano) {
        this.estaIncluidoNoPlano = estaIncluidoNoPlano;
    }
}
