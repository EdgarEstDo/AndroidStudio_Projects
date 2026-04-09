package com.example.mp0489_nf02_estrada_edgar.controladores;

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

import com.example.mp0489_nf02_estrada_edgar.DatabaseSQL;
import com.example.mp0489_nf02_estrada_edgar.R;
import com.example.mp0489_nf02_estrada_edgar.modelos.Audio;
import com.example.mp0489_nf02_estrada_edgar.modelos.modeloAudio;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    //Creación de variables
    private Menu menu;
    private ListView list;
    private ArrayList<Audio> musicList = new ArrayList<Audio>();
    private ArrayAdapter<Audio> adapter;

    private DatabaseSQL dbg;

    //Creación de variables para manejar el JSON
    private String urlApiJson = "https://raw.githubusercontent.com/EdgarEstDo/AndroidStudio_Projects/refs/heads/main/MP0489_NF02_Estrada_Edgar/API_canciones/canciones.json";
    private String jsonOnline = "";
    private ArrayList<Audio> listaCanciones;
    modeloAudio ma = new modeloAudio();

    //Creación de hilos en el sistema
    Thread onlineRead;

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

        //Conexión a la BD
        dbg = new DatabaseSQL(this);

        // Creación del adaptador con la lista vacía por ahora
        list = (ListView) findViewById(R.id.ListView_start);
        // Creamos el adaptador con la lista vacía por ahora
        adapter = new ArrayAdapter<Audio>(this, android.R.layout.simple_list_item_1, musicList);
        list.setAdapter(adapter);

        // Configuro el listener para la lista de canciones
        list.setOnItemClickListener((parent, view, position, id) -> {
            Audio aux = musicList.get(position);
            Intent changeScreen = new Intent(StartActivity.this, ReproductorActivity.class);
            changeScreen.putExtra("title", aux.getTitle());
            changeScreen.putExtra("url", aux.getUrl());
            startActivity(changeScreen);
        });

        onlineRead = new Thread(new Runnable() {
            @Override
            public void run() {
                jsonOnline = ma.getJsonFromUrl(urlApiJson);
                //Sacamos el JSON en consola
                System.out.println(jsonOnline);
                //Podemos sacar el Toast en caso de que queramos ver el JSON
                //Toast.makeText(StartActivity.this, jsonOnline, Toast.LENGTH_SHORT).show();
                ArrayList<Audio> listaDeLaNube = ma.getMusicFromJson(jsonOnline);

                //Limpio la BD antes de añadir los nuevos datos
                //dbg.deleteMusic();

                //Recorro la lista de la nube y la inserto en la BD
                for (int i = 0; i < listaDeLaNube.size(); i++) {
                    Audio a = listaDeLaNube.get(i);
                    // Solo la añadimos si NO existe ya en la base de datos
                    if (!dbg.exists(a.getTitle())) {
                        dbg.addMusic(a);
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        // Llamamos al método para actualizar la BD
                        actualizarListaDesdeBD();

                        //Creación del listener para la lista y su acción
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Audio aux = musicList.get(position);
                                Intent changeScreen = new Intent(StartActivity.this, ReproductorActivity.class);
                                changeScreen.putExtra("title", aux.getTitle());
                                changeScreen.putExtra("url", aux.getUrl());
                                startActivity(changeScreen);
                            }
                        });
                    }

                });
            }
        });
        onlineRead.start();



    } //Fin de onCreate

    // He cambiado el nombre para que no se confunda con el ciclo de vida
    private void actualizarListaDesdeBD() {
        ArrayList<Audio> listaActualizada = dbg.getMusic();
        musicList.clear();
        musicList.addAll(listaActualizada);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Cada vez que vuelvas de "CrearActivity", esto actualizará la lista
        actualizarListaDesdeBD();
    }


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

        } else if (id == R.id.delete_menu_start) {
            Toast.makeText(this, "Delete all files", Toast.LENGTH_SHORT).show();
            dbg.deleteMusic();
            actualizarListaDesdeBD();
            return true;
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