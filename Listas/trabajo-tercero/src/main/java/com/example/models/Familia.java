package com.example.models;

import com.example.controls.tda.stack.Stack;
import com.example.models.enumerador.TipoIdentificacion;

public class Familia {
    private Integer id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private TipoIdentificacion tipo;
    private String identificacion;
    private Stack<Generador> generadores;
    private boolean tieneGenerador;

    public Familia() {
        this.tieneGenerador = false;
    }

    public Familia(Integer id, String nombre, String apellido, String direccion, String telefono,
                   TipoIdentificacion tipo, String identificacion, Stack<Generador> generadores) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo = tipo;
        this.identificacion = identificacion;
        this.generadores = generadores;
        this.tieneGenerador = (generadores != null && generadores.getSize() > 0); // Inicializar basado en generadores
    }

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoIdentificacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoIdentificacion tipo) {
        this.tipo = tipo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Stack<Generador> getGeneradores() {
        return generadores;
    }

    public void setGeneradores(Stack<Generador> generadores) {
        this.generadores = generadores;
        // Actualizar el estado de tieneGenerador
        this.tieneGenerador = (generadores != null && generadores.getSize() > 0);
    }

    public boolean isTieneGenerador() { // Getter para el nuevo atributo
        return tieneGenerador;
    }

    @Override
    public String toString() {
        return " " + apellido + " " + nombre ;
    }
}
