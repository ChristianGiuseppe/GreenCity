package com.example.greencity.pojo

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Utente {
    var nome: String? = null
    var email: String? = null
    var cognome: String? = null
    var password: String? = null
    var regione: String? = null
    var capoluogo: String? = null
    var isAdmin: String? = null
    constructor() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    constructor(
        nome: String?,
        cognome: String?,
        email: String?,
        password: String?,
        regione: String?,
        capoluogo: String?,
        isAdmin: String?
    ) {
        this.nome = nome
        this.email = email
        this.cognome = cognome
        this.password = password
        this.regione = regione
        this.capoluogo = capoluogo
        this.isAdmin = isAdmin
    }


}
