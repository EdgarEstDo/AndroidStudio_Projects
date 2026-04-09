package com.example.mp0489_nf02_estrada_edgar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mp0489_nf02_estrada_edgar.modelos.Audio;

import java.util.ArrayList;

public class DatabaseSQL extends SQLiteOpenHelper {

    //Generación de datos para la BD
    public static final String dbName = "MyMusicStudio.db";
    public static final int dbVersion = 1;

    public DatabaseSQL(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    //Creación de la base de datos
    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {

        //Creación de la db de musica
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS music (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "title TEXT  UNIQUE NOT NULL," +
                        "url TEXT  UNIQUE NOT NULL" +
                        ")"
        );

    } //Fin de la creación

    //Actualización de la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    } //Fin de la actualización

    //Diseño del recorrido de la lista de las canciones que tenemos

    //Vista de todas las notas en StartActivity
    public ArrayList<Audio> getMusic() {

        //Creación de la lista de canciones
        ArrayList<Audio> musicList = new ArrayList<Audio>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM music WHERE title != ''", null);
        if (cur !=null){
            //Nos vamos a la última fila
            cur.moveToLast();
            if (cur.getCount() > 0){
                //A la primera fila
                cur.moveToFirst();
                //Mientras haya filas o no este en la primera
                while (!cur.isAfterLast()){
                    int id = cur.getInt(0);
                    String title = cur.getString(1);
                    String url = cur.getString(2);
                    musicList.add(new Audio(id, title, url));
                    cur.moveToNext();
                }

            }
            cur.close();
        }
        return musicList;
    } //Fin de la vista de todas las canciones

    //Diseño de la inserción de una canción para la parte de CrearActivity
    public void addMusic(Audio aux){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", aux.getTitle());
        values.put("url", aux.getUrl());
        //Inserto solo si no existe ya en la BD
        db.insertWithOnConflict("music", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();

    } //Fin de la inserción de una canción

    //Diseño de la eliminación de todas las canciones en la BD

    public void deleteMusic(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("music", null, null);
        db.close();
    } //Fin de la eliminación de todas las canciones

    //Creo este método para evitar que se añadan canciones repetidas en la BD desde el JSON
    public boolean exists(String title) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM music WHERE title = ?", new String[]{title});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }




} //Fin de la clase DatabaseSQL
