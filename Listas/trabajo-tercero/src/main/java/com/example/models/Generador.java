package com.example.models;

public class Generador {
    private Integer id;
    private Float coste;
    private Float consumo;
    private Float potencia;
    private String uso;

    public Generador() {
    }

    public Generador(Integer id, Float coste, Float consumo, Float potencia, String uso) {
        this.id = id;
        this.coste = coste;
        this.consumo = consumo;
        this.potencia = potencia;
        this.uso = uso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getCoste() {
        return coste;
    }

    public void setCoste(Float coste) {
        this.coste = coste;
    }

    public Float getConsumo() {
        return consumo;
    }

    public void setConsumo(Float consumo) {
        this.consumo = consumo;
    }

    public Float getPotencia() {
        return potencia;
    }

    public void setPotencia(Float potencia) {
        this.potencia = potencia;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

}
