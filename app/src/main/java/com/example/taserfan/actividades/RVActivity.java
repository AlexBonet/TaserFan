package com.example.taserfan.actividades;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.taserfan.API.Connector;
import com.example.taserfan.R;
import com.example.taserfan.actividades.vehiculos.Vehiculo;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;

import java.util.LinkedList;
import java.util.List;
import java.util.zip.Inflater;


public class RVActivity extends BaseActivity implements CallInterface, View.OnClickListener {

    private RecyclerView recyclerView;
    private RadioGroup rd;
    private RadioButton todos,coche,patin,bicis,motos;
    private EditText editText;
    private ImageButton btnAdd;
    private List<Vehiculo> vehiculoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_activity);

        recyclerView = findViewById(R.id.recyclerV);
        rd = findViewById(R.id.radioGroup);
        editText = findViewById(R.id.filtro);
        btnAdd = findViewById(R.id.btnAdd);
    }

    @Override
    public void doInBackground() {
        vehiculoList = new LinkedList<>(Connector.getConector().getAsList(Vehiculo.class, "/vehiculos"));

    }

    @Override
    public void doInUI() {
        RVAdapter myRecyclerViewAdapter = new RVAdapter(getApplicationContext(), vehiculoList);
        myRecyclerViewAdapter.setOnClickListener(this);
        recyclerView.setAdapter(myRecyclerViewAdapter);

        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLinearLayoutManager);
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        if (view == btnAdd){
            intent = new Intent(getApplicationContext(),AddVehiculo.class);
            startActivity(intent);
        }
    }
}