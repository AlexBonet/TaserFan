package com.example.taserfan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taserfan.API.Connector;
import com.example.taserfan.API.Result;
import com.example.taserfan.Model.AuthenticateData;
import com.example.taserfan.Model.Empleado;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;

public class LoginActivity extends AppCompatActivity implements CallInterface {
    Result<Empleado> result;
    private EditText mail;
    private EditText pswd;
    private Button button;
    private String email;
    private String password;

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
                email = mail.getText().toString();
                password = pswd.getText().toString();
            }
        });


    }

    @Override
    public void doInBackground() {
        result= Connector.getConector().post(Empleado.class,new AuthenticateData(email,password),"/auth");
        Toast.makeText(LoginActivity.this, result.toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void doInUI() {
        if (result instanceof Result.Success){

        }else {

        }
    }
}