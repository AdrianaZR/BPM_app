package com.example.bpm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class Info_act extends AppCompatActivity {

    private VideoView videoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_act);

        videoview = (VideoView)findViewById(R.id.vid);
        String ruta = "android.resource://"+getPackageName() + "/" + R.raw.video; //Obtenemos la ruta
        Uri uri = Uri.parse(ruta);
        videoview.setVideoURI(uri);

        MediaController media = new MediaController(this);
        videoview.setMediaController(media);
    }

    public void Maps(View v){
        Intent i= new Intent(this, Maps_act.class);
        startActivity(i);
    }
}