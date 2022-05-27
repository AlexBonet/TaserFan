package com.example.taserfan.actividades.vehiculos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;

public class Moto extends Vehiculo implements Serializable {
    private int velocidadMax;
    private int cilindrada;

    public Moto(String matricula, float precioHora, String marca, String descripcion, String color, int bateria,
                Date fechaAdq, String estado, String idCarnet, Timestamp changeDts, String changeBy, TipoVehiculos tipoVehiculo, int velocidadMax, int cilindrada) {
        super(matricula, precioHora, marca, descripcion, color, bateria, fechaAdq, estado, idCarnet, changeDts, changeBy, tipoVehiculo);
        this.velocidadMax = velocidadMax;
        this.cilindrada = cilindrada;
    }

    public Moto(int velocidadMax, int cilindrada) {
        this.velocidadMax = velocidadMax;
        this.cilindrada = cilindrada;
    }

    public Moto(String matricula, float precioHora, String marca, String descripcion, String color, int bateria,
                Date fechaAdq, String estado, String idCarnet, TipoVehiculos tipoVehiculo, int velocidadMax, int cilindrada) {
        super(matricula, precioHora, marca, descripcion, color, bateria, fechaAdq, estado, idCarnet, tipoVehiculo);
        this.velocidadMax = velocidadMax;
        this.cilindrada = cilindrada;
    }

    public int getVelocidadMax() {
        return velocidadMax;
    }

    public void setVelocidadMax(int velocidadMax) {
        this.velocidadMax = velocidadMax;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public String toString() {
        return "Moto{" +
                "velocidadMax=" + velocidadMax +
                ", cilindrada=" + cilindrada +
                '}';
    }
}
