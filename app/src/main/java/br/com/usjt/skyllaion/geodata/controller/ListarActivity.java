package br.com.usjt.skyllaion.geodata.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usjt.skyllaion.geodata.R;
import br.com.usjt.skyllaion.geodata.model.Data;
import br.com.usjt.skyllaion.geodata.model.Pais;

public class ListarActivity extends Activity {
    public static final String PAIS = "br.com.usjt.skyllaion.geodata.model.Pais";
    Activity atividade;
    Pais[] paises;
    ArrayList<String> nomes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        atividade = this;
        Intent intent = getIntent();
        String continente = intent.getStringExtra(MainActivity.CONTINENTE);
        paises = Data.listarPaises(continente);
        nomes = Data.listarNomes(paises);

        ListView listView = (ListView) findViewById(R.id.lista_paises);
        PaisAdapter adapter = new PaisAdapter(paises, this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(atividade, DetalheActivity.class);
                intent.putExtra(PAIS, paises[position]);
                startActivity(intent);
            }

        });
    }
}
