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
import android.widget.Toast;

import com.example.taserfan.API.Connector;
import com.example.taserfan.API.Result;
import com.example.taserfan.R;
import com.example.taserfan.actividades.prefe.PreferenciasActivity;
import com.example.taserfan.actividades.vehiculos.Bicicleta;
import com.example.taserfan.actividades.vehiculos.Coche;
import com.example.taserfan.actividades.vehiculos.Moto;
import com.example.taserfan.actividades.vehiculos.Patinete;
import com.example.taserfan.actividades.vehiculos.TipoVehiculos;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;

public class UpdVehiculo extends BaseActivity implements CallInterface {
    private LinearLayout lineaBici, lineaMoto1, lineaMoto2, lineaPatin1, lineaPatin2, lineaCoche1, lineaCoche2;
    private EditText matricula, marca, fecha, estado, precio, descripcion, color, carnet, bateria,
            puertasCoche, plazasCoche, velocidadMoto, cilindradaMoto, tipoBici, ruedasPatin, tamanoPatin;
    private Button btn_upd_veh, btn_cancel;

    Result<Coche> cResult;
    Result<Moto> mResult;
    Result<Bicicleta> bResult;
    Result<Patinete> pResult;

    private TipoVehiculos tipo;
    private Coche coche;
    private Moto moto;
    private Patinete patinete;
    private Bicicleta bicicleta;

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

        tipo = (TipoVehiculos) getIntent().getExtras().getSerializable("tipo");

        if (tipo == TipoVehiculos.COCHE) {
            coche = (Coche) getIntent().getExtras().getSerializable("co");

            matricula.setHint(coche.getMatricula());
            marca.setHint(coche.getMarca());
            fecha.setHint(coche.getFechaAdq().toString());
            estado.setHint(coche.getEstado());
            precio.setHint((int) coche.getPrecioHora());
            descripcion.setHint(coche.getDescripcion());
            color.setHint(coche.getColor());
            carnet.setHint(coche.getIdCarnet());
            bateria.setHint(coche.getBateria());


        } else if (tipo == TipoVehiculos.MOTOS) {
            moto = (Moto) getIntent().getExtras().getSerializable("mo");

            matricula.setHint(moto.getMatricula());
            marca.setHint(moto.getMarca());
            fecha.setHint(moto.getFechaAdq().toString());
            estado.setHint(moto.getEstado());
            precio.setHint((int) moto.getPrecioHora());
            descripcion.setHint(moto.getDescripcion());
            color.setHint(moto.getColor());
            carnet.setHint(moto.getIdCarnet());
            bateria.setHint(moto.getBateria());

            /*moto = new Moto(matricula.getText().toString(),
                    Float.parseFloat(precio.getText().toString()),
                    marca.getText().toString(),
                    descripcion.getText().toString(),
                    color.getText().toString(),
                    fecha.getText().toString(),
                    estado.getText().toString(),
                    carnet.getText().toString(),
                    TipoVehiculos.MOTOS,
                    velocidadMoto.getText().toString(),
                    cilindradaMoto.getText().toString()
            );*/

        } else if (tipo == TipoVehiculos.PATIN) {
            patinete = (Patinete)getIntent().getExtras().getSerializable("pa");

            matricula.setHint(patinete.getMatricula());
            marca.setHint(patinete.getMarca());
            fecha.setHint(patinete.getFechaAdq().toString());
            estado.setHint(patinete.getEstado());
            precio.setHint((int) patinete.getPrecioHora());
            descripcion.setHint(patinete.getDescripcion());
            color.setHint(patinete.getColor());
            carnet.setHint(patinete.getIdCarnet());
            bateria.setHint(patinete.getBateria());

        } else if (tipo == TipoVehiculos.BICIS) {
            bicicleta = (Bicicleta) getIntent().getExtras().getSerializable("bi");

            matricula.setHint(bicicleta.getMatricula());
            marca.setHint(bicicleta.getMarca());
            fecha.setHint(bicicleta.getFechaAdq().toString());
            estado.setHint(bicicleta.getEstado());
            precio.setHint((int) bicicleta.getPrecioHora());
            descripcion.setHint(bicicleta.getDescripcion());
            color.setHint(bicicleta.getColor());
            carnet.setHint(bicicleta.getIdCarnet());
            bateria.setHint(bicicleta.getBateria());

        }

