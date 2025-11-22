package com.mota.guilherme.tabelafipe.model;

public class Marca {

    private String codigo;
    private String nome;

    public Marca(DadosMarcas d) {
        this.codigo = d.codigo();
        this.nome = d.nome();
    }

    @Override
    public String toString() {
        return "Marca{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
