package com.example.greencity.pojo;

import java.util.ArrayList;
import java.util.List;

public class InformazioniGenerali {
    private static InformazioniGenerali informazioniGenerali = null;
    private boolean request_gps;
    private Utente user = null;
    private String idUs = null;
    private List<Regioni> regioni;
    private ArrayList<Markers> markers;
    public ArrayList<Markers> listaDone;
    public ArrayList<Markers> listaWait;
    public ArrayList<Markers> listaReject;



    public InformazioniGenerali() {
        regioni = new ArrayList<>();
        markers = new ArrayList<>();
        listaWait = new ArrayList<>();
        listaDone = new ArrayList<>();
        listaReject = new ArrayList<>();
        request_gps = false;
    }

    public static InformazioniGenerali getInformazioniGenerali() {
        if (informazioniGenerali == null)
            informazioniGenerali = new InformazioniGenerali();
        return informazioniGenerali;
    }

    public void setRequest_gps(boolean request_gps) {
        this.request_gps = request_gps;
    }

    public boolean isRequest_gps() {
        return request_gps;
    }

    public String getIdUs() {
        return idUs;
    }

    public void setIdUs(String idUs) {
        this.idUs = idUs;
    }

    public Utente getUser() {
        return user;
    }

    public void setUser(Utente user) {
        this.user = user;
    }

    public List<Regioni> getRegioni() {
        return regioni;
    }

    public void setRegioni(List<Regioni> regioni) {
        this.regioni = regioni;
    }

    public ArrayList<Markers> getMarkers() {
        return markers;
    }

    public void setMarkers(ArrayList<Markers> markers) {

        this.markers = markers;
    }

    public ArrayList<Markers> getListaDone() {
        return listaDone;
    }

    public void setListaDone(ArrayList<Markers> listaDone) {
        this.listaDone = listaDone;
    }

    public ArrayList<Markers> getListaWait() {
        return listaWait;
    }

    public void setListaWait(ArrayList<Markers> listaWait) {
        this.listaWait = listaWait;
    }

    public ArrayList<Markers> getListaReject() {
        return listaReject;
    }

    public void setListaReject(ArrayList<Markers> listaReject) {
        this.listaReject = listaReject;
    }


}
