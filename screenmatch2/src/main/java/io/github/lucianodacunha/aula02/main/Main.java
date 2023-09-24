package io.github.lucianodacunha.aula02.main;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.lucianodacunha.aula02.model.Titulo;
import io.github.lucianodacunha.aula02.model.TituloOMDb;

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
 * O que são bibliotecas Java;
 * Como instalar a biblioteca Gson no projeto, baixando e configurando o arquivo
 * jar dela;
 * A utilizar a biblioteca Gson para converter um json em um objeto Java;
 * Como criar um Record no Java, que é uma estrutura similar à uma classe, mas
 * utilizado apenas para representar dados;
 * A flexibilizar a conversão de um json em um objeto Java, seguindo a
 * documentação da biblioteca Gson.
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

        String json = response.body();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        TituloOMDb tituloOMDb = gson.fromJson(json, TituloOMDb.class);
        System.out.println("Titulo do tipo TituloOMDb");
        System.out.println(tituloOMDb);
        System.out.println("Titulo do tipo Titulo");
        var titulo = new Titulo(tituloOMDb);
        System.out.println(titulo);
        input.close();
    }
}
