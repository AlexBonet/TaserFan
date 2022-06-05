package com.example.taserfan.actividades;

import android.app.AlertDialog;

import com.example.taserfan.API.Result;

public class Tools {
    /**
     * Metodo para genenrar Dialog cuando hay algun campo vacio
     * @param alert Dialog de la actividad
     * @param msg el mensaje que mostrar
     */
    public static void alertaVacio(AlertDialog.Builder alert, String msg){
        alert.setMessage(msg)
                .setTitle("CAMPO VACIO")
                .setPositiveButton("Acpetar",null);
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    /**
     * Metodo para genenrar Dialog de mensajes de error
     * @param alert Dialog de la actividad
     * @param error Result error que recibimos del connector
     */
    public static void alertError(AlertDialog.Builder alert, Result.Error error, String msg){
        alert.setMessage("Error: " + error.getCode() + "\n" + " : - " + error.getMessage())
                .setTitle("ERROR " + msg)
                .setPositiveButton("Aceptar", null);
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }
}
