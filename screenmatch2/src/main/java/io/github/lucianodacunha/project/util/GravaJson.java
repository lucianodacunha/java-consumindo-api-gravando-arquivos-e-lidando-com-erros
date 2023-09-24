package io.github.lucianodacunha.project.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.lucianodacunha.project.model.Endereco;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GravaJson {
    public static boolean gravar(Endereco endereco){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File("ceps.txt");
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(endereco));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}