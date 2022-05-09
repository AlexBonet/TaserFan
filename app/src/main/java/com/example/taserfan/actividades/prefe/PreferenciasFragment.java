package com.example.taserfan.actividades.prefe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.taserfan.API.API;
import com.example.taserfan.R;

import java.util.Arrays;
import java.util.List;

public class PreferenciasFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.prefes,rootKey);

        ListPreference temas = findPreference("temas");
        EditTextPreference ip = findPreference("ip");
        EditTextPreference puerto = findPreference("puerto");

        //Temas
        List<String> entriesTemas = Arrays.asList(getResources().getStringArray(R.array.settings_theme_entries));
        List<String> valuesTemas = Arrays.asList(getResources().getStringArray(R.array.settings_theme_values));
        String tema = entriesTemas.get(valuesTemas.indexOf(GestionPreferencias.getTema(getContext())));
        temas.setSummary("Tema seleccionada: " + tema);
        temas.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                String tem = entriesTemas.get(valuesTemas.indexOf(newValue));
                temas.setSummary("Tema seleccionada: " + tem);
                return true;
            }
        });

        //IP
        ip.setSummary("IP seleccionada: " + API.Routes.IP);
        ip.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                ip.setSummary("IP seleccionada: " + API.Routes.IP);
                return true;
            }
        });

        //Puerto
        puerto.setSummary("PUERTO seleccionado: " + API.Routes.PUERTO);
        puerto.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                puerto.setSummary("PUERTO seleccionado: " + API.Routes.PUERTO);
                return true;
            }
        });
    }
}
