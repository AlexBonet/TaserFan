package com.example.taserfan.actividades.prefe;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.example.taserfan.API.API;
import com.example.taserfan.R;

import com.example.taserfan.ThemeSetup;

public class GestionPreferencias {
    private static SharedPreferences pref;
    private static GestionPreferencias gestionPreferencias;

    private GestionPreferencias(){}

    public static String getTema(Context context){
        inicializa(context);
        return pref.getString("temas","DEFAULT");
    }

    private static void inicializa(Context context) {
        if (pref == null)
            pref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static GestionPreferencias getInstance(){
        if(gestionPreferencias==null)
            gestionPreferencias = new GestionPreferencias();
        return gestionPreferencias;
    }
//TODO preguntar a joaquin com fer pa set la ip y el puerto
    public String getIp(Context context){
        inicializa(context);
        return pref.getString("ip", API.Routes.IP);
    }

    public String getPuerto(Context context){
        inicializa(context);
        return pref.getString("puerto", API.Routes.PUERTO);
    }

    public String getTheme(Context context){
        inicializa(context);
        return pref.getString(context.getString(R.string.settings_theme_key), ThemeSetup.Mode.DEFAULT.name());
    }
}
