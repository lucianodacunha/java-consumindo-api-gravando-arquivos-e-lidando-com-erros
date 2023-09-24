package io.github.lucianodacunha.aula02.main;

import com.github.javafaker.Faker;
import com.google.gson.Gson;

public class TestandoGson {
    public static void main(String[] args) {
        Faker faker = new Faker();

        var address = new Address2(
                faker.address().streetName(),
                Integer.valueOf(faker.address().streetAddressNumber()),
                faker.address().cityName()
        );

        // instanciando um objetos Gson
        var gson = new Gson();

        // Criando um json de um objeto.
        var json = gson.toJson(address, Address2.class);
        System.out.println(json);

        // Criando um objeto de um json
        var objeto = gson.fromJson(json, Address2.class);
        System.out.println(objeto);

    }
}

record Address2(String street, Integer number, String city){}
