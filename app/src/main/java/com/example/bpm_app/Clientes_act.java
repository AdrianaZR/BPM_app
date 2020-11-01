package com.example.bpm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Clases.AdminSQLiteOpenHelper;

public class Clientes_act extends AppCompatActivity {

    private EditText cod, nom, salario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_act);

        cod = (EditText)findViewById(R.id.edcodigo);
        nom = (EditText)findViewById(R.id.ednombre);
        salario = (EditText)findViewById(R.id.edsalario);
    }

    public void guardarCliente(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "fichero", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if(!cod.getText().toString().isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", cod.getText().toString());
            registro.put("nombre", nom.getText().toString());
            registro.put("salario", salario.getText().toString());

            db.insert("clientes", null, registro);
            db.close();

            Toast.makeText(this, "Se ha guardado un cliente", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Debe ingresar un cliente", Toast.LENGTH_LONG).show();
        }
        cod.setText("");
        nom.setText("");
        salario.setText("");
    }

    public void mostrarCliente(View v){
        AdminSQLiteOpenHelper admin =new AdminSQLiteOpenHelper(this, "fichero", null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = cod.getText().toString();

        if(!codigo.isEmpty())
        {
            Cursor fila = db.rawQuery("SELECT nombre, salario FROM clientes WHERE codigo ="+ codigo,null);

            if (fila.moveToFirst())
            {
                nom.setText(fila.getString(0));
                salario.setText(fila.getString(1));
            }
            else
            {
                Toast.makeText(this,"No hay clientes con el codigo asociado", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this,"ingresar el codigo del cliente",Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarCliente(View v){
        AdminSQLiteOpenHelper admin =new AdminSQLiteOpenHelper(this, "fichero", null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = cod.getText().toString();
        db.delete("clientes","codigo ="+codigo,null);

        Toast.makeText(this,"Se ha eliminado al cliente",Toast.LENGTH_LONG).show();
        cod.setText("");
        nom.setText("");
        salario.setText("");
    }

    public void actualizarCliente(View v){
        AdminSQLiteOpenHelper admin =new AdminSQLiteOpenHelper(this, "fichero", null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String codigo = cod.getText().toString();

        ContentValues cont = new ContentValues();
        cont.put("codigo",cod.getText().toString());
        cont.put("nombre",nom.getText().toString());
        cont.put("salario",salario.getText().toString());

        if (!codigo.isEmpty())
        {
            db.update("clientes",cont,"codigo="+codigo,null);
            Toast.makeText(this,"Se ha actualizado al cliente",Toast.LENGTH_LONG).show();
        }
        cod.setText("");
        nom.setText("");
        salario.setText("");
    }
}