package com.example.mp0489_nf02_estrada_edgar;

public class Audio {

    //Creación de variables
    private int id;
    private String title;
    private String url;

    //Creación del constructor
    public Audio(int id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }
    //Constructores extra
    public Audio(String title, String url) {
        this.title = title;
        this.url = url;
    }

    //Creación de los getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toString(){
        return this.id + ". " + this.title;
    }

}
