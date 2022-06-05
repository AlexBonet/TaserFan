package com.example.taserfan.actividades;

import androidx.annotation.NonNull;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.taserfan.API.Connector;
import com.example.taserfan.API.Result;
import com.example.taserfan.Model.Empleado;
import com.example.taserfan.R;
import com.example.taserfan.actividades.prefe.PreferenciasActivity;
import com.example.taserfan.actividades.vehiculos.Bicicleta;
import com.example.taserfan.actividades.vehiculos.Coche;
import com.example.taserfan.actividades.vehiculos.Moto;
import com.example.taserfan.actividades.vehiculos.Patinete;
import com.example.taserfan.actividades.vehiculos.TipoVehiculos;
import com.example.taserfan.actividades.vehiculos.Vehiculo;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;

import java.sql.Date;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

//TODO llamada que pase info
public class AddVehiculo extends BaseActivity{
    private LinearLayout lineaBici, lineaMoto1, lineaMoto2, lineaPatin1, lineaPatin2, lineaCoche1, lineaCoche2;
    private EditText matricula, marca, fecha, estado, precio, descripcion, color, carnet, bateria,
            puertasCoche, plazasCoche, velocidadMoto, cilindradaMoto, tipoBici, ruedasPatin, tamanoPatin;

    private Spinner spinner;
    private Button btn_add_veh, btn_cancel;
    private String tipo;
    private Intent intent;

    private Result<Coche> cResult;
    private Result<Moto> mResult;
    private Result<Bicicleta> bResult;
    private Result<Patinete> pResult;

