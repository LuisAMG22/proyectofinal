package com.example.proyectopruebas40;
public class Ejercicio {
    private int numero;
    private int imagen;
    private int kilos;
    private int repeticiones;

    private int valorkg;
    private int valorreps;
    private long tiempocrono;

    public Ejercicio(int numero, int imagen, int kilos, int repeticiones) {
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

    public int getKilos() {
        return kilos;
    }

    public void setKilos(int kilos) {
        this.kilos = kilos;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getValorkg() {
        return valorkg;
    }

    public void setValorkg(int valorkg) {
        this.valorkg = valorkg;
    }

    public int getValorreps() {
        return valorreps;
    }

    public void setValorreps(int valorreps) {
        this.valorreps = valorreps;
    }

    public long getTiempocrono() {
        return tiempocrono;
    }

    public void setTiempocrono(long tiempocrono) {
        this.tiempocrono = tiempocrono;
    }
}
