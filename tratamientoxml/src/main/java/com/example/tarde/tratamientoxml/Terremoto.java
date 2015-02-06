package com.example.tarde.tratamientoxml;

import java.util.Date;

/**
 * Created by tarde on 06/02/2015.
 */
public class Terremoto {
    private String id;
    private String title;
    private Date fecha;
    private String link;
    private float latitud;
    private float longitud;
    private float magnitud;

    public Terremoto() {
    }

    public Terremoto(String id, String title, Date fecha, String link, float latitud, float longitud, float magnitud) {
        this.id = id;
        this.title = title;
        this.fecha = fecha;
        this.link = link;
        this.latitud = latitud;
        this.longitud = longitud;
        this.magnitud = magnitud;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public float getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(float magnitud) {
        this.magnitud = magnitud;
    }
}