        btn_cancel = findViewById(R.id.btn_cancel_add);
        btn_cancel.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RVActivity.class);
            startActivity(intent);
        });

        btn_upd_veh = findViewById(R.id.btn_upd_veh);
        btn_upd_veh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RVActivity.class);
                //todo que añada
                executeCall(UpdVehiculo.this);

                startActivity(intent);

            }
        });
    }

    //CONEXION DB
    @Override
    public void doInBackground() {
        switch (tipo){
            case MOTOS:
                mResult = Connector.getConector().put(Moto.class,moto, "/motos");
                break;
            case COCHE:
                cResult = Connector.getConector().put(Coche.class,coche, "/coche");
                break;
            case PATIN:
                pResult = Connector.getConector().put(Patinete.class,patinete, "/patin");
                break;
            case BICIS:
                bResult = Connector.getConector().put(Bicicleta.class,bicicleta, "/bicis");
                break;
        }
    }

    @Override
    public void doInUI() {
        if(tipo==TipoVehiculos.COCHE){
            coche = new Coche(matricula.getText().toString(),
                    Float.parseFloat(precio.getText().toString()),
                    marca.getText().toString(),
                    descripcion.getText().toString(),
                    color.getText().toString(),
                    Integer.parseInt(bateria.getText().toString()),
                    fecha.getText().toString(),
                    estado.getText().toString(),
                    carnet.getText().toString(),
                    TipoVehiculos.COCHE,
                    Integer.parseInt(plazasCoche.getText().toString()),
                    Integer.parseInt(puertasCoche.getText().toString()));

        }else if(tipo==TipoVehiculos.PATIN){
            patinete = new Patinete(
                    matricula.getText().toString(),
                    Float.parseFloat(precio.getText().toString()),
                    marca.getText().toString(),
                    descripcion.getText().toString(),
                    color.getText().toString(),
                    Integer.parseInt(bateria.getText().toString()),
                    fecha.getText().toString(),
                    estado.getText().toString(),
                    carnet.getText().toString(),
                    TipoVehiculos.PATIN,
                    Integer.parseInt(ruedasPatin.getText().toString()),
                    Integer.parseInt(tamanoPatin.getText().toString()));

        }else if(tipo==TipoVehiculos.MOTOS){
            moto = new Moto(
                    matricula.getText().toString(),
                    Float.parseFloat(precio.getText().toString()),
                    marca.getText().toString(),
                    descripcion.getText().toString(),
                    color.getText().toString(),
                    Integer.parseInt(bateria.getText().toString()),
                    fecha.getText().toString(),
                    estado.getText().toString(),
                    carnet.getText().toString(),
                    TipoVehiculos.MOTOS,
                    Integer.parseInt(velocidadMoto.getText().toString()),
                    Integer.parseInt(cilindradaMoto.getText().toString()));

        }else if(tipo==TipoVehiculos.BICIS){
            bicicleta = new Bicicleta(
                    matricula.getText().toString(),
                    Float.parseFloat(precio.getText().toString()),
                    marca.getText().toString(),
                    descripcion.getText().toString(),
                    color.getText().toString(),
                    Integer.parseInt(bateria.getText().toString()),
                    fecha.getText().toString(),
                    estado.getText().toString(),
                    carnet.getText().toString(),
                    TipoVehiculos.BICIS,
                    tipoBici.getText().toString());

        }
        Toast.makeText(this, tipo + " AÑADIDO", Toast.LENGTH_SHORT).show();
    }


    /*MENU*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
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