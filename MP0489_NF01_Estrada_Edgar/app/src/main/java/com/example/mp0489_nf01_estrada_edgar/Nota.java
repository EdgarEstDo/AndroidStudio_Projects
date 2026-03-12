package com.example.mp0489_nf01_estrada_edgar;

public class Nota {

    private int id;
    private String titulo;
    private String contenido;

    //Constructor de la clase
    public Nota(int id, String titulo, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
    }
    //Otro constructor para evitar tener que introducir el id
    public Nota (String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
    }
    //Métodos getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return this.titulo;
    }
}


