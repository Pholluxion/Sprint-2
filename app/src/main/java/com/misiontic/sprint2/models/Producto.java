package com.misiontic.sprint2.models;

import com.google.android.gms.maps.model.LatLng;

public class Producto {

    private int image;
    private String titulo;
    private String desc;
    private LatLng latLng;

    public Producto() {
    }

    public Producto(int image, String titulo, String desc, LatLng latLng) {
        this.image = image;
        this.titulo = titulo;
        this.desc = desc;
        this.latLng = latLng;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "image=" + image +
                ", titulo='" + titulo + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
