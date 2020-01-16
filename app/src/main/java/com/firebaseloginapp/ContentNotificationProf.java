package com.firebaseloginapp;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebaseloginapp.AccountActivity.AdapterEtudiant;
import com.firebaseloginapp.AccountActivity.Etudiant;
import com.firebaseloginapp.AccountActivity.MyAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContentNotificationProf extends Fragment {

    RecyclerView mRecyclerView;
    AdapterEtudiant myAdapter ;
    private FirebaseAuth auth;
    View inflatedView = null;
    DatabaseReference databaseReference;

    public ContentNotificationProf() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflatedView = inflater.inflate(R.layout.activity_content_notification_prof, container, false);
        View v = inflater.inflate(R.layout.activity_content_notification_prof, container, false);


        mRecyclerView = v.findViewById(R.id.recyclerViewEtudList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ContentNotificationProf.this.getActivity()));
        final ArrayList<Etudiant> models = new ArrayList<>();
        // Inflate the layout for this fragment

       FirebaseDatabase.getInstance().getReference("DataBase").child("Etudiant").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot :  dataSnapshot.getChildren()){

                    Etudiant etd = snapshot.getValue(Etudiant.class);
                    String Nom,Prenom,cne,filier,email;
                    Nom = etd.getNom();

                    Prenom = etd.getPrenom();
                    cne = etd.getCne();
                    filier = etd.getFilier();
                    email = etd.getEmail();
                    Etudiant etudiant = new Etudiant(Nom,Prenom,cne,filier,email);
                    models.add(etudiant);

                }
                //myAdapter = new AdapterEtudiant(ContentNotificationProf.this.getActivity(),models);
                //mRecyclerView.setAdapter(myAdapter);
                myAdapter = new AdapterEtudiant(ContentNotificationProf.this.getActivity(),models);
                mRecyclerView.setAdapter(myAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return v;
    }


/*
    private ArrayList<Etudiant> getMylist(){

        ArrayList<Etudiant> models = new ArrayList<>();

        Etudiant m = new Etudiant();
        m.setNom("Tkinter");
        m.setPrenom("Cette Partie de cours Contient les notion de heritage");
        m.setCne("24848");

        m.setEmail("05/02/2020");
        m.setFilier("ssasa");



        models.add(m);

        Etudiant n = new Etudiant();
        n.setNom("Tkinter");
        n.setPrenom("Cette Partie de cours Contient les notion de heritage");
        n.setCne("24848");

        n.setEmail("05/02/2020");
        n.setFilier("ssasa");
        models.add(n);
        return models;

    } */
}
