package com.example.taserfan.actividades;

import androidx.annotation.NonNull;

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

import java.util.ArrayList;
import java.util.List;

//TODO llamada que pase info
public class AddVehiculo extends BaseActivity implements CallInterface {
     private LinearLayout lineaBici, lineaMoto1, lineaMoto2, lineaPatin1, lineaPatin2, lineaCoche1, lineaCoche2;
     private EditText matricula, marca, fecha, estado, precio, descripcion, color, carnet, bateria,
        puertasCoche, plazasCoche, velocidadMoto, cilindradaMoto, tipoBici, ruedasPatin, tamanoPatin;

     private Spinner spinner;
     private Button btn_add_veh, btn_cancel;
     private String tipo;
     private Result<Vehiculo> result;

    Result<Coche> cResult;
    Result<Moto> mResult;
    Result<Bicicleta> bResult;
    Result<Patinete> pResult;

    private TipoVehiculos tipoVehiculo;
    private Coche coche;
    private Moto moto;
    private Patinete patinete;
    private Bicicleta bicicleta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehiculo);

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
        marca = findViewById(R.id.inputMatriucla);
        fecha = findViewById(R.id.inputMatriucla);
        estado = findViewById(R.id.inputMatriucla);
        precio = findViewById(R.id.inputMatriucla);
        descripcion = findViewById(R.id.inputMatriucla);
        color = findViewById(R.id.inputMatriucla);
        carnet = findViewById(R.id.inputMatriucla);
        bateria = findViewById(R.id.inputMatriucla);
        puertasCoche = findViewById(R.id.inputMatriucla);
        plazasCoche = findViewById(R.id.inputMatriucla);
        velocidadMoto = findViewById(R.id.inputMatriucla);
        cilindradaMoto = findViewById(R.id.inputMatriucla);
        tipoBici = findViewById(R.id.inputMatriucla);
        ruedasPatin = findViewById(R.id.inputMatriucla);
        tamanoPatin = findViewById(R.id.inputMatriucla);

        btn_add_veh=findViewById(R.id.btn_add_veh);
        btn_add_veh.setOnClickListener(view -> {
            executeCall(AddVehiculo.this);
            //anar a la lista
            //TODO añadir en el menu el icono de tornar arrer en detalle y ad
        });

        btn_cancel = findViewById(R.id.btn_cancel_add);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RVActivity.class);
                startActivity(intent);
            }
        });

        /*SPINNER*/
        spinner = findViewById(R.id.spinner_tipos);
        List<TipoVehiculos> tipos=new ArrayList<>();
            tipos.add(TipoVehiculos.COCHE);
            tipos.add(TipoVehiculos.MOTOS);
            tipos.add(TipoVehiculos.BICIS);
            tipos.add(TipoVehiculos.PATIN);
        ArrayAdapter<TipoVehiculos> spinnerArray = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,tipos);
        spinner.setAdapter(spinnerArray);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override //TODO QUE CAMBIE EL NOM BOTON, podem posar un cancelar que torne a la RV
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tipoVehiculo= tipos.get(i);

                if(tipo=="COCHE"){
                    lineaBici.setVisibility(View.GONE);
                    lineaCoche1.setVisibility(View.VISIBLE);
                    lineaCoche2.setVisibility(View.VISIBLE);
                    lineaMoto1.setVisibility(View.GONE);
                    lineaMoto2.setVisibility(View.GONE);
                    lineaPatin1.setVisibility(View.GONE);
                    lineaPatin2.setVisibility(View.GONE);
                    btn_add_veh.setText("AÑADIR COCHE");
                    //(matricula, precioHora, marca, descripcion, color, bateria, fechaAdq, estado, idCarnet);
                    coche = new Coche(matricula.getText().toString(),
                            precio.getText().toString(),
                            marca.getText().toString(),
                            descripcion.getText().toString(),
                            color.getText().toString(),
                            bateria.getText().toString(),
                            fecha.getText().toString(),
                            estado.getText().toString(),
                            carnet.getText().toString(),
                            plazasCoche.getText().toString(),puertasCoche.getText().toString());

                }else if(tipo=="PATINETE"){
                    lineaBici.setVisibility(View.GONE);
                    lineaCoche1.setVisibility(View.GONE);
                    lineaCoche2.setVisibility(View.GONE);
                    lineaMoto1.setVisibility(View.GONE);
                    lineaMoto2.setVisibility(View.GONE);
                    lineaPatin1.setVisibility(View.VISIBLE);
                    lineaPatin2.setVisibility(View.VISIBLE);
                    btn_add_veh.setText("AÑADIR PATINETE");

                    patinete = new Patinete(matricula.getText().toString(),
                            precio.getText().toString(),
                            marca.getText().toString(),
                            descripcion.getText().toString(),
                            color.getText().toString(),
                            bateria.getText().toString(),
                            fecha.getText().toString(),
                            estado.getText().toString(),
                            carnet.getText().toString(),
                            ruedasPatin.getText().toString(),tamanoPatin.getText().toString());

                }else if(tipo=="MOTO"){
                    lineaBici.setVisibility(View.GONE);
                    lineaCoche1.setVisibility(View.GONE);
                    lineaCoche2.setVisibility(View.GONE);
                    lineaMoto1.setVisibility(View.VISIBLE);
                    lineaMoto2.setVisibility(View.VISIBLE);
                    lineaPatin1.setVisibility(View.GONE);
                    lineaPatin2.setVisibility(View.GONE);
                    btn_add_veh.setText("AÑADIR MOTO");

                    moto = new Moto(matricula.getText().toString(),
                            precio.getText().toString(),
                            marca.getText().toString(),
                            descripcion.getText().toString(),
                            color.getText().toString(),
                            bateria.getText().toString(),
                            fecha.getText().toString(),
                            estado.getText().toString(),
                            carnet.getText().toString(),
                            velocidadMoto.getText().toString(),
                            cilindradaMoto.getText().toString());

                }else if(tipo=="BICICLETA"){
                    lineaBici.setVisibility(View.VISIBLE);
                    lineaCoche1.setVisibility(View.GONE);
                    lineaCoche2.setVisibility(View.GONE);
                    lineaMoto1.setVisibility(View.GONE);
                    lineaMoto2.setVisibility(View.GONE);
                    lineaPatin1.setVisibility(View.GONE);
                    lineaPatin2.setVisibility(View.GONE);
                    btn_add_veh.setText("AÑADIR BICICLETA");

                    bicicleta = new Bicicleta(matricula.getText().toString(),
                            precio.getText().toString(),
                            marca.getText().toString(),
                            descripcion.getText().toString(),
                            color.getText().toString(),
                            bateria.getText().toString(),
                            fecha.getText().toString(),
                            estado.getText().toString(),
                            carnet.getText().toString(),
                            tipoBici.getText().toString());

                }else {
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

    //CONEXION BD
    @Override
    public void doInBackground() {
        switch (tipoVehiculo){
            case MOTOS:
                mResult = Connector.getConector().post(Moto.class,moto, "/motos");
                break;
            case COCHE:
                cResult = Connector.getConector().post(Coche.class,coche, "/coche");
                break;
            case PATIN:
                pResult = Connector.getConector().post(Patinete.class,patinete, "/patin");
                break;
            case BICIS:
                bResult = Connector.getConector().post(Bicicleta.class,bicicleta, "/bicis");
                break;
        }
    }

    @Override
    public void doInUI() {
        Intent intent;
        if (result instanceof Result.Success){
            LoggedInUserRepository.getInstance().login(((Result.Success<Empleado>) result).getData());
            intent = new Intent(getApplicationContext(), RVActivity.class);
            startActivity(intent);
            Toast.makeText(this, tipo + " AÑADIDO", Toast.LENGTH_SHORT).show();
        }else {
            Result.Error resultado = (Result.Error) result;
            Toast.makeText(this, "MAL" + resultado.getMessage(), Toast.LENGTH_SHORT).show();
        }


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