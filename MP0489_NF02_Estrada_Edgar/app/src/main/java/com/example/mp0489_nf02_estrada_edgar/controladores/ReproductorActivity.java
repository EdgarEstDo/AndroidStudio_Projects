package com.example.mp0489_nf02_estrada_edgar.controladores;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mp0489_nf02_estrada_edgar.R;

import java.io.IOException;

public class ReproductorActivity extends AppCompatActivity {

    //Creación de variables
    private TextView activityName;
    private TextView title;
    private TextView url;
    private ImageButton play;
    private ImageButton pause;
    private ImageButton stop;
    private Button back;
    private MediaPlayer mp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reproductor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //Inicialización de variables
        activityName = (TextView) findViewById(R.id.textView_repro);
        title = (TextView) findViewById(R.id.textView3_repro);
        url = (TextView) findViewById(R.id.textView2_repro);
        play = (ImageButton) findViewById(R.id.imageButPlay__repro);
        pause = (ImageButton) findViewById(R.id.imageButPause__repro);
        stop = (ImageButton) findViewById(R.id.imageButStop__repro);
        back = (Button) findViewById(R.id.button__repro);


        //Recogida de datos del intent
        Intent receipt = getIntent();
        String aux = receipt.getStringExtra("title");
        title.setText("Title: " + aux);
        aux = receipt.getStringExtra("url");
        url.setText(aux);
        //Damos valor a aux para cargar el streaming
        aux = receipt.getStringExtra("url");
        chargeStreaming(aux);

        //Botones para controlar la canción
        play.setOnClickListener(v -> {
            playSong();
        });
        pause.setOnClickListener(v -> {
            pauseSong();
        });
        stop.setOnClickListener(v -> {
            stopSong();
        });

        //Botón para volver a la pantalla principal
        back.setOnClickListener(v -> {
            Intent changeScreen = new Intent(ReproductorActivity.this, StartActivity.class);
            finish();
            startActivity(changeScreen);

        });

    }



    //Métodos relacionados con Media Player para poder usar el reproductor
    private void chargeStreaming (String url){

        //Variables para traducción de lenguajes
        String Toast_Streaming = getString(R.string.Toast_Streaming);
        try {
            mp = new MediaPlayer();
            //Establecemos la fuente de datos como dice la documentación
            mp.setDataSource(url);
            //Ahora preparamos el streaming de forma asíncrona
            mp.prepareAsync();
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Toast.makeText(ReproductorActivity.this,Toast_Streaming, Toast.LENGTH_SHORT).show();
                }
            });

        } catch (IOException e) {
            Log.e("Error", "Something happened with Streaming - Error ");
        }
    }

    public void playSong(){
        //Iniciamos la canción
        if (mp != null) {
            mp.start();
        }
    }

    public void pauseSong(){
        //Pausamos la canción
        if (mp != null) {
            mp.pause();
        }
    }

    public void stopSong(){
        //Paramos la canción
        if (mp != null) {
            mp.stop();
            //Para poder evitar errores, reiniciamos el Streaming
            try{
                mp.prepareAsync();

            } catch (Exception e){
                //Sacamos el error que nos da Android Studio
                e.printStackTrace();
            }
        }
    }

    //Fin de los métodos relacionados con Media Player

    //Creación de método de uso de recursos de memoria
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (mp != null) {
            //Liberamos el espacio de memoria
            mp.release();
            //Ponemos a null para evitar errores en la referencia
            mp = null;
            Log.d("Media player release", "Media player released successfully");
        }
    }



}