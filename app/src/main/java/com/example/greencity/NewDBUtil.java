package com.example.greencity;

import com.example.greencity.pojo.Regioni;
import com.mongodb.MongoClientSettings;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteFindIterable;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class NewDBUtil {
    private StitchAppClient stitchAppClient;
    private RemoteMongoClient remoteMongoClient;
    private CodecRegistry codecRegistry;
    private RemoteMongoDatabase remoteMongoDatabase;

   public NewDBUtil(){
       codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
       stitchAppClient = Stitch.getDefaultAppClient();
       remoteMongoClient = stitchAppClient.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");
       remoteMongoDatabase = remoteMongoClient.getDatabase("GreenCity");
   }

    public ArrayList<Regioni> getListaRegioni(){
        ArrayList<Regioni> regioni = new ArrayList<>();
        RemoteMongoCollection collectionRegione =remoteMongoDatabase.getCollection("Regioni", Regioni.class).withCodecRegistry(codecRegistry);
        Document documentFilter = new Document();
        RemoteFindIterable<Regioni> resultFind = collectionRegione.find(documentFilter);
        resultFind.into(regioni);
        return regioni;
    }
}
