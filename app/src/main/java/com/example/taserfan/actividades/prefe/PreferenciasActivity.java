package com.example.taserfan.actividades.prefe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.taserfan.R;

public class PreferenciasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PreferenciasFragment())
                .commit();
    }
}