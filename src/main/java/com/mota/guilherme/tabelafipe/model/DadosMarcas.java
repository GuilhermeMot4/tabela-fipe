package com.mota.guilherme.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosMarcas(@JsonAlias("codigo") String codigo,
                          @JsonAlias("nome") String nome) {
}
