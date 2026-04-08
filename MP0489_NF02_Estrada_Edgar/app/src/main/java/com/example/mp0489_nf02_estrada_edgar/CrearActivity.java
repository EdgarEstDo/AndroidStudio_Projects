package com.example.mp0489_nf02_estrada_edgar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CrearActivity extends AppCompatActivity {


    //Creación de variables
    private EditText title;
    private EditText url;
    private TextView topTitle;
    private TextView topUrl;

    private Button addSong;
    private DatabaseSQL dbg;
    private Audio aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //Conexión a la BD
        dbg = new DatabaseSQL(this);

        //Inicialización de variables
        title = (EditText) findViewById(R.id.editTextText_crear);
        url = (EditText) findViewById(R.id.editTextText2_crear);
        topTitle = (TextView) findViewById(R.id.textView2_crear);
        topUrl = (TextView) findViewById(R.id.textView3_crear);
        addSong = (Button) findViewById(R.id.button_crear);

        //Botón para añadir nueva canción

        addSong.setOnClickListener(v -> {

            if (title.getText().toString().isEmpty() || url.getText().toString().isEmpty()) {
                Toast.makeText(this, "You must fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                aux = new Audio("","");
                aux.setTitle(title.getText().toString());
                aux.setUrl(url.getText().toString());
                dbg.addMusic(aux);
                Toast.makeText(this, "Song added", Toast.LENGTH_SHORT).show();
                Intent changeScreen = new Intent(CrearActivity.this, StartActivity.class);
                finish();
                startActivity(changeScreen);
            }

        });


    } //Fin de onCreate
}