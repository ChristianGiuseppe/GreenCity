package com.example.greencity.pojo

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Markers {
    var titolo: String? = null
    var descrizione: String? = null
    var tipologia: String? = null
    var colore: String? = null
    var priorita: String? = null
    var latitudine: String? = null
    var longitudine: String? = null
    constructor() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    constructor(
        titolo: String?,
        descrizione: String?,
        tipologia: String?,
        colore: String?,
        priorita: String?,
        latitudine: String?,
        longitudine: String?

    ) {
        this.titolo = titolo
        this.descrizione = descrizione
        this.tipologia = tipologia
        this.colore = colore
        this.priorita = priorita
        this.latitudine = latitudine
        this.longitudine = longitudine
    }


}