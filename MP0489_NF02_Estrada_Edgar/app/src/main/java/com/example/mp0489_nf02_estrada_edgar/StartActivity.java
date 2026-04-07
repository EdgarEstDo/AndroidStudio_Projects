package com.example.mp0489_nf02_estrada_edgar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StartActivity extends AppCompatActivity {

    //Creación de variables
    Menu menu;


    //Creación de variables para traducción de textos

    private String Title_Menu_Start = "Playlists Online";

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

        //Inicialización de variables

        //Creación del menú - Title
        setTitle(Title_Menu_Start);

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
            finish();
            startActivity(changeScreen);
            return true;
        } else if (id == R.id.exit_menu_start) {
            //Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show();
            finish();
            System.exit(0);
        }
    return super.onOptionsItemSelected(item);
    }

}