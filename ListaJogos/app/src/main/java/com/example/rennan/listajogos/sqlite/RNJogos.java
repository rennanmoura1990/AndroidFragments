package com.example.rennan.listajogos.sqlite;

import android.content.Context;

import com.example.rennan.listajogos.model.Jogo;

import java.util.ArrayList;

/**
 * Created by Rennan on 01/12/2016.
 */

public class RNJogos {

    DAOJogos dao = new DAOJogos();

    public void inserirJogoFavorito(Context ctx, String title, String score, String publisher, String short_description, String thumb) {

        dao.inserirJogoFavorito(ctx, title, score, publisher, short_description, thumb);
    }

    public ArrayList<Jogo> listaJogosFavoritos(Context ctx) {
        return dao.listaJogosFavoritos(ctx);
    }


    public void excluirJogoFavorito(Context ctx, String nome) {
        dao.excluirJogo(ctx, nome);
    }

    public boolean verificaJogoFavoritado(Context cxt, String title) {
        String auxtitulo = dao.tituloJogo(cxt, title);
        if (auxtitulo != null)
            return true;
        else
            return false;
    }

    public String buscaIDJogo(Context ctx, String title) {
        return dao.buscaIDJogo(ctx, title);
    }


}
