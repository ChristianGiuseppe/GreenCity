package com.example.greencity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient
import org.bson.Document
import java.util.*


class SignIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val client = Stitch.getDefaultAppClient()
        val mongoClient = client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas")
        val itemsCollection = mongoClient.getDatabase("GreenCity").getCollection("User")
        itemsCollection.sync().find()
        val register: Button = findViewById(R.id.signin_btn)

        register.setOnClickListener {
        }
    }

}
