package com.example.greencity.pojo;

import java.util.ArrayList;

public class Regioni {

    private  String nomeRegione;
    private  ArrayList<String> nomiCapoluoghi;

    public Regioni() {    }

    public String getNomeRegione() {
        return nomeRegione;
    }

    public ArrayList<String> getNomiCapoluoghi() {
        return nomiCapoluoghi;
    }

    public void setNomeRegione(String nomeRegione) {
        this.nomeRegione = nomeRegione;
    }

    public void setNomiCapoluoghi(ArrayList<String> nomiCapoluoghi) {
        this.nomiCapoluoghi = nomiCapoluoghi;
    }

    @Override
    public String toString() {
        return nomeRegione;
    }
}
