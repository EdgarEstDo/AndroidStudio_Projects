package com.example.mp0489_nf01_estrada_edgar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BorrarNotasActivity extends AppCompatActivity {

    private Menu menu;
    private DataBaseSQL dbg;
    private Button BorrarTodo;
    private Button Volver;

    //Variables para traducción de textos
    private String Title_BorrarTodo;
    private String Toast_BorrarTodo;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_borrar_notas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Traducción de textos a partir de los strings.xml
        Title_BorrarTodo = getString(R.string.Title_BorrarTodo);
        Toast_BorrarTodo = getString(R.string.Toast_BorrarTodo);

        //Detalles asociados al menú
        setTitle(Title_BorrarTodo);

        //Conexión a la base de datos
        dbg = new DataBaseSQL(this);

        //Inicialización de variables
        BorrarTodo = (Button) findViewById(R.id.button_BorrarTodo);
        Volver = (Button) findViewById(R.id.button2_BorrarTodo);

        //Botón para borrar todas las notas
        BorrarTodo.setOnClickListener(v -> {
            Toast.makeText(this, Toast_BorrarTodo, Toast.LENGTH_SHORT).show();
            dbg.deleteAllNotas();
            Intent pasarPantalla = new Intent(BorrarNotasActivity.this, ListadoActivity.class);
            startActivity(pasarPantalla);
        });

        //Botón para volver a la pantalla de listado
        Volver.setOnClickListener(v -> {
            Intent pasarPantalla = new Intent(BorrarNotasActivity.this, ListadoActivity.class);
            startActivity(pasarPantalla);
        });



    } //Fin de On Create

    //Creación del menú y acciones a realizar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_borrar_notas, menu);
        return true;
    } //Fin del On Create Options Menu
}