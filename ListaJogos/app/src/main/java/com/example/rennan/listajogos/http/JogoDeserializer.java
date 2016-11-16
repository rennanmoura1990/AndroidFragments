package com.example.rennan.listajogos.http;

import com.example.rennan.listajogos.model.Jogo;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rennan on 15/11/2016.
 */

public class JogoDeserializer implements JsonDeserializer<Jogo> {
    @Override
    public Jogo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        final JsonObject jsonObject = json.getAsJsonObject();

        final JsonElement Jsonscore = jsonObject.get("score");
        final String score = Jsonscore.getAsString();

        final JsonElement Jsonpublisher = jsonObject.get("publisher");
        final String publisher = Jsonpublisher.getAsString();

        final JsonElement Jsonshort_description = jsonObject.get("short_description");
        final String short_description = Jsonshort_description.getAsString();

        final JsonElement Jsonthumb = jsonObject.get("thumb");
        final String thumb = Jsonthumb.getAsString();

        final JsonObject JsonPlataforms = (JsonObject) jsonObject.get("plataforms");

        /*final List<String> plataforms = (List<String>)JsonPlataforms.getAsJsonObject();


        for(int i=0;i < plataforms.length;i++){
            final JsonElement JsonPlataform = JsonPlataforms.get(i);
            plataforms[i] = JsonPlataform.getAsString();
        }

        final Jogo jogo = new Jogo();
        jogo.score = score;
        jogo.publisher = publisher;
        jogo.short_description = short_description;
        jogo.thumb = thumb;
        jogo.plataforms = plataforms;*/

        /*return jogo;*/

        return null;

    }
}
