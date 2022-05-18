package com.example.taserfan.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taserfan.API.Connector;
import com.example.taserfan.API.Result;
import com.example.taserfan.Model.AuthenticateData;
import com.example.taserfan.Model.Empleado;
import com.example.taserfan.R;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;

public class LoginActivity extends BaseActivity implements CallInterface {
    private Result<Empleado> result;
    private EditText mail;
    private EditText pswd;
    private Button button;
    private AuthenticateData ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mail = findViewById(R.id.username);
        pswd = findViewById(R.id.password);
        button = findViewById(R.id.login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad = new AuthenticateData(mail.getText().toString(),pswd.getText().toString());
                executeCall(LoginActivity.this);

            }
        });

    }

    @Override
    public void doInBackground() {//error te que estar per así
        result= Connector.getConector().post(Empleado.class,ad,"/auth");

    }

    @Override
    public void doInUI() {
        if (result instanceof Result.Success){
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),RVActivity.class);
            String email = mail.getText().toString();
            String password = pswd.getText().toString();
            ad= new AuthenticateData(email,password);
            startActivity(intent);
        }else {
            Result.Error resultado = (Result.Error) result;
            Toast.makeText(this, resultado.getError(), Toast.LENGTH_SHORT).show();
        }
    }
}