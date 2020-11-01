package com.example.bpm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Seguridad_act extends AppCompatActivity {

    private EditText edit;
    private TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguridad_act);

        edit = (EditText)findViewById(R.id.password);
        texto = (TextView)findViewById(R.id.textView);
    }

    private SecretKeySpec generateKey(String password)throws Exception{
        MessageDigest sha= MessageDigest.getInstance("SHA-256"); //Firma HMAC para verificar integridad de los datos
        byte[] key = password.getBytes("UTF-8");//Estandar de password bajo UF8
        key=sha.digest(key);
        SecretKeySpec secretkey = new SecretKeySpec(key, "AES");

        return secretkey;
    }

    public String encriptar(String datos, String password)throws Exception{

        if(!edit.getText().toString().isEmpty()){
            SecretKeySpec secretKey = generateKey(password);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] datosEncriptadosBytes = cipher.doFinal(datos.getBytes());
            String datosEncriptadosString = Base64.encodeToString(datosEncriptadosBytes, Base64.DEFAULT);
            return datosEncriptadosString;

        }
        else
            Toast.makeText(this,"Debe ingresar una clave",Toast.LENGTH_LONG).show();
        return null;
    }

    public void Encriptar(View v)
    {
        try{
            String mensaje = encriptar(edit.getText().toString(), texto.getText().toString());
            texto.setText(mensaje);
        }catch (Exception e)  {
            e.printStackTrace();
        }

    }


}