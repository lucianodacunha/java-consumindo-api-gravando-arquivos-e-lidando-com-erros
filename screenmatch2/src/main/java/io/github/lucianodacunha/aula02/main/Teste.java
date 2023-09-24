package io.github.lucianodacunha.aula02.main;


import com.github.javafaker.Faker;

public class Teste {
    public static void main(String[] args) {
        Faker faker = new Faker();

        /**
         * Instanciação e uso semelhante às classes.
         */
        var address = new Address(
                faker.address().streetName(),
                Integer.valueOf(faker.address().streetAddressNumber()),
                faker.address().cityName()
        );

        /**
         * Aqui estamos imprimindo um record.
         * Já implementa por padrão alguns métodos entre eles toString, equals e
         * hashCode.
         */
        System.out.println(address);

    }
}

/**
 * Definição de um Address.
 * Perceba que não possui quaisquer metódos e declaração de propriedades.
 * Possuem algumas restrições em sua personalização.
 * Também não suportam herança.
 */
record Address(String street, Integer number, String city){}
