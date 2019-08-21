package com.example.greencity

import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.android.core.StitchAppClient
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection
import org.bson.Document

object ConnectionDBUtil {
    val serviceClient: RemoteMongoClient
        get() {
            val client = Stitch.getDefaultAppClient()
            return client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas")
        }

    val db: RemoteMongoCollection<Document>
        get() {
            val client = Stitch.getDefaultAppClient()
            val mongoClient = client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas")
            return mongoClient.getDatabase("test").getCollection("my_collection")
        }


    fun defaultAppClient(): StitchAppClient {
        return Stitch.getDefaultAppClient()
    }
}
