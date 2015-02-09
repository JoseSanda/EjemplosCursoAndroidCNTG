package com.example.tarde.basededatos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by tarde on 09/02/2015.
 */
public class Noticia implements Serializable{


    public static final String TABLE = "Noticia";
    public static final String TITULO_COLUMN = "TITULO";
    public static final String LINK_COLUMN = "LINK";
    public static final String DESC_COLUMN = "DESCRIPCION";
    public static final String FECHA_COLUMN = "FECHA";
    public static final String ID_COLUMN = "ID";
    public static final String CREADOR_COLUMN = "CREADER";

    private String id;
    private String titulo;
    private String link;
    private String creador;
    private String descripcion;
    private Date fechaPublicacion;

    public Noticia() {
    }

    public Noticia(String id, String titulo, String link, String creador, String descripcion, Date fechaPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.link = link;
        this.creador = creador;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
