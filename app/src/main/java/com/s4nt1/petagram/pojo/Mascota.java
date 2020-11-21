package com.s4nt1.petagram.pojo;

public class Mascota {
    private String nombre;
    private int cantidad_likes;
    private int imagen;
    private int id;


    public Mascota(int id, String nombre, int imagen){
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;

    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCantidad_likes() {
        return cantidad_likes;
    }

    public void setCantidad_likes(int cantidad_likes) {
        this.cantidad_likes = cantidad_likes;
    }
    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }


    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }


}
