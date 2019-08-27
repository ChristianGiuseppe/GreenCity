package com.example.greencity.pojo;

import java.util.ArrayList;

public class InformazioniGenerali {
    private static InformazioniGenerali informazioniGenerali = null;
    private ArrayList<Regioni> regioni = null;
    private InformazioniGenerali(){
        regioni = new ArrayList<>();
    }

    public static InformazioniGenerali getInformazioniGenerali() {
        if(informazioniGenerali==null)
            informazioniGenerali = new InformazioniGenerali();
        return informazioniGenerali;
    }

    public ArrayList<Regioni> getRegioni() {
        return regioni;
    }

    public void setRegioni(ArrayList<Regioni> regioni) {
        this.regioni = regioni;
    }
}
