package com.example.taserfan.actividades;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taserfan.API.Result;
import com.example.taserfan.R;
import com.example.taserfan.actividades.prefe.PreferenciasActivity;
import com.example.taserfan.actividades.vehiculos.Bicicleta;
import com.example.taserfan.actividades.vehiculos.Coche;
import com.example.taserfan.actividades.vehiculos.Moto;
import com.example.taserfan.actividades.vehiculos.Patinete;
import com.example.taserfan.actividades.vehiculos.Vehiculo;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;

//TODO SABER DE QUIN tipo es cada vehiculo y fer tot
public class VistaExtendiad<T> extends BaseActivity implements CallInterface {

    private ImageButton edit, back;
    private Intent intent;
    private TextView matricula, marca, fecha, estado, precio, descripcion, color, carnet, bateria,
            puertasCoche, plazasCoche, velocidadMoto, cilindradaMoto, tipoBici, ruedasPatin, tamanoPatin;
    private LinearLayout lineaBici, lineaMoto1, lineaMoto2, lineaPatin1, lineaPatin2, lineaCoche1, lineaCoche2;
    private ImageView img;
    Vehiculo v;
    Result<T> result;
    Result<Coche> cResult;
    Result<Moto> mResult;
    Result<Bicicleta> bResult;
    Result<Patinete> pResult;
    Coche coche;
    Moto moto;
    Patinete patinete;
    Bicicleta bicicleta;
    private Object Result;
    private Object Coche;

    //todo mirar el tipo pa la foto: puc fer el execute col de get y el tipo de result que torne dirá el tipo y la foto que es

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_view);

        v = (Vehiculo) getIntent().getExtras().getSerializable("vehicle");

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

        lineaBici = findViewById(R.id.linExtBici);
        lineaCoche1 = findViewById(R.id.linExtPuertasC);
        lineaCoche2 = findViewById(R.id.lineaExtPlazasC);
        lineaMoto1 = findViewById(R.id.linExtVelM);
        lineaMoto2 = findViewById(R.id.linExtCiliM);
        lineaPatin1 = findViewById(R.id.linExtRuedasP);
        lineaPatin2 = findViewById(R.id.lineaExtTamP);

        lineaBici.setVisibility(View.GONE);
        lineaCoche1.setVisibility(View.GONE);
        lineaCoche2.setVisibility(View.GONE);
        lineaMoto1.setVisibility(View.GONE);
        lineaMoto2.setVisibility(View.GONE);
        lineaPatin1.setVisibility(View.GONE);
        lineaPatin2.setVisibility(View.GONE);

        matricula = findViewById(R.id.matriculaExt);
        marca = findViewById(R.id.marcaExt);
        fecha = findViewById(R.id.fechaExt);
        estado = findViewById(R.id.estadoExt);
        precio = findViewById(R.id.precioExt);
        descripcion = findViewById(R.id.descripExt);
        color = findViewById(R.id.colorExt);
        carnet = findViewById(R.id.carnetExt);
        bateria = findViewById(R.id.bateriaExt);
        puertasCoche = findViewById(R.id.puertasExt);
        plazasCoche = findViewById(R.id.plazasExt);
        velocidadMoto = findViewById(R.id.velocidadExt);
        cilindradaMoto = findViewById(R.id.cilindradaExt);
        tipoBici = findViewById(R.id.biciExt);
        ruedasPatin = findViewById(R.id.ruedasExtP);
        tamanoPatin = findViewById(R.id.tamanoExtP);

        img = findViewById(R.id.imgVehExt);
    }

    //CONEXION DB
    @Override
    public void doInBackground() {//todo?? com pillem el tipo y fem la llamada
/*TODO
        cResult = Connector.getConector().get(Coche.class,"/coche?matricula="+v.getMatricula());

        if (Connector.getConector().get(Coche.class,"/coche?matricula="+v.getMatricula()) == Result<Coche>){
            coche = ((Result.Success<Coche>) cResult).getData();
            matricula.setText(coche.getMatricula());
            marca.setText(coche.getMarca());
            fecha.setText(String.valueOf(coche.getFechaAdq()));
            estado.setText(coche.getEstado());
            precio.setText(String.valueOf(coche.getPrecioHora()));
            descripcion.setText(coche.getDescripcion());
            color.setText(coche.getColor());
            carnet.setText(coche.getIdCarnet());
            bateria.setText(coche.getBateria());
            puertasCoche.setText(coche.getNumPuertas());
            plazasCoche.setText(coche.getNumPlazas());

        }else if (Connector.getConector().get(Moto.class,"/coche?matricula="+v.getMatricula()) instanceof Result<Moto>){
            moto = ((Result.Success<Moto>) cResult).getData();
            matricula.setText(moto.getMatricula());
            marca.setText(moto.getMarca());
            fecha.setText(String.valueOf(moto.getFechaAdq()));
            estado.setText(moto.getEstado());
            precio.setText(String.valueOf(moto.getPrecioHora()));
            descripcion.setText(moto.getDescripcion());
            color.setText(moto.getColor());
            carnet.setText(moto.getIdCarnet());
            bateria.setText(moto.getBateria());
            cilindradaMoto.setText(moto.getCilindrada());
            velocidadMoto.setText(moto.getVelocidadMax());

        }else if (Connector.getConector().get(Bicicleta.class,"/coche?matricula="+v.getMatricula()) instanceof Result<Bicicleta>) {
            bicicleta = ((Result.Success<Bicicleta>) cResult).getData();
            matricula.setText(bicicleta.getMatricula());
            marca.setText(bicicleta.getMarca());
            fecha.setText(String.valueOf(bicicleta.getFechaAdq()));
            estado.setText(bicicleta.getEstado());
            precio.setText(String.valueOf(bicicleta.getPrecioHora()));
            descripcion.setText(bicicleta.getDescripcion());
            color.setText(bicicleta.getColor());
            carnet.setText(bicicleta.getIdCarnet());
            bateria.setText(bicicleta.getBateria());
            cilindradaMoto.setText(bicicleta.getTipo());
        }else if (Connector.getConector().get(Patinete.class,"/coche?matricula="+v.getMatricula()) instanceof Result<Patinete>){
            patinete = ((Result.Success<Patinete>) cResult).getData();
            matricula.setText(patinete.getMatricula());
            marca.setText(patinete.getMarca());
            fecha.setText(String.valueOf(patinete.getFechaAdq()));
            estado.setText(patinete.getEstado());
            precio.setText(String.valueOf(patinete.getPrecioHora()));
            descripcion.setText(patinete.getDescripcion());
            color.setText(patinete.getColor());
            carnet.setText(patinete.getIdCarnet());
            bateria.setText(patinete.getBateria());
            tamanoPatin.setText(patinete.getTamanyo());
            ruedasPatin.setText(patinete.getNumRuedas());
        }else if (cResult == Result<Coche> ){

        }*/

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