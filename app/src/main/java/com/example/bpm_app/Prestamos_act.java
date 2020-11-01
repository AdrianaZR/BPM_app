package com.example.bpm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import Clases.Creditos;

public class Prestamos_act extends AppCompatActivity {

    private Spinner spin1, spin2;
    private TextView text;
    private int saldofinal, deuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamos_act);

        spin1 = (Spinner)findViewById(R.id.spinner);
        spin2=(Spinner)findViewById(R.id.spinner2);
        text = (TextView)findViewById(R.id.textView7);

        ArrayList<String> listaClientes = (ArrayList<String>) getIntent().getSerializableExtra("listaClientes");
        ArrayList<String> listaCreditos = (ArrayList<String>) getIntent().getSerializableExtra("listaCreditos");

        ArrayAdapter<String> adapt = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaClientes);
        spin1.setAdapter(adapt);
        ArrayAdapter<String> adapt2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaCreditos);
        spin2.setAdapter(adapt2);
    }

    public void calcularSaldo(View v){
        calculo();
        text.setText("El saldo es: "+ saldofinal);
    }

    public void calcularDeuda(View v){
        calculo();
        text.setText("La cuota es: "+ deuda);
    }

    public void calculo(){
        Creditos tipo_credito = new Creditos();
        String cliente = spin1.getSelectedItem().toString();
        String credito = spin2.getSelectedItem().toString();
        int saldo=0, prestamo=0, cuotas=0;

        if(cliente.equals("Axel"))
            saldo = 750000;
        else if (cliente.equals("Roxana"))
            saldo = 900000;
        if(credito.equals("Crédito Hipotecario")) {
            prestamo = Integer.parseInt(tipo_credito.getHipotecario());
            cuotas= tipo_credito.getCuotaHipotecaria();
        }
        else if (credito.equals("Crédito Automotriz")) {
            prestamo = Integer.parseInt(tipo_credito.getAutomotriz());
            cuotas=tipo_credito.getCuotaAutomotriz();
        }
        saldofinal=saldo+prestamo;
        deuda=saldofinal/cuotas;
    }
}