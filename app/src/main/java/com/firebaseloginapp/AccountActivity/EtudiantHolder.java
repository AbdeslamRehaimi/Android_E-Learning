package com.firebaseloginapp.AccountActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebaseloginapp.R;

public class EtudiantHolder extends RecyclerView.ViewHolder implements View.OnClickListener   {
     TextView txtNom,txtPrenom,txtCne,txtFiliere,txtEmail;
    itemClickListner itemClickListner;
    EtudiantHolder(@NonNull View itemView) {
        super(itemView);

        this.txtNom = itemView.findViewById(R.id.NomEtudiant  );
        this.txtPrenom = itemView.findViewById(R.id.PrenomEtudiant  );
        this.txtCne = itemView.findViewById(R.id.cneEtudiant  );
        this.txtFiliere = itemView.findViewById(R.id.txtFiliere );
        this.txtEmail = itemView.findViewById(R.id.txtEmail);

        //itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        this.itemClickListner.onItemClickedListenner(v,getLayoutPosition());

    }
    public void setItemClickListner(itemClickListner icl){
        this.itemClickListner = icl;
    }
}
