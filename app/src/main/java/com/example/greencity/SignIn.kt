package com.example.greencity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient
import org.bson.Document


class SignIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val client = Stitch.getDefaultAppClient()
        val mongoClient = client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas")
        val collection = mongoClient.getDatabase("test").getCollection("my_collection")

        val document = Document()
        document["text"] = "prova"
        document["user_id"] = client.auth.user!!.id
        val register: Button = findViewById(R.id.signin_btn)

        register.setOnClickListener {
            collection.insertOne(document).addOnSuccessListener {
                Log.d("STITCH", "inserito")
            }
        }
    }

}
