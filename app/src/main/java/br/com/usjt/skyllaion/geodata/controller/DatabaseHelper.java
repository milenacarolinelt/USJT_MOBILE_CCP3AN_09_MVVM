package br.com.usjt.skyllaion.geodata.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int VERSAO = 1;
    public static final String DATABASE = "Countries.db";
    public static final String SQL_CREATE_PAIS =
            "CREATE TABLE " + Contract.PaisEntry.TABLE_NAME + "(" +
                    Contract.PaisEntry._ID + " INTEGER PRIMARY KEY,"+
                    Contract.PaisEntry.NOME + " TEXT," +
                    Contract.PaisEntry.REGIAO + " TEXT," +
                    Contract.PaisEntry.CAPITAL + " TEXT," +
                    Contract.PaisEntry.BANDEIRA + " TEXT," +
                    Contract.PaisEntry.CODIGO + " TEXT)";
    public static final String SQL_DROP_PAIS =
            "DROP TABLE IF EXISTS " + Contract.PaisEntry.TABLE_NAME;

    public DatabaseHelper(Context contexto){
        super(contexto, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PAIS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_PAIS);
        db.execSQL(SQL_CREATE_PAIS);
    }
}
