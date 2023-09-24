package io.github.lucianodacunha.project.model;

public record Endereco(String cep, String logradouro, String complemento,
                String bairro, String localidade, String uf, String ibge,
                String gia, String ddd, String siafi){}