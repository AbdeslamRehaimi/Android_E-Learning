package com.firebaseloginapp.AccountActivity;

import android.content.Intent;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.firebaseloginapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    //hit option + enter if you on mac , for windows hit ctrl + enter
// les attributs pour etudiants
    private EditText inputEmail, inputPassword,NomE,PrenomE,cne,filierE;
    private LinearLayout E,P;

    private RadioButton RE,RP;
    private Button btnSignIn, btnSignUp;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private ImageView imagelogo;

    // les atrributs pour le prof
    private EditText inputEmailP, inputPasswordP,NomP,PrenomP,module,filierP;

    private Button btnSignInP, btnSignUpP;
    private ProgressBar progressBarP;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
         //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        // attribute Etudiant
        NomE = findViewById(R.id.nomE);
        PrenomE = findViewById(R.id.prenomE);
        cne = findViewById(R.id.cne);
        filierE = findViewById(R.id.filier);
        RE = findViewById(R.id.EtudiantR);
        E = findViewById(R.id.etudlayout);
        P = findViewById(R.id.proflayout);
        E.setVisibility(E.INVISIBLE);
        P.setVisibility(P.INVISIBLE);
        // attribute Professeur
        btnSignInP = (Button) findViewById(R.id.sign_in_buttonP);
        btnSignUpP = (Button) findViewById(R.id.sign_up_buttonP);
        inputEmailP = (EditText) findViewById(R.id.emailP);
        inputPasswordP = (EditText) findViewById(R.id.passwordP);
        progressBarP = (ProgressBar) findViewById(R.id.progressBarp);

        RP = findViewById(R.id.ProfR);
        NomP = findViewById(R.id.nomP);
        PrenomP = findViewById(R.id.prenomP);
        module =findViewById(R.id.Module);
        filierP = findViewById(R.id.filierP);

        imagelogo =  findViewById(R.id.logoE);


        // Radio check

        RE.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          imagelogo.setVisibility(imagelogo.INVISIBLE);
                if(RE.isChecked()){
                    E.setVisibility(E.VISIBLE);
                    P.setVisibility(P.INVISIBLE);
                }
            }
        });
        RP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                imagelogo.setVisibility(imagelogo.INVISIBLE);
                if(RP.isChecked()){
                    E.setVisibility(E.INVISIBLE);
                    P.setVisibility(P.VISIBLE);
                }
            }
        });



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                Etudiant  etudiant = new Etudiant(NomE.getText().toString(),PrenomE.getText().toString(),cne.getText().toString(),filierE.getText().toString(),inputEmail.getText().toString());
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("DataBase").child("Etudiant");
                                ref.push().setValue(etudiant);

                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });



        // Pour le proooof

        btnSignUpP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmailP.getText().toString().trim();
                String password = inputPasswordP.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                              Professeur professeur = new Professeur(NomP.getText().toString(),PrenomP.getText().toString(),module.getText().toString(),filierP.getText().toString(),inputEmailP.getText().toString());
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("DataBase").child("Professeur");
                                ref.push().setValue(professeur);

                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

}

