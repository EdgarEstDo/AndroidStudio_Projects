package com.example.mp0489_nf02_estrada_edgar;

import android.annotation.SuppressLint;
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

public class StartActivity extends AppCompatActivity {

    //Creación de variables
    private Menu menu;
    private ListView list;
    private DatabaseSQL dbg;
    private ArrayList<Audio> musicList = new ArrayList<Audio>();
    private ArrayAdapter<Audio> adapter;

    //Creación de variables para traducción de textos
    private String Title_Menu_Start = "Playlists Online";


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

        //Creación del menú - Title
        setTitle(Title_Menu_Start);

        //Inicialización de variables


        //Conexión a la BD
        dbg = new DatabaseSQL(this);
        musicList = dbg.getMusic();

        //Impresión de canciones en el listado
        for (Audio n: musicList) {
            System.out.println(n.getId() + ". " + n.getTitle());
        }

        //Inicialización de listado de canciones
        list = (ListView) findViewById(R.id.ListView_start);

        //Creación del adaptador
        adapter = new ArrayAdapter<Audio>(this, android.R.layout.simple_list_item_1, musicList);
        list.setAdapter(adapter);

        //Creación del listener para saber si estoy pulsando una canción
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(StartActivity.this, "Chosen: " + musicList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                Intent changeScreen = new Intent(StartActivity.this, ReproductorActivity.class);
                changeScreen.putExtra("title", musicList.get(position).getTitle());
                changeScreen.putExtra("url", musicList.get(position).getUrl());
                startActivity(changeScreen);
            }
        });



    } //Fin de onCreate

    //Creación del menú - Acciones y botones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_start, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_menu_start) {
            Toast.makeText(this, "Add new song", Toast.LENGTH_SHORT).show();
            Intent changeScreen = new Intent(StartActivity.this, CrearActivity.class);
            startActivity(changeScreen);
            return true;
        } else if (id == R.id.delete_menu_start){
            //Creo este botón que tendré oculto para realizar pruebas y borrar las canciones
            //de la DB en caso de que lo necesite. Por defecto estará en false en el xml.
            Toast.makeText(this, "Delete all songs", Toast.LENGTH_SHORT).show();
            dbg.deleteMusic();
            Intent changeScreen = new Intent(StartActivity.this, StartActivity.class);
            finish();
            startActivity(changeScreen);

        } else if (id == R.id.exit_menu_start) {
            //Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show();
            finish();
            //System.exit(0); -- En mi caso, el system exit no funciona
            //En documentación de Android, usan finishAffinity() y con este si que surte efecto
            finishAffinity();
        }
    return super.onOptionsItemSelected(item);
    }

}