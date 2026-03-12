package com.example.mp0489_nf01_estrada_edgar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseSQL extends SQLiteOpenHelper {

    //Datos específicos para el gestor
    public static final String nombreBD = "notitas.db";
    public static final int version = 1;

    public DataBaseSQL(@Nullable Context context) {
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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Métodos para obtener las notas

    //Obtener todas las notas
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
                    int id = cur.getInt(0);
                    String titulo = cur.getString(1);
                    String contenido = cur.getString(2);
                    notas.add(new Nota(id, titulo, contenido));
                    cur.moveToNext();
                }
            }
            cur.close();
        }
        return notas;
    }

    //Ver una nota
    public Nota getNota(String titulo) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM notas WHERE titulo=?", new String[]{titulo});
        if (cur != null) {
            cur.moveToFirst();
            int id = cur.getInt(0);
            String tituloNota = cur.getString(1);
            String contenido = cur.getString(2);
            cur.close();
            return new Nota(id, tituloNota, contenido);
        } else {
            return null;
        }
    }


    //Insertar una nota
    public void insertNota(Nota n) {
        SQLiteDatabase db = getWritableDatabase();

        //Vamos a realizar un método que evite la inyección de SQL
        ContentValues values = new ContentValues();
        values.put("titulo", n.getTitulo());
        values.put("contenido", n.getContenido());
        db.insert("notas", null, values);

    }

    //Borrar una nota
    public void deleteNota(String titulo) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("notas", "titulo=?", new String[]{titulo});
    }
    //Borrar una nota pero por su id
    public void deleteNotaId(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("notas", "id=?", new String[]{String.valueOf(id)});
    }
}
