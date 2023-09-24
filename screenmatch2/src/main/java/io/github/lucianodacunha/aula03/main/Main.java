package io.github.lucianodacunha.aula03.main;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.lucianodacunha.aula03.exception.ErroDeParserDeDuracaDoFilmeException;
import io.github.lucianodacunha.aula03.model.Titulo;
import io.github.lucianodacunha.aula03.model.TituloOMDb;

import java.io.FileWriter;
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
 * O que são exceptions no Java e como tratá-las com o bloco try/catch;
 * Como capturar diferentes tipos de exceptions que podem acontecer no código;
 * Como criar uma classe exception personalizada;
 * A realizar uma validação e lançar uma exception em caso de erro.
 */
public class Main {

    public static void main(String[] args) throws IOException,
            InterruptedException {
        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);
        InputStream config = Main.class.getClassLoader().getResourceAsStream(
                "config.properties");
        Properties prop = new Properties();
        prop.load(config);

        var busca = "wall+e";
        System.out.printf("Digite o nome do filme para busca: %s\n", busca);
//        var busca = input.nextLine();

        String apikey = prop.getProperty("apikey");
        String uri = String.format("https://www.omdbapi.com/?t=%s&apikey=%s",
                busca, apikey);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(
                    URI.create(uri)).build();
            HttpResponse<String> response = client.send(
                    request, HttpResponse.BodyHandlers.ofString());
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
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        } catch (ErroDeParserDeDuracaDoFilmeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Finalizando o sistema.");
        input.close();
    }
}
