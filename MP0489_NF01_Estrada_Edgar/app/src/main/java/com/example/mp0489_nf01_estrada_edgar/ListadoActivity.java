package com.example.mp0489_nf01_estrada_edgar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    //Creación de variables
    private Menu menu;
    private ListView listaNotas;
    private DataBaseSQL dbg;
    private ArrayList<Nota> notas = new ArrayList<Nota>();
    private ArrayAdapter<Nota> adaptador;

    //Variables para traducción de textos
    private String Title_Listado;
    private String Toast_MenuBorrar;
    private String Toast_MenuCrear;
    private String Toast_EscogerNota;


    //Método On Create


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Traducción de textos a partir de los strings.xml
        Title_Listado = getString(R.string.Title_Listado);
        Toast_MenuBorrar = getString(R.string.Toast_MenuBorrar);
        Toast_MenuCrear = getString(R.string.Toast_MenuCrear);
        Toast_EscogerNota = getString(R.string.Toast_EscogerNota);

        //Detalles asociados al menú
        setTitle(Title_Listado);

        //Conexión a la base de datos
        dbg = new DataBaseSQL(this);
        notas = dbg.getNotas();

        //Impresión de las notas por consola
        for (Nota n : notas) {
            System.out.println(n.getTitulo());
        }

        //Inicialización de la lista de notas
        listaNotas = (ListView) findViewById(R.id.ListView_listado);


        //Diseño del adaptador para recoger los datos de las notas
        adaptador = new ArrayAdapter<Nota>(ListadoActivity.this, android.R.layout.simple_list_item_1, notas);
        listaNotas.setAdapter(adaptador);

        //Creación de listener con ToastMakes para saber si estoy pulsando sobre una nota
        listaNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Toast.makeText(ListadoActivity.this, Toast_EscogerNota, Toast.LENGTH_SHORT).show();

                //Paso a la pantalla de ver nota y le doy los datos a través de los paquetes que envío por Intent
                Intent pasarPantalla = new Intent(ListadoActivity.this, VerNotaActivity.class);
                pasarPantalla.putExtra("titulo", notas.get(position).getTitulo());
                startActivity(pasarPantalla);
            }
        });

    } //Fin del On Create


    //Creación del menú y acciones a realizar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_listado, menu);
        return true;
    } //Fin del On Create Options Menu


    //Creación de los botones del menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_listado_add) {
            //Diseño del botón de añadir notas
            Toast.makeText(this, Toast_MenuCrear, Toast.LENGTH_SHORT).show();
            Intent pasarPantalla = new Intent(ListadoActivity.this, CrearNotaActivity.class);
            finish();
            startActivity(pasarPantalla);
            return true;
        } else if (id == R.id.menu_listado_delete) {
            //Diseño del botón de borrar notas
            Toast.makeText(this, Toast_MenuBorrar, Toast.LENGTH_SHORT).show();
            Intent pasarPantalla = new Intent(ListadoActivity.this, BorrarNotasActivity.class);
            startActivity(pasarPantalla);
            return true;
        }
        return super.onOptionsItemSelected(item);
    
    } //Fin del On Options Item Selected


}