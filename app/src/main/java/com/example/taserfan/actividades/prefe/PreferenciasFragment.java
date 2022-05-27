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
import com.example.taserfan.ThemeSetup;

import java.util.Arrays;
import java.util.List;

public class PreferenciasFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.prefes,rootKey);

        //Temas
        ListPreference temas = findPreference("temas");
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

        // Theme preferences with ListPreference
        ListPreference themePreference = getPreferenceManager().findPreference("temas");
        if (themePreference.getValue() == null) {
            themePreference.setValue(ThemeSetup.Mode.DEFAULT.name());
        }
        themePreference.setOnPreferenceChangeListener((preference, newValue) -> {
            ThemeSetup.applyTheme(ThemeSetup.Mode.valueOf((String) newValue));
            return true;
        });


        //IP
        final EditTextPreference ip = findPreference("ip");
        ip.setSummary("IP seleccionada: " + API.Routes.IP);
        ip.setOnPreferenceChangeListener((preference, newValue) -> {
            ip.setSummary("IP seleccionada: " + API.Routes.IP);
            return true;
        });

        //Puerto
        final EditTextPreference puerto = findPreference("puerto");
        puerto.setSummary("PUERTO seleccionado: " + API.Routes.PUERTO);
        puerto.setOnPreferenceChangeListener((preference, newValue) -> {
            puerto.setSummary("PUERTO seleccionado: " + API.Routes.PUERTO);
            return true;
        });
    }
}
