package io.github.lucianodacunha.aula03.exception;

public class ErroDeParserDeDuracaDoFilmeException extends RuntimeException{

    private final String message;

    public ErroDeParserDeDuracaDoFilmeException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
