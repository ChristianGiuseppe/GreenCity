package com.example.greencity.pojo;

import java.util.ArrayList;
import java.util.List;

public class InformazioniGenerali {
    private static InformazioniGenerali informazioniGenerali = null;
    private Utente user = null;
    private List<Regioni> regioni;
    private List<Markers>markers;

    public InformazioniGenerali() {
        regioni = new ArrayList<>();
        markers =  new ArrayList<>();
    }



    public static InformazioniGenerali getInformazioniGenerali() {
        if(informazioniGenerali==null)
            informazioniGenerali = new InformazioniGenerali();
        return informazioniGenerali;
    }

    public  Utente getUser() {
        return user;
    }

    public  void setUser(Utente user) {
        this.user = user;
    }

    public List<Regioni> getRegioni() {
        return regioni;
    }

    public void setRegioni(List<Regioni> regioni) {
        this.regioni = regioni;
    }

    public List<Markers> getMarkers() {
        return markers;
    }

    public void setMarkers(List<Markers> markers) {
        this.markers = markers;
    }


}
