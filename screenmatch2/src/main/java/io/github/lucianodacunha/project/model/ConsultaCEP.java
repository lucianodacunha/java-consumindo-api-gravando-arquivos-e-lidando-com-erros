package io.github.lucianodacunha.project.model;

import com.google.gson.Gson;
import io.github.lucianodacunha.project.exception.CEPNaoEncontradoException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaCEP {
    public static Endereco consultar(String cep) throws CEPNaoEncontradoException {
        String url = String.format("https://viacep.com.br/ws/%s/json/", cep);

        HttpClient client = HttpClient.newHttpClient();
        URI endpoint = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(endpoint)
                .build();

        HttpResponse<String> response = null;

        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();

        if (json.contains("erro")){
            throw new CEPNaoEncontradoException("O CEP informado não foi encontrado.");
        }

        return new Gson().fromJson(json, Endereco.class);
    }
}
