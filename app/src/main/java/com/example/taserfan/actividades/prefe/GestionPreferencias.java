package com.example.taserfan.actividades.prefe;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class GestionPreferencias {
    private static SharedPreferences preferences;

    private GestionPreferencias(){}

    public static String getTema(Context context){
        inicializa(context);
        return preferences.getString("temas","DEFAULT");
    }

    private static void inicializa(Context context) {
        if (preferences == null)
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
}
