package com.example.greencity.pojo

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Markers {
    var keyUser: String? = null
    var titolo: String? = null
    var descrizione: String? = null
    var latitudine: String? = null
    var longitudine: String? = null
    var stato: String? = null
    var dataOra:  String? =  null

    constructor() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    constructor(
        keyUser: String?,
        titolo: String?,
        descrizione: String?,
        latitudine: String?,
        longitudine: String?,
        stato: String ?,
        dataOra: String ?

    ) {
        this.keyUser = keyUser
        this.titolo = titolo
        this.descrizione = descrizione
        this.latitudine = latitudine
        this.longitudine = longitudine
        this.stato =  stato
        this.dataOra = dataOra
    }


}