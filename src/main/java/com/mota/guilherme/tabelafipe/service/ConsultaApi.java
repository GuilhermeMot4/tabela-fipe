package com.mota.guilherme.tabelafipe.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mota.guilherme.tabelafipe.model.DadosMarcas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ConsultaApi {


    public <T> T obterDados(String endereco, TypeReference<T> tipoRetorno){

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        try {

            ObjectMapper mapper = new ObjectMapper();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Erro na API: HTTP " + response.statusCode());
            }

            return mapper.readValue(response.body(), tipoRetorno);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao consultar API FIPE", e);
        }

    }

}
