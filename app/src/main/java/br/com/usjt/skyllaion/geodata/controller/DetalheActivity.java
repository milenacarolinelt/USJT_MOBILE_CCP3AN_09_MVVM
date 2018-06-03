package br.com.usjt.skyllaion.geodata.controller;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.usjt.skyllaion.geodata.R;
import br.com.usjt.skyllaion.geodata.model.Pais;

public class DetalheActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Intent intent = getIntent();
        Pais pais = (Pais) intent.getSerializableExtra(ListarActivity.PAIS);

        FragmentManager fm = getSupportFragmentManager ();
        if (savedInstanceState == null){
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.txtPais , pais, pais.toString());
            ft.commit();
        }
    }
}
