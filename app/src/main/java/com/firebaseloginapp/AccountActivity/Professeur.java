package com.firebaseloginapp.AccountActivity;



public class Professeur {
    private String Nom , prenom ,module , fillier,email ;

    public Professeur(String nom, String prenom, String module, String fillier, String email) {
        Nom = nom;
        this.prenom = prenom;
        this.module = module;
        this.fillier = fillier;
        this.email = email;
    }
    public Professeur() {}

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getFillier() {
        return fillier;
    }

    public void setFillier(String fillier) {
        this.fillier = fillier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
