package com.example.greencity.pojo;


import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public class Regioni implements Serializable {

    @BsonProperty("nomeRegione")
    private  String nomeRegione;
    @BsonProperty("nomiCapoluoghi")
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
}
