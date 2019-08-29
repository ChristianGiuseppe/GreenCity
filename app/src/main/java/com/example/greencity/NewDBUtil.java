package com.example.greencity;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ProgressBar;

import com.example.greencity.activity.SignIn;
import com.example.greencity.pojo.InformazioniGenerali;
import com.example.greencity.pojo.Regioni;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mongodb.MongoClientSettings;
import com.mongodb.lang.NonNull;
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
import java.util.List;

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

    public void getListaRegioni(Context context , ConstraintLayout constraintLayout, ProgressBar progressBar){
        constraintLayout.setVisibility(View.VISIBLE);
        ArrayList<Regioni> regioni = new ArrayList<>();
        RemoteMongoCollection collectionRegione =remoteMongoDatabase.getCollection("Regioni", Regioni.class).withCodecRegistry(codecRegistry);
        Document documentFilter = new Document();
        RemoteFindIterable<Regioni> resultFind = collectionRegione.find(documentFilter);
        Task<List<Regioni>> itemsTask =resultFind.into(regioni);
        itemsTask.addOnCompleteListener(task -> {
            InformazioniGenerali informazioniGenerali = InformazioniGenerali.getInformazioniGenerali();
            informazioniGenerali.setRegioni(task.getResult());
            Intent iSignin = new Intent(context, SignIn.class);
            iSignin.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            constraintLayout.setVisibility(View.GONE);
            context.startActivity(iSignin);
        });
    }
}
