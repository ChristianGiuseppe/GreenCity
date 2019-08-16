package com.example.greencity;

import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import org.bson.Document;

public class ConnectionDBUtil {

    public static StitchAppClient defaultAppClient(){
        return Stitch.getDefaultAppClient();
    }
    public static RemoteMongoClient getServiceClient(){
        StitchAppClient client = Stitch.getDefaultAppClient();
        return client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");
    }
    public static RemoteMongoCollection<Document> getDB(){
        StitchAppClient client = Stitch.getDefaultAppClient();
        RemoteMongoClient mongoClient= client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");
        return mongoClient.getDatabase("test").getCollection("my_collection");
    }
}
