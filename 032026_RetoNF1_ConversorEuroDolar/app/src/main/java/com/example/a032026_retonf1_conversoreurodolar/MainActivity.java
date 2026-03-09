package com.example.a032026_retonf1_conversoreurodolar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Creación de variables asignadas al proyecto

    private TextView texto1;
    private TextView texto2;
    private TextView texto3;
    private TextView textoDatoFinal;
    private EditText dato;
    private Button boton1;
    private Button boton2;

    //Variables para modificaciones y datos internos de la app

    private double valorConversion = 0.00;


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

        //Inicializmos los textviews
        texto1 = findViewById(R.id.textView4_main);
        texto2 = findViewById(R.id.textView1_main);
        texto3 = findViewById(R.id.textView2_main);
        textoDatoFinal = findViewById(R.id.textView3_main);

        //Inicializmos los botones
        boton1 = findViewById(R.id.button3_main);
        boton2 = findViewById(R.id.button2_main);

        //Inicializmos el edittext
        dato = findViewById(R.id.editTextText_main);

        //Desarrollo de los métodos y acciones

        //Boton 1 - Acciones y mensajes
        boton1.setOnClickListener(new View.OnClickListener() {
            //Creo mensajes de contexto en caso de errores y aciertos
            @Override
            public void onClick(View view) {
                if(dato.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "No has introducido ningún valor.", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        valorConversion = Double.parseDouble(dato.getText().toString());
                        valorConversion = valorConversion * 1.21;
                        textoDatoFinal.setText(valorConversion + "$");
                        Toast.makeText(MainActivity.this, "El valor introducido es correcto.", Toast.LENGTH_SHORT).show();



                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Introduce un valor compatible.", Toast.LENGTH_SHORT).show();
                    }
                }
                // Borro el dato introducido en la caja editText
                dato.setText("");

            }


        });

        //Boton 2 - Acciones y mensajes
        boton2.setOnClickListener(new View.OnClickListener() {
            //Creo mensajes de contexto en caso de errores y aciertos
            @Override
            public void onClick(View view) {
                if(dato.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "No has introducido ningún valor.", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        valorConversion = Double.parseDouble(dato.getText().toString());
                        valorConversion = valorConversion * 0.79;
                        textoDatoFinal.setText(valorConversion + "€");
                        Toast.makeText(MainActivity.this, "El valor introducido es correcto.", Toast.LENGTH_SHORT).show();



                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Introduce un valor compatible.", Toast.LENGTH_SHORT).show();
                    }
                }
                // Borro el dato introducido en la caja editText
                dato.setText("");

            }


        });



    }
}