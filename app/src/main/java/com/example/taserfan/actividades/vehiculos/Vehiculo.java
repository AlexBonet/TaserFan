package com.example.taserfan.actividades.vehiculos;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Vehiculo implements Serializable {
    private String matricula;
    private float precioHora;
    private String marca;
    private String descripcion;
    private String color;
    private int bateria;
    private Date fechaAdq;
    private String estado;
    private String idCarnet;
    private Timestamp changeDts;
    private String changeBy;
    private TipoVehiculos tipoVehiculo;

    public Vehiculo(String matricula, float precioHora, String marca, String descripcion, String color, int bateria,
                    Date fechaAdq, String estado, String idCarnet, Timestamp changeDts, String changeBy, TipoVehiculos tipoVehiculo) {
        this.matricula = matricula;
        this.precioHora = precioHora;
        this.marca = marca;
        this.descripcion = descripcion;
        this.color = color;
        this.bateria = bateria;
        this.fechaAdq = fechaAdq;
        this.estado = estado;
        this.idCarnet = idCarnet;
        this.changeDts = changeDts;
        this.changeBy = changeBy;
        this.tipoVehiculo = tipoVehiculo;
    }

    public Vehiculo(String matricula, float precioHora, String marca, String descripcion, String color, int bateria,
                    Date fechaAdq, String estado, String idCarnet, TipoVehiculos tipoVehiculo) {
        this.matricula = matricula;
        this.precioHora = precioHora;
        this.marca = marca;
        this.descripcion = descripcion;
        this.color = color;
        this.bateria = bateria;
        this.fechaAdq = fechaAdq;
        this.estado = estado;
        this.idCarnet = idCarnet;
        this.tipoVehiculo = tipoVehiculo;
    }

    public Vehiculo() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public float getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(float precioHora) {
        this.precioHora = precioHora;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public Date getFechaAdq() {
        return fechaAdq;
    }

    public void setFechaAdq(Date fechaAdq) {
        this.fechaAdq = fechaAdq;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdCarnet() {
        return idCarnet;
    }

    public void setIdCarnet(String idCarnet) {
        this.idCarnet = idCarnet;
    }

    public Timestamp getChangeDts() {
        return changeDts;
    }

    public void setChangeDts(Timestamp changeDts) {
        this.changeDts = changeDts;
    }

    public String getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(String changeBy) {
        this.changeBy = changeBy;
    }

    public com.example.taserfan.actividades.vehiculos.TipoVehiculos getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(com.example.taserfan.actividades.vehiculos.TipoVehiculos tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
}
