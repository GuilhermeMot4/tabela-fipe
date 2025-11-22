package com.mota.guilherme.tabelafipe.Main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mota.guilherme.tabelafipe.model.*;
import com.mota.guilherme.tabelafipe.service.ConsultaApi;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    private Scanner teclado = new Scanner(System.in);

    ConsultaApi consultaApi = new ConsultaApi();

    public void main(){

        //obtem tipo de veiculo
        System.out.println("Digite o veículo: (carros, motos, caminhoes)");
        String veiculo = teclado.nextLine().toLowerCase();

        while(!veiculo.equals("carros") &&
                !veiculo.equals("motos") &&
                !veiculo.equals("caminhoes")){

            System.out.println("Digite o veículo: (carros, motos, caminhoes)");
            veiculo = teclado.nextLine().toLowerCase();
        }

        //obtem as marcas de tipo veiculo
        try{
            List<DadosMarcas> dadosMarcas = consultaApi.obterDados(URL_BASE + veiculo + "/marcas",  new TypeReference<List<DadosMarcas>>() {});
            dadosMarcas.stream()
                    .sorted(Comparator.comparing(m -> Integer.parseInt(m.codigo())))
                    .forEach(d -> System.out.println(new Marca(d)));
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
            return;
        }

        //obtem os modelos de uma marca
        String marca;
        try{
            System.out.println("Digite uma marca:");
            marca = teclado.nextLine();
            DadosModelos dadosModelos = consultaApi.obterDados(URL_BASE + veiculo + "/marcas/" + marca + "/modelos", new TypeReference<DadosModelos>() {});
            dadosModelos
                    .modelos()
                    .stream()
                    .sorted(Comparator.comparing(m -> Integer.parseInt(m.codigo())))
                    .forEach(d -> System.out.println(new Modelo(d)));
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
            return ;
        }

        //obtem os anos de uma modelo
        try{
            System.out.println("Digite um modelo:");
            var modelo = teclado.nextLine();
            List<DadosAnos> dadosAnos = consultaApi.obterDados(URL_BASE + veiculo + "/marcas/" + marca + "/modelos/" + modelo + "/anos", new TypeReference<List<DadosAnos>>() {});

            //para cada ano, obtem os valores
            System.out.println("ANOS:");
            final String vFinal = veiculo;
            dadosAnos.forEach( d -> {

                DadosVeiculo dadosVeiculo = consultaApi.obterDados(URL_BASE + vFinal + "/marcas/" + marca + "/modelos/" + modelo + "/anos/" + d.codigo(), new TypeReference<DadosVeiculo>() {});
                VeiculoValor veiculoValor = new VeiculoValor(dadosVeiculo);
                System.out.println("Ano " + d.codigo() + ": " + veiculoValor);

            });
        }catch(RuntimeException e) {
            System.out.println(e.getMessage());
        }


    }
}
