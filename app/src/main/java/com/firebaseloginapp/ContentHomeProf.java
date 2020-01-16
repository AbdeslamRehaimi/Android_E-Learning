package com.firebaseloginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebaseloginapp.AccountActivity.ContenuDetail;
import com.firebaseloginapp.AccountActivity.MyAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContentHomeProf extends Fragment {

    Button sendButton = null;
    View inflatedView = null;
    View inflatedView2 = null;

    RecyclerView mRecyclerView;
    MyAdapter myAdapter ;
    private FirebaseAuth auth;



    ImageView img;
    TextView welcom;

    public ContentHomeProf() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflatedView = inflater.inflate(R.layout.activity_content_home_prof, container, false);
        //this.inflatedView2 = inflater.inflate(R.layout.activity_pdf_uploader, container, false);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.activity_content_home_prof, container, false);
        /*
        sendButton = inflatedView2.findViewById(R.id.btnChoose);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("hihi", "clicked");
            }
        });
        */


        View v = inflater.inflate(R.layout.activity_content_home_prof, container, false);
        mRecyclerView = v.findViewById(R.id.recyclerView);

        //mRecyclerView.setLayoutManager(new LinearLayoutManager(ContentHomeEtudiant.this));
        //myAdapter = new MyAdapter(this,getMylist());


        mRecyclerView.setLayoutManager(new LinearLayoutManager(ContentHomeProf.this.getActivity()));

        final ArrayList<ContenuDetail> models = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference("DataBase").child("Contenue").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot :  dataSnapshot.getChildren()){
                    ContenuDetail cours = snapshot.getValue(ContenuDetail.class);
                    String titre = cours.getTitle();
                    String des = cours.getDescription();
                    String nomP = cours.getNomprof();
                    String date = cours.getDate();
                    int cmnts = cours.getCmts();
                    Log.d("Titre",titre);
                    String file = cours.getFile();
                    String module = cours.getModule();
                    String type = cours.getType();
                    Log.d("Titre",titre);

                    ContenuDetail cnD = new ContenuDetail(titre,des,nomP,date,cmnts, file, module, type);
                    cnD.setImg(R.drawable.unnamed);

                    models.add(cnD);
                }
                myAdapter = new MyAdapter(ContentHomeProf.this.getContext(),models);
                mRecyclerView.setAdapter(myAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return v;
    }
}
