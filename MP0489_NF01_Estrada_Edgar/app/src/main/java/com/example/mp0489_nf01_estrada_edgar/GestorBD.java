package com.example.mp0489_nf01_estrada_edgar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GestorBD extends SQLiteOpenHelper {

    //Constructor de la clase

    //Datos específicos para el gestor
    public static final String nombreBD = "notitas.db";
    public static final int version = 1;

    public GestorBD(@Nullable Context context) {
        super(context, nombreBD, null, version);
    }

    //Métodos de la clase
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creación de la tabla notas
        db.execSQL(
                "CREATE TABLE notas (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "titulo TEXT," +
                        "contenido TEXT" +
                        ")"

        );
        db.execSQL(
                "INSERT INTO notas (titulo, contenido) VALUES ('Nota 1', 'Contenido de la nota 1')"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Métodos para obtener las notas
    public ArrayList<Nota> getNotas() {
        ArrayList<Nota> notas = new ArrayList<Nota>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM notas", null);
        if (cur != null) {
            //Me voy a la última fila
            cur.moveToLast();
            //Si tengo filas
            if (cur.getCount() > 0) {
                //Me muevo hacia atrás
                cur.moveToFirst();
                //Mientras no esté en la primera fila, me muevo hacia atrás
                while (!cur.isAfterLast()) {
                    cur.moveToNext();
                }
            }
            cur.close();
        }
        return notas;
    }



}
