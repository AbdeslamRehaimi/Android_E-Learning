package com.firebaseloginapp.AccountActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.recyclerview.widget.RecyclerView;

import com.firebaseloginapp.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<ContenuDetail> models;


  public MyAdapter(Context c, ArrayList<ContenuDetail> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {
        holder.mTitle.setText(models.get(position).getTitle());
        holder.mDes.setText(models.get(position).getDescription());
      //  holder.mImeView.setImageResource(models.get(position).getImg());
        holder.mDate.setText(models.get(position).getDate());

        holder.mCmnts.setText(String.valueOf(models.get(position).getCmts()));
        holder.nProf.setText(models.get(position).getNomprof());

        holder.setItemClickListner(new itemClickListner() {
            @Override
            public void onItemClickedListenner(View v, int position) {
                String gTiltle = models.get(position).getTitle();
                String gDesc = models.get(position).getDescription();
                String  gProf = models.get(position).getNomprof();
                String gDate = models.get(position).getDate();
                String gFile = models.get(position).getFile();
                String gModule = models.get(position).getModule();
                String gType = models.get(position).getType();
             //   BitmapDrawable bitmapDrawable = (BitmapDrawable)holder.mImeView.getDrawable();
               // Bitmap bitmap = bitmapDrawable.getBitmap();
              //  ByteArrayOutputStream stream = new ByteArrayOutputStream();
               // bitmap.compress(Bitmap.CompressFormat.PNG , 100,stream);
               // byte[]  bytes = stream.toByteArray();


                //Intent intent = new Intent(.this,AnotherActivity.class);
//Log.v("Heerr",bytes.toString());
                Intent intent = new Intent(v.getContext(),AnotherActivity.class);
                intent.putExtra("iTitle",gTiltle.toString());
                intent.putExtra("iDesc",gDesc.toString());
                intent.putExtra("inomProf",gProf.toString());
                intent.putExtra("gDate",gDate.toString());
                intent.putExtra("gFile",gFile.toString());
                intent.putExtra("gModule",gModule.toString());
                intent.putExtra("gType",gType.toString());

                 /* intent.putExtra("iImage",bytes); */
                c.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
