package com.example.greencity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.widget.Button
import com.google.android.gms.tasks.OnCompleteListener
import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.core.services.mongodb.remote.RemoteInsertOneResult
import org.bson.Document
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient
import com.mongodb.stitch.android.core.StitchAppClient






class SignIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val client = Stitch.initializeDefaultAppClient("greencity-pfvds")
        var mongoClient = client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas")
        var itemsCollection = mongoClient.getDatabase("greencitydb").getCollection("user")
        // get reference to button
        val btn_Register = findViewById(R.id.register_btn) as Button;
        // set on-click listener
        btn_Register.setOnClickListener {
            val doc1 = Document()
                .append("user", "basketball")

            val insertTask = itemsCollection.insertOne(doc1)
            insertTask.addOnCompleteListener(OnCompleteListener<RemoteInsertOneResult> { task ->
                if (task.isSuccessful) {
                    Log.d(
                        "app", String.format(
                            "successfully inserted item with id %s",
                            task.result.insertedId
                        )
                    )
                } else {
                    Log.e("app", "failed to insert document with: ", task.exception)
                }
            })
        }
    }


}
