package com.example.restclientservweb;

public class FAQs {

    String pregunta;
    String respuesta;

    public FAQs(String pregunta, String respuesta) {

        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public FAQs() {
    }

    public void setpregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    public String getpregunta() {
        return pregunta;
    }

    public void setrespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    public String getrespuesta() {
        return respuesta;
    }
}
