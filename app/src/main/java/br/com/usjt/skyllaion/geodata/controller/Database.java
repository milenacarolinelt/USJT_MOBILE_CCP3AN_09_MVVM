package br.com.usjt.skyllaion.geodata.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.usjt.skyllaion.geodata.model.Pais;

public class Database {
    DatabaseHelper dbHelper;

    public Database(Context contexto){
        dbHelper = new DatabaseHelper(contexto);
    }

    public void inserePaises(Pais[] paises){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Contract.PaisEntry.TABLE_NAME, null, null);
        for(Pais pais:paises){
            ContentValues values = new ContentValues();
            values.put(Contract.PaisEntry.NOME, pais.getNome());
            values.put(Contract.PaisEntry.REGIAO, pais.getRegiao());
            values.put(Contract.PaisEntry.CAPITAL, pais.getCapital());
            values.put(Contract.PaisEntry.BANDEIRA, pais.getBandeira());
            values.put(Contract.PaisEntry.CODIGO, pais.getCodigo3());
            db.insert(Contract.PaisEntry.TABLE_NAME, null, values);
        }
    }

    public Pais[] selecionaPaises(){
        ArrayList<Pais> paises = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] colunas = { Contract.PaisEntry.NOME,
                Contract.PaisEntry.REGIAO,
                Contract.PaisEntry.CAPITAL,
                Contract.PaisEntry.BANDEIRA,
                Contract.PaisEntry.CODIGO};
        String ordem = Contract.PaisEntry.NOME;
        Cursor c = db.query(Contract.PaisEntry.TABLE_NAME, colunas, null, null,
                ordem, null, null);
        while(c.moveToNext()) {
            Pais pais = new Pais();
            pais.setNome(c.getString(c.getColumnIndex(Contract.PaisEntry.NOME)));
            pais.setRegiao(c.getString(c.getColumnIndex(Contract.PaisEntry.REGIAO)));
            pais.setCapital(c.getString(c.getColumnIndex(Contract.PaisEntry.CAPITAL)));
            pais.setBandeira(c.getString(c.getColumnIndex(Contract.PaisEntry.BANDEIRA)));
            pais.setCodigo3(c.getString(c.getColumnIndex(Contract.PaisEntry.CODIGO)));

            paises.add(pais);
        }
        c.close();
        if(paises.size()> 0) {
            return paises.toArray(new Pais[0]);
        } else {
            return new Pais[0];
        }
    }
}
