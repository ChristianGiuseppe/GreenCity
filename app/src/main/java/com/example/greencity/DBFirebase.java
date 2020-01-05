package com.example.greencity;

import com.example.greencity.pojo.Markers;
import com.example.greencity.pojo.Utente;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBFirebase {
    private static DBFirebase dbFirebase = null;
    private DatabaseReference databaseReference;

    public DBFirebase() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
    }



    public static DBFirebase getDbFirebase() {
        if (dbFirebase == null)
            dbFirebase = new DBFirebase();
        return dbFirebase;
    }
    public static DBFirebase setDbFirebaseNull() {
        dbFirebase = null;
        return dbFirebase;
    }

    public static void setDbFirebase(DBFirebase dbFirebase) {
        DBFirebase.dbFirebase = dbFirebase;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public void setDatabaseReference(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public void signIn(Utente user) {
        databaseReference.child("users").push().setValue(user);

    }

    public void insertReport(Markers m, String idUser) {
        databaseReference.child("users").child(idUser).child("lista_report").push().setValue(m);
    }

}
