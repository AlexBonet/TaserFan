package com.example.taserfan.actividades.vehiculos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;

public class Bicicleta extends Vehiculo implements Serializable {
    private String tipo;

    public Bicicleta(String matricula, float precioHora, String marca, String descripcion, String color, int bateria, Date fechaAdq, String estado, String idCarnet, Timestamp changeDts, String changeBy, String tipo) {
        super(matricula, precioHora, marca, descripcion, color, bateria, fechaAdq, estado, idCarnet, changeDts, changeBy);
        this.tipo = tipo;
    }

    public Bicicleta(String matricula, float precioHora, String marca, String descripcion, String color, int bateria, Date fechaAdq, String estado, String idCarnet, String tipo) {
        super(matricula, precioHora, marca, descripcion, color, bateria, fechaAdq, estado, idCarnet);
        this.tipo = tipo;
    }

    public Bicicleta(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Bicicleta{" +
                "tipo='" + tipo + '\'' +
                '}';
    }
}
