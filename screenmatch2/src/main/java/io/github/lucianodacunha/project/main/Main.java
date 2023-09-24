package io.github.lucianodacunha.project.main;

import io.github.lucianodacunha.project.exception.CEPNaoEncontradoException;
import io.github.lucianodacunha.project.model.ConsultaCEP;
import io.github.lucianodacunha.project.model.Endereco;
import io.github.lucianodacunha.project.util.GravaJson;

import java.io.IOException;

import java.util.Locale;
import java.util.Scanner;

/**
 * O que vamos fazer?
 *
 * - Criar uma aplicação para consultar a API ViaCEP
 * - Menu para o usuário informar o CEP para busca
 * - Geração de um arquivo .JSON com os dados do endereço
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);

        // 13657004, 13657026, 13657108, 13653020
        System.out.println("*********************************");
        System.out.println("*** Bem vindo ao CONSULTA CEP ***");
        System.out.println("*********************************");
        System.out.print("Entre com o CEP desejado: ");
        String cep = input.nextLine();

        Endereco endereco = null;

        try {
            endereco = ConsultaCEP.consultar(cep);
            System.out.print("CEP informado encontrado. Deseja gravar para um arquivo [s/N]: ");
            String gravar = input.nextLine();

            if (gravar.equalsIgnoreCase("s")) {
                GravaJson.gravar(endereco);
                System.out.println("Arquivo gravado com sucesso.");
            }
        } catch (CEPNaoEncontradoException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Programa finalizado.");
        }
    }
}