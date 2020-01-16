package com.firebaseloginapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebaseloginapp.AccountActivity.Contenu;
import com.firebaseloginapp.AccountActivity.ContenuBean;
import com.firebaseloginapp.AccountActivity.ContenuDetail;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;


public class pdfUploader extends AppCompatActivity {

    private Button save, annuler, importer;
    private EditText txtTitre, txtDescription, txtPdfname;
    private Spinner spModule, spType, spFilier;
    @Nullable Intent dataAA;

    ContenuBean contenu ;
    private FirebaseAuth auth;
    StorageReference storageReference;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_uploader);

        save = (Button) findViewById(R.id.btnSave);
        annuler = (Button) findViewById(R.id.btnCancel);
        importer = (Button) findViewById(R.id.btnChoose);

        txtTitre = (EditText) findViewById(R.id.txtTitre);
        txtDescription = (EditText) findViewById(R.id.txtDescription);
        txtPdfname = (EditText) findViewById(R.id.txtPdfName);

        spModule = (Spinner) findViewById(R.id.txtModule);
        spType = (Spinner) findViewById(R.id.txtType);
        spFilier = (Spinner) findViewById(R.id.txtFiliere);

        //contenu = new Contenu();

        //firebase shit
        auth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("DataBase").child("Contenue");

        importer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPdfFile();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPDFFile(dataAA.getData());
            }
        });


        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pdfUploader.this, HomeProf.class);
                startActivity(intent);
            }
        });

    }

    public void selectPdfFile(){
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select pdf file"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            dataAA = data;
            //uploadPDFFile(data.getData());
        }
    }

    public void uploadPDFFile(Uri data){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading ...");
        progressDialog.show();

        StorageReference reference = storageReference.child("uploads/"+txtPdfname.getText().toString()+".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        auth = FirebaseAuth.getInstance();
                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                        while(!uri.isComplete());
                        Uri url = uri.getResult();
                        ContenuDetail cont = new ContenuDetail();
                        cont.setDescription(txtDescription.getText().toString());
                        cont.setFile(url.toString());
                        cont.setNomprof(auth.getCurrentUser().getEmail().toString());
                 //       cont.setNomprof();
                        cont.setModule(spModule.getSelectedItem().toString());
                        cont.setType(spType.getSelectedItem().toString());
                        cont.setTitle(txtTitre.getText().toString());

                        Date today = new Date();
                        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
                        String dt = form.format(today);
                        cont.setDate(dt.toString());


                        databaseReference.child(databaseReference.push().getKey()).setValue(cont);
                        Toast.makeText(pdfUploader.this, "file uploaded", Toast.LENGTH_SHORT);
                        progressDialog.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()/ taskSnapshot.getTotalByteCount());
                progressDialog.setMessage("Uploaded "+(int)progress+" %");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(pdfUploader.this, "Un erreur est survenue "+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                Toast.makeText(pdfUploader.this, "Votre Cours bien sauvegarder !", Toast.LENGTH_LONG).show();
            }
        });
    }
}
