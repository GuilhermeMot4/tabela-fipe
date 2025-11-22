package com.mota.guilherme.tabelafipe.model;

public class VeiculoValor {

    private int tipoVeiculo;
    private double valor;
    private String marca;
    private String modelo;
    private int ano;
    private String combustivel;
    private String codigoFipe;
    private String mesReferencia;
    private char siglaCombustivel;

    public VeiculoValor(DadosVeiculo dadosVeiculo) {

        this.marca = dadosVeiculo.marca();
        this.modelo = dadosVeiculo.modelo();
        this.ano = dadosVeiculo.ano();

        this.valor = Double.parseDouble(
                    dadosVeiculo.valor().replace("R$", "")
                    .replace(".", "")
                    .replace(",", ".")
                    .trim()
                );
    }

    public String getModelo() {
        return modelo;
    }

    public int getTipoVeiculo() {
        return tipoVeiculo;
    }

    public double getValor() {
        return valor;
    }

    public String getMarca() {
        return marca;
    }

    public int getAno() {
        return ano;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public String getCodigoFipe() {
        return codigoFipe;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public char getSiglaCombustivel() {
        return siglaCombustivel;
    }

    @Override
    public String toString() {
        return "VeiculoValor{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", valor=" + valor +
                '}';
    }
}
