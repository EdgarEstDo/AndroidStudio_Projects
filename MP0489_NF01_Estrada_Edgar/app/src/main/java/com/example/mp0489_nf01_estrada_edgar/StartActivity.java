package com.example.mp0489_nf01_estrada_edgar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    //Creación de variables
    private TextView textoInicial;
    private TextView textoDesigner;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Inicialización de variables
        textoInicial = (TextView) findViewById(R.id.textView_start);
        textoDesigner = (TextView) findViewById(R.id.textView2_start);

        //Creación de la tarea
        TimerTask myTimerTask = new TimerTask() {
            @Override
            public void run() {

                Intent pasarPantalla = new Intent(StartActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        };

        //Creación del Timer para el cambio de pantalla - 3 segundos de espera
        Timer timer = new Timer();
        timer.schedule(myTimerTask, 3000);
    }
}