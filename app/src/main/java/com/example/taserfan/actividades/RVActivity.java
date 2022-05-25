package com.example.taserfan.actividades;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.taserfan.API.Connector;
import com.example.taserfan.R;
import com.example.taserfan.actividades.prefe.PreferenciasActivity;
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
        todos = findViewById(R.id.rbtodos);
        coche = findViewById(R.id.rbcoche);
        patin = findViewById(R.id.rbpatin);
        bicis = findViewById(R.id.rbbicis);
        motos = findViewById(R.id.rbmotos);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddVehiculo.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void doInBackground() {
        vehiculoList = new LinkedList<>(Connector.getConector().getAsList(Vehiculo.class, "/vehicles"));

    }

    @Override
    public void doInUI() {
        hideProgress();

        RVAdapter myRVAdapter = new RVAdapter(getApplicationContext(), vehiculoList);
        myRVAdapter.setOnClickListener(this);
        recyclerView.setAdapter(myRVAdapter);

        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLinearLayoutManager);
    }

    @Override
    public void onClick(View view) {
        int posi = recyclerView.getChildAdapterPosition(view);
        Intent intent = new Intent(getApplicationContext(),VistaExtendiad.class);
        intent.putExtra("matricula",vehiculoList.get(posi).getMatricula());
        startActivity(intent);
    }

    /*MENU*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.confi):
                Intent intentPreferenciasActivity = new Intent(this, PreferenciasActivity.class);
                startActivity(intentPreferenciasActivity);
                return true;
            case (R.id.exit):
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}