package io.github.lucianodacunha.aula04.model;

public class Filme extends Titulo implements Favoritavel {
    private String diretor;
    private boolean favorito;

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    @Override
    public void favoritar() {
        this.favorito = true;
    }

    @Override
    public boolean isFavorito() {
        return this.favorito;
    }
}
