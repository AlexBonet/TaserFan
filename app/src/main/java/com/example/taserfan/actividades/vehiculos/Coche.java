package com.example.taserfan.actividades.vehiculos;

import java.sql.Timestamp;
import java.sql.Date;

public class Coche extends Vehiculo {
    private int numPlazas;
    private int numPuertas;

    public Coche(String matricula, float precioHora, String marca, String descripcion, String color, int bateria, Date fechaAdq, String estado, String idCarnet, Timestamp changeDts, String changeBy, int numPlazas, int numPuertas) {
        super(matricula, precioHora, marca, descripcion, color, bateria, fechaAdq, estado, idCarnet, changeDts, changeBy);
        this.numPlazas = numPlazas;
        this.numPuertas = numPuertas;
    }

    public Coche(String matricula, float precioHora, String marca, String descripcion, String color, int bateria, Date fechaAdq, String estado, String idCarnet, int numPlazas, int numPuertas) {
        super(matricula, precioHora, marca, descripcion, color, bateria, fechaAdq, estado, idCarnet);
        this.numPlazas = numPlazas;
        this.numPuertas = numPuertas;
    }

    public Coche(int numPlazas, int numPuertas) {
        this.numPlazas = numPlazas;
        this.numPuertas = numPuertas;
    }

    public int getNumPlazas() {
        return numPlazas;
    }

    public void setNumPlazas(int numPlazas) {
        this.numPlazas = numPlazas;
    }

    public int getNumPuertas() {
        return numPuertas;
    }

    public void setNumPuertas(int numPuertas) {
        this.numPuertas = numPuertas;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "numPlazas=" + numPlazas +
                ", numPuertas=" + numPuertas +
                '}';
    }
}
