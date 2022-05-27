package com.example.taserfan.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.taserfan.R;
import com.example.taserfan.actividades.prefe.PreferenciasActivity;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;

public class UpdVehiculo extends BaseActivity implements CallInterface {
    private LinearLayout lineaBici, lineaMoto1, lineaMoto2, lineaPatin1, lineaPatin2, lineaCoche1, lineaCoche2;
    private EditText matricula, marca, fecha, estado, precio, descripcion, color, carnet, bateria,
            puertasCoche, plazasCoche, velocidadMoto, cilindradaMoto, tipoBici, ruedasPatin, tamanoPatin;
    private Button btn_upd_veh, btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upd_vehiculo);

        lineaBici = findViewById(R.id.lineaTipoBici);
        lineaCoche1 = findViewById(R.id.lineapuertasC);
        lineaCoche2 = findViewById(R.id.lineaplazasC);
        lineaMoto1 = findViewById(R.id.lineaVelocidadM);
        lineaMoto2 = findViewById(R.id.lineacilindrada);
        lineaPatin1 = findViewById(R.id.lineatamano);
        lineaPatin2 = findViewById(R.id.lineaRuedaP);

        lineaBici.setVisibility(View.GONE);
        lineaCoche1.setVisibility(View.GONE);
        lineaCoche2.setVisibility(View.GONE);
        lineaMoto1.setVisibility(View.GONE);
        lineaMoto2.setVisibility(View.GONE);
        lineaPatin1.setVisibility(View.GONE);
        lineaPatin2.setVisibility(View.GONE);

        matricula = findViewById(R.id.updMatriucla);
        marca = findViewById(R.id.updMarca);
        fecha = findViewById(R.id.updFecha);
        estado = findViewById(R.id.updEstado);
        precio = findViewById(R.id.updPrecio);
        descripcion = findViewById(R.id.updDescip);
        color = findViewById(R.id.updColor);
        carnet = findViewById(R.id.updCarnet);
        bateria = findViewById(R.id.updBateria);
        puertasCoche = findViewById(R.id.updPuertas);
        plazasCoche = findViewById(R.id.updPlazas);
        velocidadMoto = findViewById(R.id.updVel);
        cilindradaMoto = findViewById(R.id.updCilind);
        tipoBici = findViewById(R.id.updTBici);
        ruedasPatin = findViewById(R.id.updRuedaP);
        tamanoPatin = findViewById(R.id.updTamano);

        btn_cancel = findViewById(R.id.btn_cancel_add);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RVActivity.class);
                startActivity(intent);
            }
        });

        btn_upd_veh = findViewById(R.id.btn_upd_veh);
        btn_upd_veh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RVActivity.class);
                //todo que añada

                startActivity(intent);

            }
        });
    }

    //CONEXION DB
    @Override
    public void doInBackground() {

    }

    @Override
    public void doInUI() {

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