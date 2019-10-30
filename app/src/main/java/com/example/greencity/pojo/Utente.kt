package com.example.greencity.pojo

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Utente {
    var nome: String? = null
    var email: String? = null
    var cognome: String? = null
    var password: String? = null
    constructor() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    constructor(username: String?, email: String?,surname: String?,password: String?) {
        this.nome = username
        this.email = email
        this.cognome = surname
        this.password = password
    }
}
