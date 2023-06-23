package com.example.practicajava;

import java.io.Serializable;

public class Persona implements Serializable {

    private String cedula;
    private String nombres;
    private String fecha;
    private String ciudad;
    private String genero;
    private String correo;
    private String telefono;


    public Persona(String cedula, String nombres, String fecha, String ciudad, String genero, String correo, String telefono) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.fecha = fecha;
        this.ciudad = ciudad;
        this.genero = genero;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Persona(){}

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
