package com.example.models.enumerador;

public enum TipoIdentificacion {
    CEDULA("CEDULA"), PASAPORTE("PASAPORTE");
    private String name;

    private TipoIdentificacion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
