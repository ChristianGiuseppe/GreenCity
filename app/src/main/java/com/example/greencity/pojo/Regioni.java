package com.example.greencity.pojo;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class Regioni {
    private String nome;
    private ArrayList<String> capoluoghi;

    public Regioni() {
    }

    public Regioni(String nome, ArrayList<String> capoluoghi) {
        this.nome = nome;
        this.capoluoghi = capoluoghi;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<String> getCapoluoghi() {
        return capoluoghi;
    }

    public void setCapoluoghi(ArrayList<String> capoluoghi) {
        this.capoluoghi = capoluoghi;
    }

    @Override
    public String toString() {
        return nome;
    }
}
