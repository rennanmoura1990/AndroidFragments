package com.example.rennan.listajogos;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.rennan.listajogos.http.JogosParser;
import com.example.rennan.listajogos.model.Jogo;

import java.io.IOException;
import java.util.List;

/**
 * Created by Rennan on 02/11/2016.
 */

public class JogosSearchTask extends AsyncTaskLoader<List<Jogo>> {

    List<Jogo> mJogos;
    String mQuery;

    public JogosSearchTask(Context context, String query) {
        super(context);
        mQuery = query;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mJogos == null) {
            forceLoad();
        } else {
            deliverResult(mJogos);
        }
    }

    @Override
    public List<Jogo> loadInBackground() {
        try {
            mJogos = JogosParser.listajogos(mQuery);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mJogos;
    }
}
