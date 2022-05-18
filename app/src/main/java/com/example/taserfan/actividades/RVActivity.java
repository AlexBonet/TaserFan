package com.example.taserfan.actividades;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.taserfan.R;
import com.example.taserfan.base.BaseActivity;
import com.example.taserfan.base.CallInterface;

import java.util.zip.Inflater;


public class RVActivity extends BaseActivity implements CallInterface, View.OnClickListener {

    private RecyclerView recyclerView;
    private RadioGroup rd;
    private RadioButton todos,coche,patin,bicis,motos;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_activity);

        recyclerView = findViewById(R.id.recyclerV);
        rd = findViewById(R.id.radioGroup);
        editText = findViewById(R.id.filtro);
    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void doInUI() {
        RVAdapter myRecyclerViewAdapter = new RVAdapter(this);
        myRecyclerViewAdapter.setOnClickListener(this);
        recyclerView.setAdapter(myRecyclerViewAdapter);

        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLinearLayoutManager);
    }

    @Override
    public void onClick(View view) {

    }
}