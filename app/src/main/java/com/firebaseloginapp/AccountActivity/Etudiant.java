package com.firebaseloginapp.AccountActivity;

import java.io.Serializable;

public class Etudiant implements Serializable {
private String Nom,Prenom,cne,filier,email;

  public Etudiant(String nom, String prenom, String cne, String filier, String email) {
        Nom = nom;
        Prenom = prenom;
        this.cne = cne;
        this.filier = filier;
        this.email = email;   }

    public Etudiant() {

    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getFilier() {
        return filier;
    }

    public void setFilier(String filier) {
        this.filier = filier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
