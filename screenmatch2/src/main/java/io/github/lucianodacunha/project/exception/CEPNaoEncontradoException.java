package io.github.lucianodacunha.project.exception;

public class CEPNaoEncontradoException extends Exception {
    private final String mensagem;

    public CEPNaoEncontradoException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}