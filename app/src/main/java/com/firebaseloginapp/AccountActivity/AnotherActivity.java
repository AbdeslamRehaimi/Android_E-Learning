package com.firebaseloginapp.AccountActivity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.FirebaseDatabase;

import com.firebaseloginapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class AnotherActivity extends AppCompatActivity {
    TextView mTitle,mDesc,mProf;
    Button btnDownload;
    RecyclerView mRecyclerView;
    CommentAdapter myAdapter ;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    String fileName;
    ImageView mImageTv, userImg;
    EditText cmnt;
    Button addCmnt;
    String ContenuKey;
    RecyclerView rvComment;
    CommentAdapter cmntAdapter;
    ArrayList<comment> listComment;
    static String COMMENT_KEY = "comment" ;
    StorageReference ref;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        btnDownload = (Button) findViewById(R.id.btnTelecharger);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download();
            }
        });


        ActionBar actionBar = getSupportActionBar();
        mTitle = findViewById(R.id.TitleTv);

        mDesc = findViewById(R.id.descriptions);
        mProf = findViewById(R.id.nomprof);
        rvComment = findViewById(R.id.recyclerView1);
        cmnt = findViewById(R.id.contenu_detail_comment);
        userImg = findViewById(R.id.contenu_detail_currentuser_img);
        addCmnt = findViewById(R.id.contenu_detail_add_comment_btn);
        //listCmntImg = findViewById(R.id.contenu_detail_user_img);

        rvComment.setLayoutManager(new LinearLayoutManager(this));


        firebaseAuth = FirebaseAuth.getInstance();
        //firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();


        Intent intent = getIntent();
        String mTitleTv = intent.getStringExtra("iTitle");
        String mDescTv = intent.getStringExtra("iDesc");
        String mProfTv = intent.getStringExtra("inomProf");
       /* byte[] mBytes = getIntent().getByteArrayExtra("iImage");
        Bitmap bitmap = BitmapFactory.decodeByteArray(mBytes,0 ,mBytes.length); */

        mTitle.setText(mTitleTv);
        mDesc.setText(mDescTv);
        mProf.setText(mProfTv);
        fileName = intent.getStringExtra("gFile");

    //  mImageTv.setImageBitmap(bitmap);

        addCmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference commentReference = firebaseDatabase.getInstance().getReference("DataBase").child("comment");
                String comment_content = cmnt.getText().toString();
                // String uid = firebaseUser.getUid();
               String uname = firebaseAuth.getCurrentUser().getEmail();
               //  String uimg = firebaseUser.getPhotoUrl().toString();

                comment comment = new comment(comment_content,"1",R.drawable.manwhiteicon,uname);

                commentReference.push().setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        showMessage("comment added");
                        cmnt.setText("");
                        addCmnt.setVisibility(View.VISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMessage("fail to add comment : "+e.getMessage());
                    }
                });


            }
        });

        final ArrayList<comment> models = new ArrayList<>();
        // Insertion
        FirebaseDatabase.getInstance().getReference("DataBase").child("comment").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                auth = FirebaseAuth.getInstance();
                for(DataSnapshot snapshot :  dataSnapshot.getChildren()){
                  /*
                    c.setContent("this is my comment");
                    c.setUimg(R.drawable.manicon);
                    c.setUname("commentateur");
                    c.setUid("5"); */
                    comment comments = snapshot.getValue(comment.class);
                    String content = comments.getContent();
                    String uid = comments.getUid();
                    int uimg = comments.getUimg();
                    String uname = comments.getUname();
                    comment cmnt = new comment(content,uid,uimg,uname);
                    models.add(cmnt);
                }

                myAdapter = new CommentAdapter(AnotherActivity.this.getBaseContext(),models);
                rvComment.setAdapter(myAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void download(){
        FirebaseStorage  storage  = firebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(fileName);

        //ref = firebaseStorage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/data-88112.appspot.com/o/uploads%2Fdfg.pdf?alt=media&token=35ef2b5c-4d95-4895-940f-6eec86d39365");
       // ref = storageReference.child("uploads/CoursMobile.pdf");
        //ref = storageReference.getReferenceFromUrl(fileName);

        Log.v("haha", fileName);



        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFile(AnotherActivity.this, "votreCours", ".pdf", Environment.DIRECTORY_DOWNLOADS, url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void downloadFile(Context context, String fileName, String fileExtention, String destinationDirectory, String url){
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtention);
        downloadManager.enqueue(request);
    }







    private void iniRvComment() {
        rvComment.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference commentRef = firebaseDatabase.getReference(COMMENT_KEY).child("comment");
        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listComment = new ArrayList<>();
                for (DataSnapshot snap:dataSnapshot.getChildren()) {

                    comment comnt = snap.getValue(comment.class);
                    listComment.add(comnt) ;

                }

                CommentAdapter commentAdapter = new CommentAdapter(getApplicationContext(),listComment);
                rvComment.setAdapter(commentAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showMessage(String message) {

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }


  /*  private ArrayList<comment> getMylist1(){

        ArrayList<comment> Cmt = new ArrayList<>();

        comment c = new comment();
        c.setContent("this is my comment");
        c.setUimg(R.drawable.manicon);
        c.setUname("commentateur");
        c.setUid("5");
        Cmt.add(c);

        comment d = new comment();
        d.setContent("this is my comment nigga");
        d.setUimg(R.drawable.manicon);
        d.setUname("commentateur 1");
        d.setUid("5");
        Cmt.add(d);

        comment e = new comment();
        e.setContent("this is my comment");
        e.setUimg(R.drawable.manicon);
        e.setUname("commentateur 2");
        e.setUid("5");
        Cmt.add(e);

        comment f = new comment();
        f.setContent("this is my comment");
        f.setUimg(R.drawable.manicon);
        f.setUname("commentateur 3");
        f.setUid("5");
        Cmt.add(f);

        return Cmt;

    } */
}
