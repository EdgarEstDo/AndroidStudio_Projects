package com.example.mp0489_nf02_estrada_edgar.modelos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class modeloAudio {


    //Creación de método para recoger los datos del JSON de la nube

    public String getJsonFromUrl(String url){

        String json = "";
        String line = "";

        try {

            //Indicamos conexión a través de la URL
            URL direccion = new URL(url);

            //Establecemos la conexión
            HttpURLConnection conn = (HttpURLConnection) direccion.openConnection();
            //Conectamos con el servidor
            conn.connect();
            //Definimos los buffer de lectura
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            while ((line = br.readLine()) != null){
                //relleno la info de cada buffer con el string de salida del json
                json += line;
            }

            br.close();
            isr.close();
            is.close();
            conn.disconnect();


        } catch (MalformedURLException e){

            throw new RuntimeException(e);
        } catch (IOException e){

            throw new RuntimeException(e);
        }

        return json;
    }

    public ArrayList<Audio> getMusicFromJson(String json) {

        ArrayList<Audio> musicListJson = new ArrayList<Audio>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("canciones");
            //dbg.deleteMusic();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject aux = jsonArray.getJSONObject(i);
                String title = aux.getString("nombre");
                String url = aux.getString("url");
                musicListJson.add(new Audio(title, url));
            }


        } catch (JSONException eJson) {
            throw new RuntimeException(eJson);

        }
        return musicListJson;
    }

    public void addMusicJson(String title, String url){

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nombre", title);
            jsonObject.put("url", url);

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);


        } catch (JSONException eJson) {
            throw new RuntimeException(eJson);
        }
    }
}
