package com.example.rennan.listajogos;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.rennan.listajogos.http.JogosParser;
import com.example.rennan.listajogos.model.Jogo;

import java.io.IOException;
import java.util.List;

/**
 * Created by Rennan on 13/11/2016.
 */

public class JogoDetailTask extends AsyncTaskLoader<Jogo> {

    Jogo mJogo;

    public JogoDetailTask(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mJogo == null) {
            forceLoad();
        } else {
            deliverResult(mJogo);
        }
    }

    @Override
    public Jogo loadInBackground() {

        return mJogo;
    }

}
