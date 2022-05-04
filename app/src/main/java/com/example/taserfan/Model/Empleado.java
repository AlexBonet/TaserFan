package com.example.taserfan.Model;

import java.util.Date;

public class Empleado {
    private int IDEMPLEADO;
    private String DNI;
    private String nombre;
    private String APELLIDOS;
    private String EMAIL;
    private String DOMICILIO;
    private String CP;
    private Date FECHANAC;
    private String CARGO;
    private String PASSWORD;

    public Empleado(String nombre) {
        this.nombre = nombre;
    }

    public Empleado(){}

    public Empleado(String DNI, String nombre, String APELLIDOS, String email) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.APELLIDOS = APELLIDOS;
        this.EMAIL = email;
    }

    public Empleado(int IDEMPLEADO, String DNI, String NOMBRE, String APELLIDOS, String DOMICILIO,
                    String CP, String EMAIL, Date FECHANAC, String CARGO, String PASSWORD) {
        this.IDEMPLEADO = IDEMPLEADO;
        this.DNI = DNI;
        this.nombre = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.DOMICILIO = DOMICILIO;
        this.CP = CP;
        this.EMAIL = EMAIL;
        this.FECHANAC = FECHANAC;
        this.CARGO = CARGO;
        this.PASSWORD=PASSWORD;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "IDEMPLEADO=" + IDEMPLEADO +
                ", DNI='" + DNI + '\'' +
                ", nombre='" + nombre + '\'' +
                ", APELLIDOS='" + APELLIDOS + '\'' +
                ", DOMICILIO='" + DOMICILIO + '\'' +
                ", CP='" + CP + '\'' +
                ", EMAIL='" + EMAIL + '\'' +
                ", FECHANAC=" + FECHANAC +
                ", CARGO='" + CARGO + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                '}';
    }

    public int getIDEMPLEADO() {
        return IDEMPLEADO;
    }

    public void setIDEMPLEADO(int IDEMPLEADO) {
        this.IDEMPLEADO = IDEMPLEADO;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public void setAPELLIDOS(String APELLIDOS) {
        this.APELLIDOS = APELLIDOS;
    }

    public String getDOMICILIO() {
        return DOMICILIO;
    }

    public void setDOMICILIO(String DOMICILIO) {
        this.DOMICILIO = DOMICILIO;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public Date getFECHANAC() {
        return FECHANAC;
    }

    public void setFECHANAC(Date FECHANAC) {
        this.FECHANAC = FECHANAC;
    }

    public String getCARGO() {
        return CARGO;
    }

    public void setCARGO(String CARGO) {
        this.CARGO = CARGO;
    }
}
