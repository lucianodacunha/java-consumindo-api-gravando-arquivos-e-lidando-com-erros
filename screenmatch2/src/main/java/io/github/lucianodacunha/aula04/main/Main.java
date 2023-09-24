package io.github.lucianodacunha.aula04.main;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.lucianodacunha.aula04.exception.ErroDeParserDeDuracaDoFilmeException;
import io.github.lucianodacunha.aula04.model.Titulo;
import io.github.lucianodacunha.aula04.model.TituloOMDb;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

/**
 * Escrever dados em um arquivo, utilizando classes do pacote java.io;
 * Serializar um objeto Titulo para um formato JSON, utilizando a biblioteca
 * vista anteriormente Gson;
 * Gerar o arquivo no formato JSON, com uma formatação mais elegante, usando o
 * método setPrettyPrinting.
 */
public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);
        List<Titulo> filmes = new ArrayList<>();

        do {
            System.out.print("Entre com o nome do filme ou q para sair: ");
            var busca = input.nextLine();

            if (busca.equalsIgnoreCase("q"))
                break;

            String uri = String.format("https://www.omdbapi.com/?t=%s&apikey=%s",
                    busca, getAPIKey());

            try {
                var tituloOMDb = getMovieData(uri);
                filmes.add(new Titulo(tituloOMDb));

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (ErroDeParserDeDuracaDoFilmeException e) {
                System.out.println(e.getMessage());
            }
        } while(true);

        writeToFile(filmes);
        System.out.println("Finalizando o sistema.");
        input.close();
    }

    static String getAPIKey(){
        InputStream config = Main.class.getClassLoader().getResourceAsStream(
                "config.properties");
        Properties prop = new Properties();

        try {
            prop.load(config);
        }  catch (IOException e){
            System.out.println("Erro ao carregar o arquivo de properties");
        }

        String apikey = prop.getProperty("apikey");
        return apikey;
    }

    static TituloOMDb getMovieData(String uri){
        TituloOMDb tituloOMDb = null;
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
            tituloOMDb = gson.fromJson(json, TituloOMDb.class);
            return tituloOMDb;
        } catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }

        return tituloOMDb;
    }

    static void writeToFile(List<Titulo> titulos){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        FileWriter fw = null;
        try {
            fw = new FileWriter("arquivo.txt");
            fw.write(gson.toJson(titulos));
        } catch (IOException e){
            System.out.println(e.getMessage());
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
