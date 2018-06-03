package br.com.usjt.skyllaion.geodata.controller;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import java.io.IOException;

import br.com.usjt.skyllaion.geodata.R;
import br.com.usjt.skyllaion.geodata.model.Pais;

public class MainActivity extends AppCompatActivity {
    public static final String PAIS = "br.com.usjt.skyllaion.geodata.controller.MainActivity.ListarPaises";
    public static final String CONTINENTE = "CONTINENTE";
    private Spinner spinContinente;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        spinContinente = findViewById(R.id.continente);
    }

    public void listarPaises(View view) {
        String continente = spinContinente.getSelectedItem().toString();
        if (continente.equals("Todos")) {
            new JSONPaises().execute("https://restcountries.eu/rest/v2/all");
        } else {
            new JSONPaises().execute("https://restcountries.eu/rest/v2/region/" + continente);
        }
    }

    private class JSONPaises extends AsyncTask<String, Void, Pais[]> {
        @Override
        protected Pais[] doInBackground(String... strings) {

            Database db = new Database(context);
            Pais[] paises = db.selecionaPaises();
            if (paises == null || paises.length < 1) {
                try {
                    paises = Network.buscarPaises(strings[0]).toArray(new Pais[0]);
                    db.inserePaises(paises);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                Log.d("Banco de dados","Países.");
            }
            return paises;
        }

        protected void onPostExecute(Pais[] paises) {
            Intent intent = new Intent(context, ListarActivity.class);
            intent.putExtra(PAIS, paises.toString());
            intent.putExtra(CONTINENTE, spinContinente.getSelectedItem().toString());
            startActivity(intent);
        }

    }

}
