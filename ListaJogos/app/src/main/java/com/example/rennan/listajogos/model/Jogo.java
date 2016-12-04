package com.example.rennan.listajogos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.internal.platform.Platform;

/**
 * Created by Rennan on 29/10/2016.
 */

public class Jogo implements Parcelable {

    public String id;
    public String title;
    public String score;
    public String publisher;
    public String short_description;
    public String thumb;

    public Jogo(){}

    protected Jogo(Parcel in) {
        id = in.readString();
        title = in.readString();
        score = in.readString();
        publisher = in.readString();
        short_description = in.readString();
        thumb = in.readString();
    }

    public static final Creator<Jogo> CREATOR = new Creator<Jogo>() {
        @Override
        public Jogo createFromParcel(Parcel in) {
            return new Jogo(in);
        }

        @Override
        public Jogo[] newArray(int size) {
            return new Jogo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(score);
        parcel.writeString(publisher);
        parcel.writeString(short_description);
        parcel.writeString(thumb);
    }

}
