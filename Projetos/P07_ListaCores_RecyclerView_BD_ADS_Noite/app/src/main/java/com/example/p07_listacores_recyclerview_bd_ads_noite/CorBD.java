package com.example.p07_listacores_recyclerview_bd_ads_noite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class CorBD extends SQLiteOpenHelper {

    public static class TabCor implements BaseColumns {
        public static final String TABELA = "tab_cor";
        public static final String COL_ID = "_id";
        public static final String COL_NOME = "nome";
    }

    public static final String SQL_TABELA = "CREATE TABLE IF NOT EXISTS " +
            TabCor.TABELA + " ( " + TabCor.COL_ID + " INTEGER PRIMARY KEY, " +
            TabCor.COL_NOME + " TEXT )";

    private static final int VERSAO_BD = 1;
    private static final String NOME_BD = "cores.db";

    public CorBD(@Nullable Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TABELA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
