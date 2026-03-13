package com.example.mp0489_nf01_estrada_edgar;

import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CrearNotaActivity extends AppCompatActivity {
    //Creación de variables
    private Menu menu;
    private EditText titulo;
    private EditText contenido;
    private TextView MensajeCrearNota;
    private Button Volver;
    private Button CrearNota;
    private DataBaseSQL dbg;
    private Nota nota;

    //Variables para traducción de textos
    private String Title_CrearNota;
    private String Toast_CrearNota;
    private String Toast_NotaVacia;



    //Método On Create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_nota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Traducción de textos a partir de los strings.xml
        Title_CrearNota = getString(R.string.Title_CrearNota);
        Toast_CrearNota = getString(R.string.Toast_CrearNota);
        Toast_NotaVacia = getString(R.string.Toast_NotaVacia);

        //Detalles asociados al menú
        setTitle(Title_CrearNota);

        //Conexión a la base de datos
        dbg = new DataBaseSQL(this);

        //Inicialización de variables
        titulo = (EditText) findViewById(R.id.editTextText2_Crear);
        contenido = (EditText) findViewById(R.id.editTextText3_Crear);
        MensajeCrearNota = (TextView) findViewById(R.id.textView2_Crear);
        Volver = (Button) findViewById(R.id.button_Crear);
        CrearNota = (Button) findViewById(R.id.button2_Crear);

        //Botón para volver a la pantalla de listado
        Volver.setOnClickListener(v -> {
            Intent pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
            startActivity(pasarPantalla);
        });

        //Botón para crear la nota
        CrearNota.setOnClickListener(v -> {

            if (titulo.getText().toString().isEmpty() || contenido.getText().toString().isEmpty()) {
                Toast.makeText(this, Toast_NotaVacia, Toast.LENGTH_SHORT).show();
            } else {
                nota = new Nota("","");
                nota.setTitulo(titulo.getText().toString());
                nota.setContenido(contenido.getText().toString());
                dbg.insertNota(nota);
                Toast.makeText(this, Toast_CrearNota, Toast.LENGTH_SHORT).show();
                Intent pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
                startActivity(pasarPantalla);
            }
        });


    } //Fin del On Create



    //Creación del menú y acciones a realizar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_crear_nota, menu);
        return true;

    } //Fin del On Create Options Menu


}