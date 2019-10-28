package com.example.greencity.pojo

import kotlin.properties.Delegates

class Utente {
    constructor() {}
    constructor(
        nome: String,
        cognome: String,
        regione: String,
        capoluogo: String,
        email: String,
        password: String
    ) {
        this.nome = nome
        this.cognome = cognome
        this.capoluogo = capoluogo
        this.regione = regione
        this.email = email
        this.password = password
    }

    private var nome: String by Delegates.notNull()
    private var cognome: String by Delegates.notNull()
    private var regione: String by Delegates.notNull()
    private var capoluogo: String by Delegates.notNull()
    private var email: String by Delegates.notNull()
    private var password: String by Delegates.notNull()

}
