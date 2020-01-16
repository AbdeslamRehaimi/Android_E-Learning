package com.firebaseloginapp.AccountActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebaseloginapp.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
public class AdapterEtudiant  extends RecyclerView.Adapter<EtudiantHolder>{

    Context c;
    ArrayList<Etudiant> models;

    public AdapterEtudiant(Context c, ArrayList<Etudiant> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public EtudiantHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row2,null);
        return new EtudiantHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EtudiantHolder holder, int position) {
        holder.txtNom.setText(models.get(position).getNom() + " " +models.get(position).getPrenom());
        //holder.txtPrenom.setText(models.get(position).getPrenom());
        holder.txtPrenom.setText(null);
        //holder.mImeView.setImageResource(models.get(position).getImg);
        //holder.txtCne.setText(models.get(position).getCne());
        holder.txtCne.setText(null);
        holder.txtEmail.setText(String.valueOf(models.get(position).getEmail()));
        holder.txtFiliere.setText(models.get(position).getFilier());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}

