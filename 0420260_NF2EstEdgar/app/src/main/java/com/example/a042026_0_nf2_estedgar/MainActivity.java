package com.example.a042026_0_nf2_estedgar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Variables globales
    private TextView suma_random;
    private Button result1;
    private Button result2;
    private Button result3;
    private int resultado;
    private int valor1;
    private int valor2;
    private int botonAleatorio;

    //Random para generar números aleatorios
    Random random = new Random();



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
        suma_random =  findViewById(R.id.textView_main);
        result1 =  findViewById(R.id.button_main);
        result2 =  findViewById(R.id.button2_main);
        result3 =  findViewById(R.id.button3_main);
        resultado = 0;
        valor1 = 0;
        valor2 = 0;

        //Generación de números aleatorios para escoger los botones
        botonAleatorio = random.nextInt(3 - 1 + 1) + 1;

        if(botonAleatorio == 1){
            result1.setText(String.valueOf(resultado));
            result2.setText(String.valueOf(resultado + 1));
            result3.setText(String.valueOf(resultado - 1));
        }else if(botonAleatorio == 2){
            result2.setText(String.valueOf(resultado));
            result1.setText(String.valueOf(resultado + 1));
            result3.setText(String.valueOf(resultado - 1));
        }else if(botonAleatorio == 3){
            result3.setText(String.valueOf(resultado));
            result1.setText(String.valueOf(resultado + 1));
            result2.setText(String.valueOf(resultado - 1));
        }

        }








    }
