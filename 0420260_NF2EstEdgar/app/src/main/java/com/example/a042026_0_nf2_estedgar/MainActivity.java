package com.example.a042026_0_nf2_estedgar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    //Random para generar números aleatorios
    Random random = new Random();

    //Objeto para sonidos
    MediaPlayer mp;



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

        generarNuevaPregunta();
        //Estructura para comparar los botones con el resultado
        result1.setOnClickListener(view -> {
            if (result1.getText().equals(String.valueOf(resultado))) {
                preguntaCorrecta();
            } else {
                preguntaIncorrecta();
            }
        });
        result2.setOnClickListener(view -> {
            if (result2.getText().equals(String.valueOf(resultado))) {
                preguntaCorrecta();
            } else {
                preguntaIncorrecta();
            }
        });
        result3.setOnClickListener(view -> {
            if (result3.getText().equals(String.valueOf(resultado))) {
                preguntaCorrecta();
                } else {
                preguntaIncorrecta();
            }
        }); //Fin setOnClickListener



    } //Fin onCreate

    //Funcion pregunta correcta
    private void preguntaCorrecta() {
        Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
        //Uso de los métodos de sonido
        mp = MediaPlayer.create(this, R.raw.succesful);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        generarNuevaPregunta();
    }
    private void preguntaIncorrecta() {
        Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
        //Uso de los métodos de sonido
        mp = MediaPlayer.create(this, R.raw.wrong);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }

    //Función para generar una nueva pregunta cada vez que acertemos
    private void generarNuevaPregunta() {
        //Variables locales e inicialización
        int botonAleatorio = 0;

        //Randomización de valores de suma
        valor1 = random.nextInt(9);
        valor2 = random.nextInt(9);
        //Suma de valores para el resultado y comparación
        resultado = valor1 + valor2;
        //Mostrar en pantalla los números aleatorios de la suma
        suma_random.setText(String.valueOf(valor1) + " + " + String.valueOf(valor2));

        //Generación de números aleatorios para escoger los botones
        botonAleatorio = random.nextInt(3 - 1 + 1) + 1;

        //Bucle para modificar los botones con los números aleatorios
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
        } //Fin if
    } //Fin generarNuevaPregunta

    
    }
