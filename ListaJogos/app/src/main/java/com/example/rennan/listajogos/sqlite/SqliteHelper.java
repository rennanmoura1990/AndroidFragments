package com.example.rennan.listajogos.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rennan on 27/11/2016.
 */

//Cria o banco e atualiza os dados do banco de dados,como adicionar novas tabelas

public class SqliteHelper extends SQLiteOpenHelper {

    private static SqliteHelper instance;
    private Context ctx;

    public SqliteHelper(Context ctx){
        super(ctx,SqlContants.DBNAME,null,SqlContants.DBVersion);
        this.ctx = ctx;
    }

    // Singleton
    public static synchronized SqliteHelper getInstance(Context ctx){
        if(instance == null){
            instance = new SqliteHelper(ctx);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SqlContants.SCRIPT_JOGOS.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
