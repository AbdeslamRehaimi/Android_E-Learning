package com.firebaseloginapp.AccountActivity;

import java.io.Serializable;
import java.util.Date;

public class ContenuDetail implements Serializable {
    private String title,description,nomprof;
    private int img;
    private String date;
    private int cmts;
    private String file;
    private String module;
    private String type;

    public ContenuDetail(String title, String description, String nomprof, String date, int cmts, String file, String module, String type) {
        this.title = title;
        this.description = description;
        this.nomprof = nomprof;
        this.date = date;
        this.cmts = cmts;
        this.file = file;
        this.module = module;
        this.type = type;
    }

    public ContenuDetail(String title, String description, String nomprof, String date, int cmts) {
        this.title = title;
        this.description = description;
        this.nomprof = nomprof;
        this.date = date;
        this.cmts = cmts;
    }

    public ContenuDetail() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomprof() {
        return nomprof;
    }

    public void setNomprof(String nomprof) {
        this.nomprof = nomprof;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCmts() {
        return cmts;
    }

    public void setCmts(int cmts) {
        this.cmts = cmts;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
