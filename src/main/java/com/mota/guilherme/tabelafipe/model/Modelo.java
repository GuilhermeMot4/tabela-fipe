package com.mota.guilherme.tabelafipe.model;

public class Modelo {

    private String codigo;
    private String nome;

    public Modelo(DadoModelo d) {
        this.codigo = d.codigo();
        this.nome = d.nome();
    }

    @Override
    public String toString() {
        return "Modelo{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
