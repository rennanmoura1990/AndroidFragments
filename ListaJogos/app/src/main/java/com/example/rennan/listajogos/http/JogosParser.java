package com.example.rennan.listajogos.http;

import android.util.Log;

import com.example.rennan.listajogos.model.Jogo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Rennan on 29/10/2016.
 */


public class JogosParser {


    /*public static final String URL_SEARCH = "https://igdbcom-internet-game-database-v1.p.mashape.com/games/?fields=id%2Cname&limit=25&offset=0&order=release_dates.date%3Adesc&search=";*/
    public static final String URL_SEARCH = "https://videogamesrating.p.mashape.com/get.php?count=20&game=";

    public static List<Jogo> listajogos(String nome) throws IOException {

        OkHttpClient client = new OkHttpClient(); //faz conexão

        Request request = new Request.Builder() //requer a url
                .url(URL_SEARCH + nome)
                .header("X-Mashape-Key", "iAZUVAwgJWmsh9LvJpME7qnSdKiPp1P9T5bjsnqGpMYY4pi7ZN")
                .header("Accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        List<Jogo> jogos = null;
        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
            String json = response.body().string();

            Gson gson = new Gson();
            Type type = new TypeToken<List<Jogo>>() {
            }.getType();
            jogos = gson.fromJson(json, type);

            /*GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Jogo.class,new JogoDeserializer());
            Gson gson = gsonBuilder.create();
            Type type = new TypeToken<List<Jogo>>() {
            }.getType();*/


        }
        return jogos;

    }
}
