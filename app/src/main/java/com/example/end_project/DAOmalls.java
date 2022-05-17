package com.example.end_project;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOmalls {
    private final DatabaseReference databaseReference;
    public DAOmalls()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(M_RV.class.getSimpleName());
    }
    public Task<Void> add(M_RV mall){
        return databaseReference.push().setValue(mall);
    }
    public Query get(){
        return databaseReference.orderByKey();
    }

}
