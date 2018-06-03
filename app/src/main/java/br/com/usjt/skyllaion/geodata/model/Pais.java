package br.com.usjt.skyllaion.geodata.model;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;

import br.com.usjt.skyllaion.geodata.R;
import br.com.usjt.skyllaion.geodata.controller.Util;

public class Pais extends Fragment implements Serializable, Comparable{

    private String nome;
    private String codigo3;
    private String capital;
    private String regiao;
    private String subRegiao;
    private String demonimo;
    private int populacao;
    private int area;
    private String bandeira;
    private double gini;
    private ArrayList<String> idiomas;
    private ArrayList<String> moedas;
    private ArrayList<String> dominios;
    private ArrayList<String> fusos;
    private ArrayList<String> fronteiras;
    private double latitude;
    private double longitude;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo3() {
        return codigo3;
    }

    public void setCodigo3(String codigo3) {
        this.codigo3 = codigo3;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getSubRegiao() {
        return subRegiao;
    }

    public void setSubRegiao(String subRegiao) {
        this.subRegiao = subRegiao;
    }

    public String getDemonimo() {
        return demonimo;
    }

    public void setDemonimo(String demonimo) {
        this.demonimo = demonimo;
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public double getGini() {
        return gini;
    }

    public void setGini(double gini) {
        this.gini = gini;
    }

    public ArrayList<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(ArrayList<String> idiomas) {
        this.idiomas = idiomas;
    }

    public ArrayList<String> getMoedas() {
        return moedas;
    }

    public void setMoedas(ArrayList<String> moedas) {
        this.moedas = moedas;
    }

    public ArrayList<String> getDominios() {
        return dominios;
    }

    public void setDominios(ArrayList<String> dominios) {
        this.dominios = dominios;
    }

    public ArrayList<String> getFusos() {
        return fusos;
    }

    public void setFusos(ArrayList<String> fusos) {
        this.fusos = fusos;
    }

    public ArrayList<String> getFronteiras() {
        return fronteiras;
    }

    public void setFronteiras(ArrayList<String> fronteiras) {
        this.fronteiras = fronteiras;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView txtPais = (TextView) getActivity().findViewById(R.id.texto);
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.foto_pais);
        Drawable drawable = Util.getDrawable(getActivity(), getCodigo3().toLowerCase());
        if(drawable == null){
            drawable = getActivity().getDrawable(R.drawable.bandeira);
        }
        txtPais.setText(toString());
        imageView.setImageDrawable(drawable);
    }

    @Override
    public String toString() {
        return  "\nNome: '" + nome + '\'' +
                "\nCodigo3: '" + codigo3 + '\'' +
                "\nCapital: '" + capital + '\'' +
                "\nRegião: '" + regiao + '\'' +
                "\nsubRegiao='" + subRegiao + '\'' +
                "\nDemonimo: '" + demonimo + '\'' +
                "\nPopulação: " + populacao +
                "\nArea: " + area +
                "\nBandeira: '" + bandeira + '\'' +
                "\nGini: " + gini +
                "\nIdiomas: " + idiomas +
                "\nMoedas: " + moedas +
                "\nDominios: " + dominios +
                "\nFusos:" + fusos +
                "\nFronteiras: " + fronteiras +
                "\nLatitude: " + latitude +
                "\nLongitude: " + longitude;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null || o.getClass() != getClass()) {
            return 0;
        } else {
            Pais pais = (Pais) o;
            Collator c = Collator.getInstance();
            c.setStrength(Collator.PRIMARY);
            return c.compare(this.nome, pais.getNome());
        }
    }


}
