package com.example.taserfan.actividades;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.taserfan.API.API;
import com.example.taserfan.API.Connector;
import com.example.taserfan.API.Result;
import com.example.taserfan.Model.AuthenticateData;
import com.example.taserfan.Model.Empleado;
import com.example.taserfan.R;
import com.example.taserfan.actividades.prefe.PreferenciasActivity;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;

public class LoginActivity extends BaseActivity implements CallInterface {
    private Result<Empleado> result;
    private EditText mail;
    private EditText pswd;
    private Button button;
    private AuthenticateData ad;
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        alert = new AlertDialog.Builder(LoginActivity.this);

        mail = findViewById(R.id.username);
        pswd = findViewById(R.id.password);
        button = findViewById(R.id.login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeCall(LoginActivity.this);
            }
        });
    }

    @Override
    public void doInBackground() {
        ad = new AuthenticateData(mail.getText().toString(),pswd.getText().toString());
        result = Connector.getConector().post(Empleado.class,ad, API.Routes.AUTHENTICATE);
    }

    @Override
    public void doInUI() {
        Intent intent;
        if (result instanceof Result.Success){
            LoggedInUserRepository.getInstance().login(((Result.Success<Empleado>) result).getData());
            intent = new Intent(getApplicationContext(), RVActivity.class);

            alert.setMessage("Se ha iniciado sesi??n correctamente")
                    .setTitle("INICIAR SESI??N")
                    .setPositiveButton("Aceptar", null);
            AlertDialog alertDialog = alert.create();
            alertDialog.show();

            startActivity(intent);
        }else {
            Result.Error resultado = (Result.Error) result;
            Tools.alertError(alert,resultado,"ERROR AL INICIAR SESI??N");
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