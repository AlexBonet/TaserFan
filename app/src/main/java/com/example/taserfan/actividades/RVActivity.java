package com.example.taserfan.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taserfan.API.Connector;
import com.example.taserfan.API.Result;
import com.example.taserfan.R;
import com.example.taserfan.actividades.prefe.PreferenciasActivity;
import com.example.taserfan.actividades.vehiculos.Vehiculo;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;
import com.google.android.material.snackbar.Snackbar;

import java.util.LinkedList;
import java.util.List;


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
        executeCall(this);
    }

    @Override
    public void doInBackground() {
        vehiculoList = new LinkedList<>(Connector.getConector().getAsList(Vehiculo.class, "/vehiculos"));
    }

    @Override
    public void doInUI() {
        RVAdapter myRVAdapter = new RVAdapter(getApplicationContext(), vehiculoList);
        myRVAdapter.setOnClickListener(this);
        recyclerView.setAdapter(myRVAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        /*DESLIZAR PARA ELIMINAR*/
        ItemTouchHelper mIth = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        // move item in `fromPos` to `toPos` in adapter.
                        recyclerView.getAdapter().notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                        return true;// true if moved, false otherwise
                    }
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        Vehiculo vehiculo = vehiculoList.get(viewHolder.getAdapterPosition());
                        int position = viewHolder.getAdapterPosition();
                        vehiculoList.remove(position);
                        myRVAdapter.notifyItemRemoved(position);

                        executeCall(new CallInterface() {
                            @Override
                            public void doInBackground() {
                                Connector.getConector().delete(Vehiculo.class, "/coche?matricula="+vehiculo.getMatricula());
                            }

                            @Override
                            public void doInUI() {

                            }
                        });

                        Snackbar.make(recyclerView, "Deleted " + vehiculo.getMatricula(), Snackbar.LENGTH_LONG)
                                .setAction("Undo", v -> {
                                    vehiculoList.add(position, vehiculo);
                                    myRVAdapter.notifyItemInserted(position);

                                    executeCall(new CallInterface() {
                                        @Override
                                        public void doInBackground() {

                                        }

                                        @Override
                                        public void doInUI() {

                                        }
                                    });

                                }).show();
                    }
                });
        mIth.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onClick(View view) {
        int posi = recyclerView.getChildAdapterPosition(view);
        Intent intent = new Intent(getApplicationContext(),VistaExtendiad.class);
        intent.putExtra("matricula",vehiculoList.get(posi).getMatricula());
        intent.putExtra("tipo",vehiculoList.get(posi).getTipoVehiculo());
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
        Intent intentPreferenciasActivity;
        switch (item.getItemId()) {
            case (R.id.confi):
                intentPreferenciasActivity = new Intent(this, PreferenciasActivity.class);
                startActivity(intentPreferenciasActivity);
                return true;
            case (R.id.exit):
                intentPreferenciasActivity = new Intent(this, LoginActivity.class);
                startActivity(intentPreferenciasActivity);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}