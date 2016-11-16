package com.example.rennan.listajogos.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Rennan on 29/10/2016.
 */

public class Jogo implements Serializable {

    /*@SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;*/

    public String title;
    public String score;
    public String publisher;
    public String short_description;
    public String thumb;
    public Plataforms plataforms;

}
