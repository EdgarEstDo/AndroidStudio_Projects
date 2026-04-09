package com.example.mp0489_nf02_estrada_edgar.controladores;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mp0489_nf02_estrada_edgar.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //Creación de variables
    private TextView InitialText;
    private TextView DesignerText;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Inicialización de variables
        InitialText = (TextView) findViewById(R.id.textView_main);
        DesignerText = (TextView) findViewById(R.id.textView2_main);

        //Creación de pantalla temporal inicial mediante timer aprendido en NF1
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent changeScreen = new Intent(MainActivity.this, StartActivity.class);
                finish();
                startActivity(changeScreen);

            }
        };

        //Creación del timer
        Timer timer = new Timer();
        timer.schedule(task, 3000);
    }


}