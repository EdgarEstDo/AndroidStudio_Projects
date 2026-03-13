package com.example.mp0489_nf01_estrada_edgar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VerNotaActivity extends AppCompatActivity {

    private Menu menu;
    private Nota nota;
    private DataBaseSQL dbg;
    private TextView titulo;
    private TextView contenido;
    private Button Volver;
    private Button BorrarNota;

    //Variables para traducción de textos
    private String Title_VerNota;
    private String Toast_BorrarNota;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_nota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Traducción de textos a partir de los strings.xml
        Title_VerNota = getString(R.string.Title_VerNota);
        Toast_BorrarNota = getString(R.string.Toast_BorrarNota);

        //Detalles asociados al menú
        setTitle(Title_VerNota);

        //Conexión a la base de datos
        dbg = new DataBaseSQL(this);

        //Inicialización de variables
        titulo = (TextView) findViewById(R.id.textView2_Ver);
        contenido = (TextView) findViewById(R.id.textView3_Ver);
        Volver = (Button) findViewById(R.id.button_Ver);
        BorrarNota = (Button) findViewById(R.id.button2_Ver);


        //Visualizar la nota y recojo los datos de los paquetes enviados por la pantalla de listado
        Intent intent = getIntent();
        String tituloNota = intent.getStringExtra("titulo");

        //Muestro la nota
        nota = dbg.getNota(tituloNota);
        titulo.setText(nota.getTitulo());
        contenido.setText(nota.getContenido());



        //Botón para volver a la pantalla de listado
        Volver.setOnClickListener(v -> {
            Intent pasarPantalla = new Intent(VerNotaActivity.this, ListadoActivity.class);
            startActivity(pasarPantalla);
        });

        //Botón para borrar la nota
        BorrarNota.setOnClickListener(v -> {
            dbg.deleteNotaId(nota.getId());
            Toast.makeText(this, Toast_BorrarNota, Toast.LENGTH_SHORT).show();
            Intent pasarPantalla = new Intent(VerNotaActivity.this, ListadoActivity.class);
            startActivity(pasarPantalla);
        });

        } //Fin del On Create

    //Creación del menú y acciones a realizar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ver_nota, menu);
        return true;

    } //Fin del On Create Options Menu

}