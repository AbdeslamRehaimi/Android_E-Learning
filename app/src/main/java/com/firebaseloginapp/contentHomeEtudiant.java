package com.firebaseloginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebaseloginapp.AccountActivity.Contenu;
import com.firebaseloginapp.AccountActivity.ContenuDetail;
import com.firebaseloginapp.AccountActivity.MyAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class contentHomeEtudiant extends Fragment {
    RecyclerView mRecyclerView;
    MyAdapter myAdapter ;
    private FirebaseAuth auth;
    DatabaseReference databaseReference;


    ImageView img;
    TextView welcom;

    public contentHomeEtudiant() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_content_home_etudiant, container, false);
        mRecyclerView = v.findViewById(R.id.recyclerView);

        //mRecyclerView.setLayoutManager(new LinearLayoutManager(ContentHomeEtudiant.this));
        //myAdapter = new MyAdapter(this,getMylist());
       //  mRecyclerView.setAdapter(myAdapter);

        mRecyclerView = v.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(contentHomeEtudiant.this.getActivity()));


        final ArrayList<ContenuDetail> models = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference("DataBase").child("Contenue").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot :  dataSnapshot.getChildren()){
                    ContenuDetail cours = snapshot.getValue(ContenuDetail.class);
                    String titre = cours.getTitle();
                    String des = cours.getDescription();
                    String nomP = cours.getNomprof();
                    String date = cours.getDate().toString();
                    int cmnts = cours.getCmts();
                    Log.d("Titre",titre);
                    String file = cours.getFile();
                    String module = cours.getModule();
                    String type = cours.getType();
                    Log.d("Titre",titre);

                    ContenuDetail cnD = new ContenuDetail(titre,des,nomP,date,cmnts, file, module, type);
                    cnD.setImg(R.drawable.unnamed);

                    models.add(cnD);

                    //auth = FirebaseAuth.getInstance();
                    //String v = auth.getCurrentUser().getEmail();
                    //Log.v("Email", v);
                    //Query query = FirebaseDatabase.getInstance().getReference("DataBase").child("emaildddd").equalTo("hamouch@gmail.com");
                    //ContenuDetail k  = (ContenuDetail) FirebaseDatabase.getInstance().getReference("DataBase").child("Etudiant").equalTo("hamouch@gmail.com");




                }
                myAdapter = new MyAdapter(contentHomeEtudiant.this.getActivity(),models);
                mRecyclerView.setAdapter(myAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });








        return v;

    }







}
