package com.example.taserfan.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.taserfan.API.Result;
import com.example.taserfan.Model.Empleado;
import com.example.taserfan.R;
import com.example.taserfan.actividades.vehiculos.Coche;
import com.example.taserfan.actividades.vehiculos.Patinete;
import com.example.taserfan.actividades.vehiculos.Vehiculo;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;

import java.util.ArrayList;
import java.util.List;

//TODO tot (fer que se puga scroll)
public class AddVehiculo extends BaseActivity implements CallInterface {
     private LinearLayout lineaBici, lineaMoto1, lineaMoto2, lineaPatin1, lineaPatin2, lineaCoche1, lineaCoche2;
     private EditText matricula, marca, fecha, estado, precio, descripcion, color, carnet, bateria,
        puertasCoche, plazasCoche, velocidadMoto, cilindradaMoto, tipoBici, ruedasPatin, tamanoPatin;
     private Spinner spinner;
     private Button btn_add_veh, btn_cancel;
     private String tipo;
     private Result<Vehiculo> result;

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
        List<String> tipos=new ArrayList<>();
            tipos.add("COCHE");
            tipos.add("PATINETE");
            tipos.add("MOTO");
            tipos.add("BICICLETA");
        ArrayAdapter<String> spinnerArray = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,tipos);
        spinner.setAdapter(spinnerArray);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override //TODO QUE CAMBIE EL NOM BOTON, podem posar un cancelar que torne a la RV
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tipo= tipos.get(i);

                if(tipo=="COCHE"){
                    lineaBici.setVisibility(View.GONE);
                    lineaCoche1.setVisibility(View.VISIBLE);
                    lineaCoche2.setVisibility(View.VISIBLE);
                    lineaMoto1.setVisibility(View.GONE);
                    lineaMoto2.setVisibility(View.GONE);
                    lineaPatin1.setVisibility(View.GONE);
                    lineaPatin2.setVisibility(View.GONE);

                    //(matricula, precioHora, marca, descripcion, color, bateria, fechaAdq, estado, idCarnet);
//                    Coche c = new Coche(matricula.getText().toString(),precio.getText().toString(),marca.getText().toString(),descripcion.getText().toString(),
//                    color.getText().toString(), ((int) bateria.getText().toString()),fecha.getText().toString(), estado.getText().toString(),carnet.getText().toString()
//                            ,plazasCoche.getText().toString(),puertasCoche.getText().toString());

                }else if(tipo=="PATINETE"){
                    Patinete p;
                    lineaBici.setVisibility(View.GONE);
                    lineaCoche1.setVisibility(View.GONE);
                    lineaCoche2.setVisibility(View.GONE);
                    lineaMoto1.setVisibility(View.GONE);
                    lineaMoto2.setVisibility(View.GONE);
                    lineaPatin1.setVisibility(View.VISIBLE);
                    lineaPatin2.setVisibility(View.VISIBLE);

//                    Patinete p = new Patinete(matricula.getText().toString(),precio.getText().toString(),marca.getText().toString(),descripcion.getText().toString(),
//                    color.getText().toString(), ((int) bateria.getText().toString()),fecha.getText().toString(), estado.getText().toString(),carnet.getText().toString()
//                            ,plazasCoche.getText().toString(),puertasCoche.getText().toString());)
                }else if(tipo=="MOTO"){
                    lineaBici.setVisibility(View.GONE);
                    lineaCoche1.setVisibility(View.GONE);
                    lineaCoche2.setVisibility(View.GONE);
                    lineaMoto1.setVisibility(View.VISIBLE);
                    lineaMoto2.setVisibility(View.VISIBLE);
                    lineaPatin1.setVisibility(View.GONE);
                    lineaPatin2.setVisibility(View.GONE);

//                    Moto m = new Patinete(matricula.getText().toString(),precio.getText().toString(),marca.getText().toString(),descripcion.getText().toString(),
//                    color.getText().toString(), ((int) bateria.getText().toString()),fecha.getText().toString(), estado.getText().toString(),carnet.getText().toString()
//                            ,plazasCoche.getText().toString(),puertasCoche.getText().toString());)
                }else if(tipo=="BICICLETA"){
                    lineaBici.setVisibility(View.VISIBLE);
                    lineaCoche1.setVisibility(View.GONE);
                    lineaCoche2.setVisibility(View.GONE);
                    lineaMoto1.setVisibility(View.GONE);
                    lineaMoto2.setVisibility(View.GONE);
                    lineaPatin1.setVisibility(View.GONE);
                    lineaPatin2.setVisibility(View.GONE);

//                    Bicicleta b = new Patinete(matricula.getText().toString(),precio.getText().toString(),marca.getText().toString(),descripcion.getText().toString(),
//                    color.getText().toString(), ((int) bateria.getText().toString()),fecha.getText().toString(), estado.getText().toString(),carnet.getText().toString()
//                            ,plazasCoche.getText().toString(),puertasCoche.getText().toString());)
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

    @Override
    public void doInBackground() {

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
}