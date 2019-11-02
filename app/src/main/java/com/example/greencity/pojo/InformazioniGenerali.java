package com.example.greencity.pojo;

import java.util.ArrayList;
import java.util.List;

public class InformazioniGenerali {
    private static InformazioniGenerali informazioniGenerali = null;
    private List<Regioni> regioni;

    public InformazioniGenerali() {
        regioni = new ArrayList<>();
    }

    public static InformazioniGenerali getInformazioniGenerali() {
        if(informazioniGenerali==null)
            informazioniGenerali = new InformazioniGenerali();
        return informazioniGenerali;
    }

    public List<Regioni> getRegioni() {
        return regioni;
    }

    public void setRegioni(List<Regioni> regioni) {
        this.regioni = regioni;
    }
}
