package com.example.taserfan.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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

    private TipoVehiculos tipo;
    private String v_matricula;
    private Coche coche;
    private Moto moto;
    private Patinete patinete;
    private Bicicleta bicicleta;

    private Result result;
    private Result<Coche> cResult;
    private Result<Moto> mResult;
    private Result<Bicicleta> bResult;
    private Result<Patinete> pResult;
    private AlertDialog.Builder alert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upd_vehiculo);

        alert = new AlertDialog.Builder(UpdVehiculo.this);

        lineaBici = findViewById(R.id.lineaTipoBici);
        lineaCoche1 = findViewById(R.id.lineapuertasC);
        lineaCoche2 = findViewById(R.id.lineaplazasC);
        lineaMoto1 = findViewById(R.id.lineaVelocidadM);
        lineaMoto2 = findViewById(R.id.lineacilindrada);
        lineaPatin1 = findViewById(R.id.lineatamano);
        lineaPatin2 = findViewById(R.id.lineaRuedaP);

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
        v_matricula = getIntent().getExtras().getString("matr");

        executeCall(this);

        /* mostrar algunas linear*/
        if (tipo == TipoVehiculos.COCHE) {
            lineaBici.setVisibility(View.GONE);
            lineaCoche1.setVisibility(View.VISIBLE);
            lineaCoche2.setVisibility(View.VISIBLE);
            lineaMoto1.setVisibility(View.GONE);
            lineaMoto2.setVisibility(View.GONE);
            lineaPatin1.setVisibility(View.GONE);
            lineaPatin2.setVisibility(View.GONE);
        }else if (tipo == TipoVehiculos.MOTOS) {
            lineaBici.setVisibility(View.GONE);
            lineaCoche1.setVisibility(View.GONE);
            lineaCoche2.setVisibility(View.GONE);
            lineaMoto1.setVisibility(View.VISIBLE);
            lineaMoto2.setVisibility(View.VISIBLE);
            lineaPatin1.setVisibility(View.GONE);
            lineaPatin2.setVisibility(View.GONE);
        }else if (tipo == TipoVehiculos.PATIN) {
            lineaBici.setVisibility(View.GONE);
            lineaCoche1.setVisibility(View.GONE);
            lineaCoche2.setVisibility(View.GONE);
            lineaMoto1.setVisibility(View.GONE);
            lineaMoto2.setVisibility(View.GONE);
            lineaPatin1.setVisibility(View.VISIBLE);
            lineaPatin2.setVisibility(View.VISIBLE);
        }else if (tipo == TipoVehiculos.BICIS) {
            lineaBici.setVisibility(View.VISIBLE);
            lineaCoche1.setVisibility(View.GONE);
            lineaCoche2.setVisibility(View.GONE);
            lineaMoto1.setVisibility(View.GONE);
            lineaMoto2.setVisibility(View.GONE);
            lineaPatin1.setVisibility(View.GONE);
        }

        /* cancelar */
        btn_cancel = findViewById(R.id.btn_cancel_add);
        btn_cancel.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RVActivity.class);
            startActivity(intent);
        });

        /* actualizar */
        btn_upd_veh = findViewById(R.id.btn_upd_veh);
        btn_upd_veh.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RVActivity.class);

            if (matricula.getText().toString().equals("")){
                Tools.alertaVacio(alert,"La matricula está vacia");
            }else if (marca.getText().toString().equals("")){
                Tools.alertaVacio(alert,"La marca está vacia");
            }else if (fecha.getText().toString().equals("")){
                Tools.alertaVacio(alert,"La fecha está vacia");
            }else if (estado.getText().toString().equals("")){
                Tools.alertaVacio(alert,"El estado está vacio");
            }else if (precio.getText().toString().equals("")){
                Tools.alertaVacio(alert,"El precio está vacio");
            }else if (descripcion.getText().toString().equals("")){
                Tools.alertaVacio(alert,"La descripción está vacia");
            }else if (color.getText().toString().equals("")){
                Tools.alertaVacio(alert,"El color está vacio");
            }else if (carnet.getText().toString().equals("")){
                Tools.alertaVacio(alert,"El carnet está vacio");
            }else if (bateria.getText().toString().equals("")){
                Tools.alertaVacio(alert,"La bateria está vacia");
            }else {
                switch (tipo){
                    case MOTOS:
                        if (cilindradaMoto.getText().toString().equals("")){
                            Tools.alertaVacio(alert,"La cilindrada está vacia");
                        }else if (velocidadMoto.getText().toString().equals("")){
                            Tools.alertaVacio(alert,"La velocidad está vacia");
                        }else {
                            executeCall(new CallInterface() {
                                @Override
                                public void doInBackground() {
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
                                    result = Connector.getConector().put(Moto.class,moto, "/motos");
                                }

                                @Override
                                public void doInUI() {
                                    if (result instanceof Result.Success) {
                                        if (result instanceof Result.Success) {
                                            alert.setMessage("Vehiculo actualizado con existo")
                                                    .setTitle("ACTUALIZANDO")
                                                    .setPositiveButton("Aceptar", null);
                                            AlertDialog alertDialog = alert.create();
                                            alertDialog.show();
                                        } else {
                                            Result.Error error = (Result.Error) result;
                                            Tools.alertError(alert,error,"ACTUALIZANDO");
                                        }
                                    }
                                }
                            });
                            startActivity(intent);
                        }
                        break;
                    case COCHE:
                        if (puertasCoche.getText().toString().equals("")){
                            Tools.alertaVacio(alert,"El numero de puertas está vacio");
                        }else if (plazasCoche.getText().toString().equals("")){
                            Tools.alertaVacio(alert,"El numero de plazas está vacio");
                        }else {
                            executeCall(new CallInterface() {
                                @Override
                                public void doInBackground() {
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
                                    result = Connector.getConector().put(Coche.class,coche, "/coche");
                                }

                                @Override
                                public void doInUI() {
                                    if (result instanceof Result.Success) {
                                        if (result instanceof Result.Success) {
                                            alert.setMessage("Vehiculo actualizado con existo")
                                                    .setTitle("ACTUALIZANDO")
                                                    .setPositiveButton("Aceptar", null);
                                            AlertDialog alertDialog = alert.create();
                                            alertDialog.show();
                                        } else {
                                            Result.Error error = (Result.Error) result;
                                            Tools.alertError(alert,error,"ACTUALIZANDO");
                                        }
                                    }
                                }
                            });
                            startActivity(intent);
                        }
                        break;
                    case PATIN:
                        if (ruedasPatin.getText().toString().equals("")){
                            Tools.alertaVacio(alert,"El numero de ruedas está vacio");
                        }else if (tamanoPatin.getText().toString().equals("")){
                            Tools.alertaVacio(alert,"El tamaño de las ruedas está vacio");
                        }else {
                            executeCall(new CallInterface() {
                                @Override
                                public void doInBackground() {
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
                                    result = Connector.getConector().put(Patinete.class,patinete, "/patin");
                                }

                                @Override
                                public void doInUI() {
                                    if (result instanceof Result.Success) {
                                        if (result instanceof Result.Success) {
                                            alert.setMessage("Vehiculo actualizado con existo")
                                                    .setTitle("ACTUALIZANDO")
                                                    .setPositiveButton("Aceptar", null);
                                            AlertDialog alertDialog = alert.create();
                                            alertDialog.show();
                                        } else {
                                            Result.Error error = (Result.Error) result;
                                            Tools.alertError(alert,error,"ACTUALIZANDO");

                                        }
                                    }
                                }
                            });
                            startActivity(intent);
                        }
                        break;
                    case BICIS:
                        if (tipoBici.getText().toString().equals("")){
                            Tools.alertaVacio(alert,"El tipo está vacio");
                        }else {
                            executeCall(new CallInterface() {
                                @Override
                                public void doInBackground() {
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
                                    result = Connector.getConector().put(Bicicleta.class,bicicleta, "/bicis");
                                }

                                @Override
                                public void doInUI() {
                                    if (result instanceof Result.Success) {
                                        if (result instanceof Result.Success) {
                                            alert.setMessage("Vehiculo actualizado con existo")
                                                    .setTitle("ACTUALIZANDO")
                                                    .setPositiveButton("Aceptar", null);
                                            AlertDialog alertDialog = alert.create();
                                            alertDialog.show();
                                        } else {
                                            Result.Error error = (Result.Error) result;
                                            Tools.alertError(alert,error,"ACTUALIZANDO");
                                        }
                                    }
                                }
                            });
                            startActivity(intent);
                        }
                        break;
                }
            }
        });
    }

    //CONEXION DB (Obtener el vehiculo)
    @Override
    public void doInBackground() {
        if (tipo == TipoVehiculos.COCHE){
            cResult = Connector.getConector().get(Coche.class,"/coche?matricula="+v_matricula);
        }else if (tipo == TipoVehiculos.MOTOS){
            mResult = Connector.getConector().get(Moto.class,"/motos?matricula="+v_matricula);
        }else if (tipo == TipoVehiculos.BICIS) {
            bResult = Connector.getConector().get(Bicicleta.class,"/bicis?matricula="+v_matricula);
        }else if (tipo == TipoVehiculos.PATIN) {
            pResult = Connector.getConector().get(Patinete.class,"/patin?matricula="+v_matricula);
        }
    }

    @Override
    public void doInUI() {
        if (tipo == TipoVehiculos.COCHE){
            if (cResult instanceof Result.Success){
                coche = ((Result.Success<Coche>) cResult).getData();

                matricula.setText(coche.getMatricula());
                marca.setText(coche.getMarca());
                fecha.setText(String.valueOf(coche.getFechaAdq()));
                estado.setText(coche.getEstado());
                precio.setText(String.valueOf(coche.getPrecioHora()));
                descripcion.setText(coche.getDescripcion());
                color.setText(coche.getColor());
                carnet.setText(coche.getIdCarnet());
                bateria.setText(String.valueOf(coche.getBateria()));
                puertasCoche.setText(String.valueOf(coche.getNumPuertas()));
                plazasCoche.setText(String.valueOf(coche.getNumPlazas()));
            }else{
                Result.Error error = (Result.Error) cResult;
                Tools.alertError(alert,error,"ACTUALIZANDO");
            }


        }else if (tipo == TipoVehiculos.MOTOS){
            moto = ((Result.Success<Moto>) mResult).getData();

            matricula.setText(moto.getMatricula());
            marca.setText(moto.getMarca());
            fecha.setText(String.valueOf(moto.getFechaAdq()));
            estado.setText(moto.getEstado());
            precio.setText(String.valueOf(moto.getPrecioHora()));
            descripcion.setText(moto.getDescripcion());
            color.setText(moto.getColor());
            carnet.setText(moto.getIdCarnet());
            bateria.setText(String.valueOf(moto.getBateria()));
            cilindradaMoto.setText(String.valueOf(moto.getCilindrada()));
            velocidadMoto.setText(String.valueOf(moto.getVelocidadMax()));

        }else if (tipo == TipoVehiculos.BICIS) {
            bicicleta = ((Result.Success<Bicicleta>) bResult).getData();

            matricula.setText(bicicleta.getMatricula());
            marca.setText(bicicleta.getMarca());
            fecha.setText(String.valueOf(bicicleta.getFechaAdq()));
            estado.setText(bicicleta.getEstado());
            precio.setText(String.valueOf(bicicleta.getPrecioHora()));
            descripcion.setText(bicicleta.getDescripcion());
            color.setText(bicicleta.getColor());
            carnet.setText(bicicleta.getIdCarnet());
            bateria.setText(String.valueOf(bicicleta.getBateria()));
            tipoBici.setText(bicicleta.getTipo());

        }else if (tipo == TipoVehiculos.PATIN) {
            patinete = ((Result.Success<Patinete>) pResult).getData();

            matricula.setText(patinete.getMatricula());
            marca.setText(patinete.getMarca());
            fecha.setText(String.valueOf(patinete.getFechaAdq()));
            estado.setText(patinete.getEstado());
            precio.setText(String.valueOf(patinete.getPrecioHora()));
            descripcion.setText(patinete.getDescripcion());
            color.setText(patinete.getColor());
            carnet.setText(patinete.getIdCarnet());
            bateria.setText(String.valueOf(patinete.getBateria()));
            tamanoPatin.setText(String.valueOf(patinete.getTamanyo()));
            ruedasPatin.setText(String.valueOf(patinete.getNumRuedas()));
        }
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