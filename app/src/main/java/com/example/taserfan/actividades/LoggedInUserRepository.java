package com.example.taserfan.actividades;

import com.example.taserfan.Model.Empleado;

public class LoggedInUserRepository {
    private static   LoggedInUserRepository loggedInUserRepository;
    private static Empleado empleado;

    public static LoggedInUserRepository getInstance(){
        if(loggedInUserRepository == null){
            loggedInUserRepository = new LoggedInUserRepository();
        }
        return loggedInUserRepository;
    }

    public void login (Empleado e){
        LoggedInUserRepository.empleado = empleado;
    }

    public static Empleado getLoggedUser(){
        return empleado;
    }

    public void logout() {
        empleado = null;
    }

}