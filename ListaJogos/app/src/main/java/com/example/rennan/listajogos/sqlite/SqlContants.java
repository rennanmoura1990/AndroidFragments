package com.example.rennan.listajogos.sqlite;

/**
 * Created by Rennan on 27/11/2016.
 */

public interface SqlContants {

    String DBNAME = "jogosFavoritos";
    int DBVersion = 1;
    String ID = "id"; //só usado nos favoritos
    String TITLE = "title";
    String SCORE = "score";
    String PUBLISHER = "publisher";
    String SHORT_DESCRIPTION = "short_description";
    String THUMB = "thumb";
    String TEXT_NULL = "text null";

    StringBuilder SCRIPT_JOGOS = new StringBuilder("create table " + DBNAME)
            .append(" ("+ID+" integer primary key autoincrement,")
            .append(TITLE + " " + TEXT_NULL + ",")
            .append(SCORE + " " + TEXT_NULL + ",")
            .append(PUBLISHER + " " + TEXT_NULL + ",")
            .append(SHORT_DESCRIPTION + " " + TEXT_NULL + ",")
            .append(THUMB + " " + TEXT_NULL + ");");
}
