package br.com.usjt.skyllaion.geodata.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import br.com.usjt.skyllaion.geodata.model.Pais;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Network {

    public static ArrayList<Pais> buscarPaises(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ArrayList<Pais> paises = new ArrayList<>();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        String json = response.body().string();

        try {
            JSONArray lista = new JSONArray(json);
            for (int i = 0; i < lista.length(); i++) {
                JSONObject item = (JSONObject) lista.get(i);
                Pais pais = new Pais();
                pais.setNome(item.getString("name"));
                pais.setCapital(item.getString("capital"));
                pais.setRegiao(item.getString("region"));
                pais.setSubRegiao(item.getString("subregion"));
                pais.setPopulacao(item.getInt("population"));
                pais.setCodigo3(item.getString("alpha3Code"));

                try {
                    pais.setLatitude(item.getJSONArray("latlng").getInt(0));
                    pais.setLongitude(item.getJSONArray("latlng").getInt(1));
                } catch (Exception e){
                    pais.setLatitude(0);
                    pais.setLongitude(0);
                }

                try {
                    pais.setArea(item.getInt("area"));
                } catch (Exception e){
                    pais.setArea(0);
                }

                try {
                    pais.setGini(item.getInt("gini"));
                } catch (Exception e){
                    pais.setGini(0);
                }



                pais.setDemonimo(item.getString("demonym"));
                pais.setBandeira(item.getString("flag"));

                ArrayList<String> fusos = new ArrayList<>();
                for (int j = 0;j<item.getJSONArray("timezones").length(); j++){
                    fusos.add(item.getJSONArray("timezones").getString(j));
                }
                pais.setFusos(fusos);

                ArrayList<String> moedas = new ArrayList<>();
                for (int j = 0;j<item.getJSONArray("currencies").length(); j++){
                    moedas.add(item.getJSONArray("currencies").getJSONObject(j).getString("code"));
                }
                pais.setMoedas(moedas);

                ArrayList<String> idiomas = new ArrayList<>();
                for (int j = 0;j<item.getJSONArray("languages").length(); j++){
                    idiomas.add(item.getJSONArray("languages").getJSONObject(j).getString("name"));
                }
                pais.setIdiomas(idiomas);

                ArrayList<String> dominios = new ArrayList<>();
                for (int j = 0;j<item.getJSONArray("topLevelDomain").length(); j++){
                    dominios.add(item.getJSONArray("topLevelDomain").getString(j));
                }
                pais.setDominios(dominios);

                ArrayList<String> fronteiras = new ArrayList<>();
                for (int j = 0;j<item.getJSONArray("borders").length(); j++){
                    fronteiras.add(item.getJSONArray("borders").getString(j));
                }
                pais.setFronteiras(fronteiras);
                paises.add(pais);


            }

        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        return paises;
    }
}
