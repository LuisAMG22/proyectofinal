package com.example.pruebasproyecto;

public class Ejercicio {
    private int numero;
    private int imagen;
    private String kilos;
    private String repeticiones;

    public Ejercicio(int numero, int imagen, String kilos, String repeticiones) {
        this.numero = numero;
        this.imagen = imagen;
        this.kilos = kilos;
        this.repeticiones = repeticiones;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getKilos() {
        return kilos;
    }

    public void setKilos(String kilos) {
        this.kilos = kilos;
    }

    public String getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(String repeticiones) {
        this.repeticiones = repeticiones;
    }
}
