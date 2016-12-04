package com.example.rennan.listajogos.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rennan.listajogos.model.Jogo;

import java.util.ArrayList;

/**
 * Created by Rennan on 27/11/2016.
 */

public class DAOJogos {

    /* Insere 1 jogo favorito no Banco */
    public void inserirJogoFavorito(Context cxt, String title, String score, String publisher, String short_description, String thumb) {
        SqliteHelper sql = SqliteHelper.getInstance(cxt);
        SQLiteDatabase db = sql.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SqlContants.TITLE, title);
        values.put(SqlContants.SCORE, score);
        values.put(SqlContants.PUBLISHER, publisher);
        values.put(SqlContants.SHORT_DESCRIPTION, short_description);
        values.put(SqlContants.THUMB, thumb);

        db.insert(SqlContants.DBNAME, null, values);

        db.close();
    }

    /* Retorna 1 lista de Jogos */
    public ArrayList<Jogo> listaJogosFavoritos(Context ctx) {

        ArrayList<Jogo> auxJogos = new ArrayList<Jogo>();
        Cursor cursor;

        SqliteHelper sql = SqliteHelper.getInstance(ctx);
        SQLiteDatabase db = sql.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM " + SqlContants.DBNAME, null);

        while (cursor.moveToNext()) {
            Jogo jogo = new Jogo();
            jogo.id = cursor.getString(cursor.getColumnIndex(SqlContants.ID));
            jogo.title = cursor.getString(cursor.getColumnIndex(SqlContants.TITLE));
            jogo.score = cursor.getString(cursor.getColumnIndex(SqlContants.SCORE));
            jogo.publisher = cursor.getString(cursor.getColumnIndex(SqlContants.PUBLISHER));
            jogo.short_description = cursor.getString(cursor.getColumnIndex(SqlContants.SHORT_DESCRIPTION));
            jogo.thumb = cursor.getString(cursor.getColumnIndex(SqlContants.THUMB));

            auxJogos.add(jogo);
        }
        db.close();
        return auxJogos;


    }

    /* Retorna 1 Jogo pelo Nome */
    public String tituloJogo(Context cxt, String title) {

        Cursor cursor;
        String auxNome = null;

        SqliteHelper sql = SqliteHelper.getInstance(cxt);
        SQLiteDatabase db = sql.getReadableDatabase();

        cursor = db.query(SqlContants.DBNAME, new String[]{SqlContants.TITLE}, "title=?", new String[]{title}, null, null, null);

        while (cursor.moveToNext()) {
            auxNome = cursor.getString(cursor.getColumnIndex(SqlContants.TITLE));
        }
        db.close();
        return auxNome;

    }

    public void excluirJogo(Context ctx,String id){

        SqliteHelper sql = SqliteHelper.getInstance(ctx);
        SQLiteDatabase db = sql.getWritableDatabase();

        db.delete(SqlContants.DBNAME,"id = ?",new String[]{id});

        db.close();


    }

    /* Retorna o id atual do jogo (Evita Bug do FAB travar)*/
    public String buscaIDJogo(Context cxt,String title){

        Cursor cursor;
        String auxID = null;

        SqliteHelper sql = SqliteHelper.getInstance(cxt);
        SQLiteDatabase db = sql.getReadableDatabase();

        cursor = db.query(SqlContants.DBNAME, new String[]{SqlContants.ID}, "title=?", new String[]{title}, null, null, null);

        while (cursor.moveToNext()) {
            auxID = cursor.getString(cursor.getColumnIndex(SqlContants.ID));
        }
        db.close();
        return auxID;
    }

}