    private TipoVehiculos tipoVehiculo;
    private Coche coche;
    private Moto moto;
    private Patinete patinete;
    private Bicicleta bicicleta;

    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehiculo);

        alert = new AlertDialog.Builder(AddVehiculo.this);

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

        matricula = findViewById(R.id.inputMatriucla);
        marca = findViewById(R.id.inputMarca);
        fecha = findViewById(R.id.inputFecha);
        estado = findViewById(R.id.inputEstado);
        precio = findViewById(R.id.inputPrecio);
        descripcion = findViewById(R.id.inputDescripcion);
        color = findViewById(R.id.inputColor);
        carnet = findViewById(R.id.inputCarnet);
        bateria = findViewById(R.id.inputBateria);
        puertasCoche = findViewById(R.id.inputpuertasc);
        plazasCoche = findViewById(R.id.inputplazasC);
        velocidadMoto = findViewById(R.id.inputVelocidadM);
        cilindradaMoto = findViewById(R.id.inputCilindradaM);
        tipoBici = findViewById(R.id.inputbici);
        ruedasPatin = findViewById(R.id.inputRuedasP);
        tamanoPatin = findViewById(R.id.inputtamano);

        btn_add_veh = findViewById(R.id.btn_add_veh);
        btn_add_veh.setOnClickListener(view -> {
            intent = new Intent(getApplicationContext(), RVActivity.class);

            if (matricula.getText().toString().equals("")) {
                Tools.alertaVacio(alert, "La matricula está vacia");
            } else if (marca.getText().toString().equals("")) {
                Tools.alertaVacio(alert, "La marca está vacia");
            } else if (fecha.getText().toString().equals("")) {
                Tools.alertaVacio(alert, "La fecha está vacia");
            } else if (estado.getText().toString().equals("")) {
                Tools.alertaVacio(alert, "El estado está vacio");
            } else if (precio.getText().toString().equals("")) {
                Tools.alertaVacio(alert, "El precio está vacio");
            } else if (descripcion.getText().toString().equals("")) {
                Tools.alertaVacio(alert, "La descripción está vacia");
            } else if (color.getText().toString().equals("")) {
                Tools.alertaVacio(alert, "El color está vacio");
            } else if (carnet.getText().toString().equals("")) {
                Tools.alertaVacio(alert, "El carnet está vacio");
            } else if (bateria.getText().toString().equals("")) {
                Tools.alertaVacio(alert, "La bateria está vacia");
            }else {
                switch (tipoVehiculo){
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
                                    mResult = Connector.getConector().post(Moto.class, moto, "/motos");
                                }

                                @Override
                                public void doInUI() {
                                    if (mResult instanceof Result.Success) {
                                        alert.setMessage("Vehiculo añadido con existo")
                                                .setTitle("AÑADIENDO")
                                                .setPositiveButton("Aceptar", null);
                                        AlertDialog alertDialog = alert.create();
                                        alertDialog.show();

                                        startActivity(intent);

                                    } else {
                                        Result.Error error = (Result.Error) mResult;
                                        Tools.alertError(alert, error, "AÑADIENDO");

                                    }
                                }
                            });
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
                                    cResult = Connector.getConector().post(Coche.class, coche, "/coche");
                                }

                                @Override
                                public void doInUI() {
                                    if (cResult instanceof Result.Success) {
                                        alert.setMessage("Vehiculo añadido con existo")
                                                .setTitle("AÑADIENDO")
                                                .setPositiveButton("Aceptar", null);
                                        AlertDialog alertDialog = alert.create();
                                        alertDialog.show();

                                        startActivity(intent);

                                    } else {
                                        Result.Error error = (Result.Error) cResult;
                                        Tools.alertError(alert, error, "AÑADIENDO");
                                    }
                                }
                            });
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
                                    pResult = Connector.getConector().post(Patinete.class, patinete, "/patin");
                                }

                                @Override
                                public void doInUI() {
                                    if (pResult instanceof Result.Success) {
                                        alert.setMessage("Vehiculo añadido con existo")
                                                .setTitle("AÑADIENDO")
                                                .setPositiveButton("Aceptar", null);
                                        AlertDialog alertDialog = alert.create();
                                        alertDialog.show();

                                        startActivity(intent);

                                    } else {
                                        Result.Error error = (Result.Error) pResult;
                                        Tools.alertError(alert, error, "AÑADIENDO");
                                    }
                                }
                            });
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
                                    bResult = Connector.getConector().post(Bicicleta.class, bicicleta, "/bicis");
                                }

                                @Override
                                public void doInUI() {
                                    if (bResult instanceof Result.Success) {
                                        alert.setMessage("Vehiculo añadido con existo")
                                                .setTitle("AÑADIENDO")
                                                .setPositiveButton("Aceptar", null);
                                        AlertDialog alertDialog = alert.create();
                                        alertDialog.show();

                                        startActivity(intent);

                                    } else {
                                        Result.Error error = (Result.Error) bResult;
                                        Tools.alertError(alert, error, "AÑADIENDO");
                                    }
                                }
                            });
                        }
                        break;
                }
            }
        });

        btn_cancel = findViewById(R.id.btn_cancel_add);
        btn_cancel.setOnClickListener(view -> {
            intent = new Intent(getApplicationContext(), RVActivity.class);
            startActivity(intent);
        });

        /*SPINNER*/
        spinner = findViewById(R.id.spinner_tipos);
        List<TipoVehiculos> tipos = new ArrayList<>();
        tipos.add(TipoVehiculos.COCHE);
        tipos.add(TipoVehiculos.MOTOS);
        tipos.add(TipoVehiculos.BICIS);
        tipos.add(TipoVehiculos.PATIN);
        ArrayAdapter<TipoVehiculos> spinnerArray = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipos);
        spinner.setAdapter(spinnerArray);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tipo = tipos.get(i).toString();
                tipoVehiculo = tipos.get(i);

                if (tipo == TipoVehiculos.COCHE.toString()) {
                    lineaBici.setVisibility(View.GONE);
                    lineaCoche1.setVisibility(View.VISIBLE);
                    lineaCoche2.setVisibility(View.VISIBLE);
                    lineaMoto1.setVisibility(View.GONE);
                    lineaMoto2.setVisibility(View.GONE);
                    lineaPatin1.setVisibility(View.GONE);
                    lineaPatin2.setVisibility(View.GONE);
                    btn_add_veh.setText("AÑADIR COCHE");

                } else if (tipo == TipoVehiculos.PATIN.toString()) {
                    lineaBici.setVisibility(View.GONE);
                    lineaCoche1.setVisibility(View.GONE);
                    lineaCoche2.setVisibility(View.GONE);
                    lineaMoto1.setVisibility(View.GONE);
                    lineaMoto2.setVisibility(View.GONE);
                    lineaPatin1.setVisibility(View.VISIBLE);
                    lineaPatin2.setVisibility(View.VISIBLE);
                    btn_add_veh.setText("AÑADIR PATINETE");

                } else if (tipo == TipoVehiculos.MOTOS.toString()) {
                    lineaBici.setVisibility(View.GONE);
                    lineaCoche1.setVisibility(View.GONE);
                    lineaCoche2.setVisibility(View.GONE);
                    lineaMoto1.setVisibility(View.VISIBLE);
                    lineaMoto2.setVisibility(View.VISIBLE);
                    lineaPatin1.setVisibility(View.GONE);
                    lineaPatin2.setVisibility(View.GONE);
                    btn_add_veh.setText("AÑADIR MOTO");

                } else if (tipo == TipoVehiculos.BICIS.toString()) {
                    lineaBici.setVisibility(View.VISIBLE);
                    lineaCoche1.setVisibility(View.GONE);
                    lineaCoche2.setVisibility(View.GONE);
                    lineaMoto1.setVisibility(View.GONE);
                    lineaMoto2.setVisibility(View.GONE);
                    lineaPatin1.setVisibility(View.GONE);
                    lineaPatin2.setVisibility(View.GONE);
                    btn_add_veh.setText("AÑADIR BICICLETA");

                } else {
                    lineaBici.setVisibility(View.GONE);
                    lineaCoche1.setVisibility(View.GONE);
                    lineaCoche2.setVisibility(View.GONE);
                    lineaMoto1.setVisibility(View.GONE);
                    lineaMoto2.setVisibility(View.GONE);
                    lineaPatin1.setVisibility(View.GONE);
                    lineaPatin2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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