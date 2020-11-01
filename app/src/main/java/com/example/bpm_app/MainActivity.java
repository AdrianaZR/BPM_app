package com.example.bpm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pb;
    private Button boton;
    private EditText nombre, contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = (ProgressBar)findViewById(R.id.progressBar);
        boton = (Button)findViewById(R.id.button);
        nombre = (EditText)findViewById(R.id.texto1);
        contrasena=(EditText)findViewById(R.id.texto2);

        pb.setVisibility(View.INVISIBLE);

        boton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View view){
                new Task().execute();

            }
        });
    }

    class Task extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            for(int i=0; i<=10;i++){
                try {
                    Thread.sleep(100);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            String user = nombre.getText().toString();
            String password = contrasena.getText().toString();
            if(user.equalsIgnoreCase("android") &&  password.equals("123")) {
                Intent i = new Intent(getBaseContext(), Home_act.class);
                startActivity(i);
            }

            else{
                Toast.makeText(getApplicationContext(), "Usuario y/o contraseña erróneos", Toast.LENGTH_SHORT).show();
            }
        }
    }
}