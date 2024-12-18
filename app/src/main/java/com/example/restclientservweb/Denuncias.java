package com.example.restclientservweb;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.google.gson.annotations.SerializedName;

public class Denuncias {


    String fecha;
    String nombre;
    String comentario;

    public Denuncias(String nombre, String comentario, String fecha)
    {
        this.nombre = nombre;
        this.comentario= comentario;
        this.fecha = fecha;
    }

    public void setfecha(String fecha) {
        this.fecha = fecha;
    }
    public String getfecha() {
        return fecha;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }
    public String getnombre() {
        return nombre;
    }

    public void setcomentario(String comentario) {
        this.comentario = comentario;
    }
    public String getComentario() {
        return comentario;
    }
}
