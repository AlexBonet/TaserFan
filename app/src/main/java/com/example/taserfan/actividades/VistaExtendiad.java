package com.example.taserfan.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.taserfan.R;
//TODO SABER DE QUIN tipo es cada vehiculo y fer tot
public class VistaExtendiad extends AppCompatActivity {

    private ImageButton edit, back;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_view);

        edit = findViewById(R.id.btnEdit);
        back = findViewById(R.id.btnBack);

        edit.setOnClickListener(view -> {
            intent = new Intent(getApplicationContext(),AddVehiculo.class);
            startActivity(intent);
        });

        back.setOnClickListener(view -> {
            intent = new Intent(getApplicationContext(),RVActivity.class);
            startActivity(intent);
        });
    }
}