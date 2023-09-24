package io.github.lucianodacunha.aula01.main;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

/**
 * O que é uma API e seu funcionamento básico;
 * Como funciona a API do OMDb, para a busca de filmes;
 * A realizar uma consulta na API do OMDb utilizando o Postman;
 * Como se integrar à API do OMDb em Java, utilizando as classes HttpClient,
 * HttpRequest e HttpResponse.
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);
        InputStream config = Main.class.getClassLoader().getResourceAsStream(
                "config.properties");
        Properties prop = new Properties();
        prop.load(config);

        System.out.println("Digite o nome do filme para busca: matrix");
//        var busca = input.nextLine();
        var busca = "matrix";

        String apikey = prop.getProperty("apikey");
        String uri = String.format("https://www.omdbapi.com/?t=%s&apikey=%s",
                busca, apikey);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        input.close();
    }
}
