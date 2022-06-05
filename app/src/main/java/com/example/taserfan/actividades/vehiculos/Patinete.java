package com.example.taserfan.actividades.vehiculos;

import java.io.Serializable;
import java.sql.Timestamp;


public class Patinete extends Vehiculo implements Serializable {
    private int numRuedas;
    private int tamanyo;

    public Patinete(String matricula, float precioHora, String marca, String descripcion, String color, int bateria, String fechaAdq, String estado, String idCarnet, Timestamp changeDts, String changeBy, TipoVehiculos tipoVehiculo, int numRuedas, int tamanyo) {
        super(matricula, precioHora, marca, descripcion, color, bateria, fechaAdq, estado, idCarnet, changeDts, changeBy, tipoVehiculo);
        this.numRuedas = numRuedas;
        this.tamanyo = tamanyo;
    }

    public Patinete(String matricula, float precioHora, String marca, String descripcion, String color, int bateria, String fechaAdq, String estado, String idCarnet, TipoVehiculos tipoVehiculo, int numRuedas, int tamanyo) {
        super(matricula, precioHora, marca, descripcion, color, bateria, fechaAdq, estado, idCarnet, tipoVehiculo);
        this.numRuedas = numRuedas;
        this.tamanyo = tamanyo;
    }

    public int getNumRuedas() {
        return numRuedas;
    }

    public void setNumRuedas(int numRuedas) {
        this.numRuedas = numRuedas;
    }

    public int getTamanyo() {
        return tamanyo;
    }

    public void setTamanyo(int tamanyo) {
        this.tamanyo = tamanyo;
    }

    @Override
    public String toString() {
        return "Patinete{" +
                "numRuedas=" + numRuedas +
                ", tamanyo=" + tamanyo +
                '}';
    }
}
