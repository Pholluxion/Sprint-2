package com.misiontic.sprint2.models;

import com.google.android.gms.maps.model.LatLng;

public class Producto {

    private int image;
    private String titulo;
    private String desc;
    private Double lat;
    private Double lng;

    public Producto() {
    }

    public Producto(int image, String titulo, String desc, Double lat, Double lng) {
        this.image = image;
        this.titulo = titulo;
        this.desc = desc;
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
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
